package dol.example.service;

import dol.example.domain.TQuestOfCharacter;
import dol.example.dto.TodoDTO;
import javassist.NotFoundException;

import java.util.List;

public interface TQuestOfCharacterService {
    public void findQuestOfCharacter(Long characterId) throws NotFoundException;
    public TQuestOfCharacter saveQuestOfCharacter(Long characterId, List<TodoDTO> todoDtoList) throws NotFoundException;
}
