package dol.example.controller;

import dol.example.util.SoapUtil;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

    @RequestMapping(value = "/info/byNexonAccountId/{accountId}", method = RequestMethod.GET)
    public String getCharacterInfoByNexonAccountId(@PathVariable("accountId") String accountId){
        JSONObject info = new SoapUtil().getCharacterInfoByAccountID(Long.valueOf(accountId));
        System.out.println(info);

        return JSONObject.valueToString(info);
    }

    @RequestMapping(value = "/info/byDolMaplestoryId/{id}", method = RequestMethod.GET)
    public void getCharacterInfoByDolMaplestoryId(){

    }
}
