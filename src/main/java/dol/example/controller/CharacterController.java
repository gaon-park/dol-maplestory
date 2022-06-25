package dol.example.controller;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TCharacterBoss;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import dol.example.dto.common.CharacterBossDetail;
import dol.example.dto.common.CharacterDetail;
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
    public ResponseEntity<CharacterDetail> getCharacterInfo(@PathVariable Long characterId) {
        TCharacter tCharacter = tCharacterService.findTCharacterById(characterId);
        List<TCharacterBoss> tCharacterBossList = tCharacterBossService.findTCharacterBossListByCharacterId(tCharacter.getId());
        List<CharacterBossDetail> characterBossDetailList = tCharacterBossService.convertToDetail(tCharacterBossList);
        List<CharacterBossDetail> salesListForBestWeeklyRevenue = tCharacterBossService.getBestWeeklyEarnings(characterBossDetailList);

        CharacterDetail characterDetail = CharacterDetail
                .builder()
                .id(tCharacter.getId())
                .avatarImgUrl(tCharacter.getAvatarImgUrl())
                .worldName(tCharacter.getWorldName())
                .characterName(tCharacter.getCharacterName())
                .lev(tCharacter.getLev())
                .exp(tCharacter.getExp())
                .job(tCharacter.getJob())
                .pop(tCharacter.getPop())
                .totRank(tCharacter.getTotRank())
                .worldRank(tCharacter.getWorldRank())
                .guild(tCharacter.getGuild())
//                .quest(tCharacter.getQuest())
                .clearableBossList(characterBossDetailList)
                .salesListForBestWeeklyRevenue(salesListForBestWeeklyRevenue)
                .weeklyRevenue(
                        salesListForBestWeeklyRevenue
                                .stream()
                                .map(o -> o.getSellingStonePrice())
                                .reduce(0, Integer::sum)
                )
                .build();
        return ResponseEntity.ok(characterDetail);
    }

    @RequestMapping(value = "/list", produces = "application/json; charset=utf8", method = RequestMethod.POST)
    public ResponseEntity<TUser> postCharacters(@RequestBody PostCharactersRequest request) {
        if (request.getUserId() == null || request.getCharacterDetailList() == null) {
            throw new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION);
        }

        TUser tUser = tUserService.findTUser(request.getUserId());
        List<TCharacter> characters = request.getCharacterDetailList()
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
//                                .quest(o.getQuest())
                                .build())
                .collect(Collectors.toList());

        // save characters
        List<TCharacter> savedList = tCharacterService.saveTCharacterList(characters);

        // if save success
        if (savedList.size() > 0) {
            // save clearable boss
            List<CharacterDetail> characterInfoRequestList = request.getCharacterDetailList();
            for (CharacterDetail characterDetail : characterInfoRequestList) {
                // if dto has clearable boss list
                if (characterDetail.getClearableBossList() != null && !characterDetail.getClearableBossList().isEmpty()) {
                    // find character id by name
                    TCharacter tCharacter = savedList.stream().filter(o -> o.getCharacterName().equals(characterDetail.getCharacterName())).findAny().orElse(null);
                    if (tCharacter != null) {
                        List<TCharacterBoss> tCharacterBossList = new ArrayList<>();
                        for (int i = 0; i < characterDetail.getClearableBossList().size(); i++) {
                            TCharacterBoss temp = new TCharacterBoss();
                            temp.setCharacterId(tCharacter.getId());
                            temp.setBossId(characterDetail.getClearableBossList().get(i).getBossId());
                            temp.setNumberOfPartyMembers(characterDetail.getClearableBossList().get(i).getNumberOfPartyMembers());
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

        return ResponseEntity.ok(tUserService.findTUser(request.getUserId()));
    }
}
