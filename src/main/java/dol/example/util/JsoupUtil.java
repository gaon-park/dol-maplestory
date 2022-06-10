package dol.example.util;

import dol.example.common.JobInfo;
import dol.example.common.WorldInfo;
import dol.example.domain.TCharacter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.io.IOException;

public class JsoupUtil {

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

        // TCharater entity에 값 저장
        TCharacter tCharacter = null;
        if(element != null){
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
        }

        return tCharacter;
    }

    private TCharacter setWorldRank(TCharacter tCharacter){
        String worldRankURL = "https://maplestory.nexon.com/Ranking/World/Total"
                + "?c=" + tCharacter.getCharacterName()
                + "&w=" + WorldInfo.getWorldInfoIdByWorldName(tCharacter.getWorldName());

        Element element = getCharacterInfoElementFromURL(worldRankURL);

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
