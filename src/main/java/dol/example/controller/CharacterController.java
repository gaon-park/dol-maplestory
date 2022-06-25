package dol.example.controller;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TCharacterBoss;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import dol.example.dto.request.CharacterInfoRequest;
import dol.example.dto.request.PostCharactersRequest;
import dol.example.service.TCharacterBossService;
import dol.example.service.TCharacterService;
import dol.example.service.TUnionService;
import dol.example.service.TUserService;
import dol.example.util.JsoupUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    TCharacterBossService tCharacterBossService;

    @RequestMapping(value = "/load/{characterName}", method = RequestMethod.GET)
    public ResponseEntity<TCharacter> getCharacterFromMaplestory(@PathVariable String characterName) {
        JsoupUtil jsoupUtil = new JsoupUtil();
        return ResponseEntity.ok(jsoupUtil.getCharacterInfoFromMaplestory(characterName));
    }

    @RequestMapping(value = "/{characterId}", method = RequestMethod.GET)
    public ResponseEntity<TCharacter> getCharacterInfo(@PathVariable Long characterId) {
        return ResponseEntity.ok(tCharacterService.findTCharacterById(characterId));
    }

    @RequestMapping(value = "/list", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public ResponseEntity<TUser> postCharacters(@RequestBody PostCharactersRequest request) {
        Long userId = request.getUserId();
        if (userId == null) {
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }

        TUser tUser = tUserService.findTUser(userId);
        List<TCharacter> characters = request.getCharacterInfoList()
                .stream()
                .map(o ->
                        TCharacter
                                .builder()
                                .user(tUser)
                                .avatarImgUrl(o.getAvatarImgUrl())
                                .worldName(o.getWorldName())
                                .characterName(o.getCharacterName())
                                .lev(o.getLev())
                                .exp(o.getExp())
                                .job(o.getJob())
                                .pop(o.getPop())
                                .totRank(o.getTotRank())
                                .worldRank(o.getWorldRank())
                                .guild(o.getGuild())
                                .quest(o.getQuest())
                                .build())
                .collect(Collectors.toList());

        // save characters
        List<TCharacter> savedList = tCharacterService.saveTCharacterList(characters);

        // if save success
        if (savedList.size() > 0) {
            // save clearable boss
            List<CharacterInfoRequest> characterInfoRequestList = request.getCharacterInfoList();
            for (CharacterInfoRequest characterInfoRequest : characterInfoRequestList) {
                // if dto has clearable boss list
                if (characterInfoRequest.getClearableBossList() != null && !characterInfoRequest.getClearableBossList().isEmpty()) {
                    // find character id by name
                    TCharacter tCharacter = savedList.stream().filter(o -> o.getCharacterName().equals(characterInfoRequest.getCharacterName())).findAny().orElse(null);
                    if (tCharacter != null) {
                        List<TCharacterBoss> tCharacterBossList = new ArrayList<>();
                        for (int i = 0; i < characterInfoRequest.getClearableBossList().size(); i++) {
                            TCharacterBoss temp = new TCharacterBoss();
                            temp.setCharacterId(tCharacter.getId());
                            temp.setBossId(characterInfoRequest.getClearableBossList().get(i).getBossId());
                            temp.setNumberOfPartyMembers(characterInfoRequest.getClearableBossList().get(i).getNumberOfPartyMembers());
                            tCharacterBossList.add(temp);
                        }
                        tCharacterBossService.saveTCharacterBossList(tCharacterBossList);
                    }
                }
            }

            // save union
            TUnion tUnion = new TUnion();
            tUnion.setCharacterIdList(savedList.stream().map(o -> o.getId()).toList());
            tUnion.setWorldName(savedList.get(0).getWorldName());
            tUnion.setUser(savedList.get(0).getUser());
            tUnionService.saveTUnion(tUnion);
        }

        return ResponseEntity.ok(tUserService.findTUser(userId));
    }
}
