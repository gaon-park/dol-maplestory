package dol.example.service.impl;

import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.domain.TQuestOfCharacter;
import dol.example.dto.todo.Todo;
import dol.example.repository.TCharacterRepository;
import dol.example.repository.TQuestOfCharacterRepository;
import dol.example.service.TQuestOfCharacterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TQuestOfCharacterServiceImpl implements TQuestOfCharacterService {

    @Autowired
    private TCharacterRepository tCharacterRepository;

    @Autowired
    private TQuestOfCharacterRepository tQuestOfCharacterRepository;

    @Override
    public TQuestOfCharacter findQuestOfCharacter(Long characterId) {
        return tQuestOfCharacterRepository.findById(characterId).orElseThrow(() -> new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION, "존재하지 않는 캐릭터 id"));
    }

    @Override
    public TQuestOfCharacter saveQuestOfCharacter(Long characterId, List<Todo> todoDTOList) {
        tCharacterRepository.findById(characterId).orElseThrow(() -> new APIException(ExceptionInfo.INVALID_REQUEST_EXCEPTION, "존재하지 않는 캐릭터 id " + characterId));

        TQuestOfCharacter tQuestOfCharacter = convert(characterId, todoDTOList);
        return tQuestOfCharacterRepository.save(tQuestOfCharacter);
    }

    private TQuestOfCharacter convert(Long characterId, List<Todo> todoDTOList){
        TQuestOfCharacter tQuestOfCharacter = new TQuestOfCharacter();
        tQuestOfCharacter.setCharacterId(characterId);

        for(Todo dto : todoDTOList){
            switch (dto.getTodoInfo()){
                case ARCANE_QUEST0 -> tQuestOfCharacter.setArcaneQuest0(true);
                case ARCANE_QUEST1 -> tQuestOfCharacter.setArcaneQuest1(true);
                case ARCANE_QUEST2 -> tQuestOfCharacter.setArcaneQuest2(true);
                case ARCANE_QUEST3 -> tQuestOfCharacter.setArcaneQuest3(true);
                case ARCANE_QUEST4 -> tQuestOfCharacter.setArcaneQuest4(true);
                case ARCANE_QUEST5 -> tQuestOfCharacter.setArcaneQuest5(true);

                case ARCANE_MINIGAME0 -> tQuestOfCharacter.setArcaneMinigame0(true);
                case ARCANE_MINIGAME1 -> tQuestOfCharacter.setArcaneMinigame1(true);
                case ARCANE_MINIGAME2 -> tQuestOfCharacter.setArcaneMinigame2(true);
                case ARCANE_MINIGAME3 -> tQuestOfCharacter.setArcaneMinigame3(true);
                case ARCANE_MINIGAME4 -> tQuestOfCharacter.setArcaneMinigame4(true);
                case ARCANE_MINIGAME5 -> tQuestOfCharacter.setArcaneMinigame5(true);

                case TENEBRIS0 -> tQuestOfCharacter.setTenebris0(true);

                case AUTHENTIC_QUEST0 -> tQuestOfCharacter.setAuthenticQuest0(true);
                case AUTHENTIC_QUEST1 -> tQuestOfCharacter.setAuthenticQuest1(true);
                case AUTHENTIC_QUEST2 -> tQuestOfCharacter.setAuthenticQuest2(true);

                case HAVEN0 -> tQuestOfCharacter.setHaven0(true);
                case DARK_WORLD_TREE0 -> tQuestOfCharacter.setDarkWorldTree0(true);

                case KRITIAS0 -> tQuestOfCharacter.setKritias0(true);

                case MONSTER_PARK0 -> tQuestOfCharacter.setMonsterPark0(true);

                case UNION0 -> tQuestOfCharacter.setUnion0(true);

                case GUILD0 -> tQuestOfCharacter.setGuild0(true);
                case GUILD1 -> tQuestOfCharacter.setGuild1(true);
            }
        }

        return tQuestOfCharacter;
    }
}
