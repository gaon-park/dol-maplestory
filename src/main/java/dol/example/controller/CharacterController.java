package dol.example.controller;

import dol.example.domain.TCharacter;
import dol.example.service.TCharacterService;
import dol.example.util.JsoupUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

    @Autowired
    TCharacterService tCharacterService;

    @RequestMapping(value = "/info/byDolMaplestoryId/{id}", method = RequestMethod.GET)
    public void getCharacterInfoByDolMaplestoryId(){

    }

    @RequestMapping(value = "/info/{characterName}", method = RequestMethod.GET)
    public ResponseEntity<TCharacter> getCharacterInfo(@PathVariable String characterName){
        JsoupUtil jsoupUtil = new JsoupUtil();
        TCharacter tCharacter = jsoupUtil.getCharacterInfoFromMaplestory(characterName);
        return ResponseEntity.ok(tCharacter);
    }

    @RequestMapping(value = "/list", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public void postCharacterInfo(@RequestBody List<TCharacter> request) {
        System.out.println(request);
        tCharacterService.saveTCharacterList(request);
    }
}
