package dol.example.util;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.common.info.JobInfo;
import dol.example.common.info.WorldInfo;
import dol.example.domain.TCharacter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupUtil {

    /**
     * 월드명을 모르는 경우
     * @param characterName
     * @return
     */
    public TCharacter getCharacterInfoFromMaplestory(String characterName){
        TCharacter tCharacter = null;
        // 일반 월드 우선 검색
        try {
            tCharacter = getCharacterInfoFromTotRank(WorldInfo.ALL_0.getName(), characterName);
        } catch (APIException e1) {
            // 리부트 월드 재검색
            try {
                tCharacter = getCharacterInfoFromTotRank(WorldInfo.ALL_1.getName(), characterName);
            } catch (APIException e2) {
                // 검색 실패. 존재하지 않는 캐릭터
            }
        }

        if(tCharacter != null){
            tCharacter = setWorldRank(tCharacter);
        }
        return tCharacter;
    }

    /**
     * 월드명을 아는 경우(리부트/일반월드)
     * @param worldName
     * @param characterName
     * @return
     */
    public TCharacter getCharacterInfoFromMaplestory(String worldName, String characterName){
        TCharacter tCharacter = getCharacterInfoFromTotRank(worldName, characterName);
        // 검색에 성공했을 경우, 월드 랭킹 설정
        if(tCharacter != null){
            tCharacter = setWorldRank(tCharacter);
        }
        return tCharacter;
    }

    private TCharacter getCharacterInfoFromTotRank(String worldName, String characterName){
        String totRankingURL = "https://maplestory.nexon.com/Ranking/World/Total"
                + "?c=" + characterName
                + "&w=" + ((worldName.contains("리부트")) ? Integer.toString(WorldInfo.ALL_1.getId()) : Integer.toString(WorldInfo.ALL_0.getId()));

        Element element = getCharacterInfoElementFromURL(totRankingURL);

        if(element == null){
            throw new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION);
        }

        // TCharater entity에 값 저장
        TCharacter tCharacter = null;
        tCharacter = new TCharacter();
        tCharacter.setAvatarImgUrl(element.select(".char_img").select("img").attr("src"));
        tCharacter.setCharacterName(element.selectFirst("dt").text());
        tCharacter.setJob(JobInfo.getJobInfoByJobDetail(element.selectFirst("dd").text().split("/")[1].strip()));

        Elements elements = element.select("td");
        // elemenets의 순서
        // 0. 랭킹 순위변동(xx -xx)
        // 1. 닉네임 직업 / 직업디테일(xx xx / xx)
        // 2. 레벨(Lv.xxx)
        // 3. 경험치(xxx,xxx,xxx,xxx)
        // 4. 인기도(xx)
        // 5. 길드(xx)
        tCharacter.setTotRank(Integer.parseInt(elements.get(0).text().split(" ")[0]));
        tCharacter.setLev(Integer.parseInt(elements.get(2).text().replace("Lv.", "")));
        tCharacter.setExp(Long.parseLong(elements.get(3).text().replace(",", "")));
        tCharacter.setPop(Integer.parseInt(elements.get(4).text()));
        tCharacter.setGuild(elements.get(5).text());

        // world
        tCharacter.setWorldName(worldName);

        return tCharacter;
    }

    private TCharacter setWorldRank(TCharacter tCharacter){
        // 전체 월드로 받은 경우
        if(tCharacter.getWorldName().equals(WorldInfo.ALL_0.getName())){
            for(Integer worldId : WorldInfo.getNormalWorldIdList()){
                try {
                    tCharacter = searchWorldRank(tCharacter, worldId);
                    // 성공했다는 것은 곧, 월드명을 찾았다는 것을 의미하므로
                    break;
                } catch (APIException e){
                    continue;
                }
            }
        } else if(tCharacter.getWorldName().equals(WorldInfo.ALL_1.getName())){
            for(Integer worldId : WorldInfo.getRebootWorldIdList()){
                try {
                    tCharacter = searchWorldRank(tCharacter, worldId);
                    // 성공했다는 것은 곧, 월드명을 찾았다는 것을 의미하므로
                    break;
                } catch (APIException e){
                    continue;
                }
            }
        }
        // 이미 상세 월드를 알고 있는 경우
        else{
            tCharacter = searchWorldRank(tCharacter, WorldInfo.getWorldInfoIdByWorldName(tCharacter.getWorldName()));
        }

        return tCharacter;
    }

    private TCharacter searchWorldRank(TCharacter tCharacter, Integer worldId){
        String worldRankURL = "https://maplestory.nexon.com/Ranking/World/Total"
                + "?c=" + tCharacter.getCharacterName()
                + "&w=" + worldId;

        Element element = getCharacterInfoElementFromURL(worldRankURL);

        if(element == null) {
            throw new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION);
        }

        // TCharater entity에 world rank 값 저장
        Elements elements = element.select("td");
        // elemenets의 순서
        // 0. 랭킹 순위변동(xx -xx)
        // 1. 닉네임 직업 / 직업디테일(xx xx / xx)
        // 2. 레벨(Lv.xxx)
        // 3. 경험치(xxx,xxx,xxx,xxx)
        // 4. 인기도(xx)
        // 5. 길드(xx)
        tCharacter.setWorldRank(Integer.parseInt(elements.get(0).text().split(" ")[0]));

        return tCharacter;
    }

    private Element getCharacterInfoElementFromURL(String URL){
        Element element = null;
        try {
            Connection conn = Jsoup.connect(URL);
            Document html = conn.get();

            // 해당 캐릭터 추출
            element = html.selectFirst(".search_com_chk");
        } catch (IOException e){
            e.printStackTrace();
        }
        return element;
    }
}
