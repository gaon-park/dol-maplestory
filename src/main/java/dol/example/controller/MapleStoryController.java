package dol.example.controller;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.WorldInfo;
import dol.example.domain.TCharacter;
import dol.example.dto.response.SearchCharacterListResponse;
import dol.example.util.JsonUtil;
import dol.example.util.JsoupUtil;
import dol.example.util.SoapUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/maplestory")
/**
 * maplestory soap api/공식 홈페이지 정보를 이용해서 데이터를 가져오는 api
 */
public class MapleStoryController {

    private static final Logger logger = LoggerFactory.getLogger(MapleStoryController.class);

    /**
     * 대표 캐릭터의 정보를 취득
     *
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/representative-character/{accountId}", method = RequestMethod.GET)
    public ResponseEntity getCharacterInfo(@PathVariable("accountId") String accountId) {
        String info = new SoapUtil().getCharacterInfoByAccountID(Long.valueOf(accountId));
        JsonUtil jsonUtil = new JsonUtil();
        return ResponseEntity.ok(jsonUtil.convertJsonToTCharacter(jsonUtil.convertXmlToJson(info)));
    }

    @RequestMapping(value = "/character/list", method = RequestMethod.GET)
    public ResponseEntity<SearchCharacterListResponse> getCharacterList(@RequestBody String siteCopyString) {
        // 캐릭터 이름 추출
        logger.info("캐릭터 이름 추출");
        WorldCharacterPair worldCharacterPair = getCharacterNameListFromSiteCopyString(siteCopyString);
        String worldName = worldCharacterPair.worldName;
        List<String> characterNameList = worldCharacterPair.characterNameList;

        // 랭킹 페이지에서 캐릭터 검색
        logger.info("랭킹 페이지에서 캐릭터 검색");
        List<TCharacter> tCharacterList = new ArrayList<>();
        List<String> notFoundCharacterList = new ArrayList<>();
        JsoupUtil jsoupUtil = new JsoupUtil();
        for (String characterName : characterNameList) {
            logger.info(characterName + " 검색");
            try {
                TCharacter searchCharacter = jsoupUtil.getCharacterInfoFromMaplestory(worldName, characterName);
                tCharacterList.add(searchCharacter);
            } catch (APIException e) {
                notFoundCharacterList.add(characterName);
            }
        }

        SearchCharacterListResponse response = new SearchCharacterListResponse();
        response.setCharacterList(tCharacterList);
        response.setNotFoundCharacterList(notFoundCharacterList);

        return ResponseEntity.ok(response);
    }

    /**
     * 사이트 전체 복사한 문자열에서 캐릭터 이름 추출
     *
     * @param siteCopyString
     * @return
     */
    private WorldCharacterPair getCharacterNameListFromSiteCopyString(String siteCopyString) {
        String[] lines = siteCopyString.split("\\n");

        // [worldName] 으로만 이루어진 문자열 검색
        String characterWorldCharacterStr = "";
        WorldInfo worldInfo = null;
        for (WorldInfo info : WorldInfo.getSpecificWorldInfoList()) {
            for (int i = 0; i < lines.length; i++) {
                if (info.getName().equals(lines[i])) {
                    worldInfo = info;
                    characterWorldCharacterStr = lines[i + 1];
                    break;
                }
            }
            if (worldInfo != null) {
                break;
            }
        }

        // 다수의 [캐릭터+월드명+캐릭터]로 되어 있는 문자열에서 [캐릭터]를 추출, 리스트에 할당
        List<String> characterNameList = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < characterWorldCharacterStr.length(); i++) {
            String s = Character.toString(characterWorldCharacterStr.charAt(i));
            temp += s;
            if (temp.endsWith(worldInfo.getName())) {
                String innerTemp = temp.substring(0, temp.length() - worldInfo.getName().length());

                if (innerTemp.length() > 0 &&
                        (innerTemp + characterWorldCharacterStr.substring(i + 1, i + innerTemp.length() + 1)).equals(innerTemp + innerTemp)) {
                    characterNameList.add(innerTemp);
                    temp = "";
                    i += innerTemp.length();
                    continue;
                }
            }
        }

        return new WorldCharacterPair(worldInfo.getName(), characterNameList);
    }

    /**
     * 월드명과 캐릭터 이름 리스트를 동시 반환하기 위한 내부 클래스
     */
    class WorldCharacterPair {
        private String worldName;
        private List<String> characterNameList;

        public WorldCharacterPair(
                String worldName,
                List<String> characterNameList
        ) {
            this.worldName = worldName;
            this.characterNameList = characterNameList;
        }
    }
}
