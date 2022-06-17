package dol.example.controller;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import dol.example.service.TCharacterService;
import dol.example.service.TUnionService;
import dol.example.service.TUserService;
import dol.example.util.JsoupUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/character")
public class CharacterController {

    @Autowired
    TUserService tUserService;

    @Autowired
    TCharacterService tCharacterService;

    @Autowired
    TUnionService tUnionService;

    @RequestMapping(value = "/info/{characterName}", method = RequestMethod.GET)
    public ResponseEntity<TCharacter> getCharacterInfo(@PathVariable String characterName){
        JsoupUtil jsoupUtil = new JsoupUtil();
        TCharacter tCharacter = jsoupUtil.getCharacterInfoFromMaplestory(characterName);
        return ResponseEntity.ok(tCharacter);
    }

    @RequestMapping(value = "/list", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public ResponseEntity<TUser> postCharacterInfo(@RequestBody Map<String, Object> request) {
        List<TCharacter> requestCharacters = (List<TCharacter>) request.get("characters");
        Long userId = (Long) request.get("userId");
        if(requestCharacters == null || userId == null){
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }

        // save characters
        List<TCharacter> savedList = tCharacterService.saveTCharacterList(userId, requestCharacters);

        // save union
        TUnion tUnion = new TUnion();
        tUnion.setCharacterIdList(savedList.stream().map(o -> o.getId()).toList());
        tUnion.setWorldName(savedList.get(0).getWorldName());
        tUnion.setUser(savedList.get(0).getUser());
        tUnionService.saveTUnion(tUnion);

        return ResponseEntity.ok(tUserService.findTUser(savedList.get(0).getUser().getId()));
    }
}
