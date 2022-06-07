package dol.example.controller;

import dol.example.domain.TCharacter;
import dol.example.util.JsonUtil;
import dol.example.util.SoapUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/maplestory")
/**
 * maplestory soap api를 이용해서 정보를 가져오는 api
 */
public class MapleStoryController {

    /**
     * 대표 캐릭터의 정보를 취득
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/representative-character/{accountId}", method = RequestMethod.GET)
    public TCharacter getCharacterInfo(@PathVariable("accountId") String accountId){
        String info = new SoapUtil().getCharacterInfoByAccountID(Long.valueOf(accountId));
        System.out.println(info);

        JsonUtil jsonUtil = new JsonUtil();
        return jsonUtil.convertJsonToTCharacter(jsonUtil.convertXmlToJson(info));
    }
}
