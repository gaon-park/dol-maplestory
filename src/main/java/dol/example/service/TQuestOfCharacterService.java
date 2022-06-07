package dol.example.service;

import dol.example.domain.TQuestOfCharacter;
import dol.example.dto.todo.Todo;
import javassist.NotFoundException;

import java.util.List;

public interface TQuestOfCharacterService {
    public void findQuestOfCharacter(Long characterId) throws NotFoundException;
    public TQuestOfCharacter saveQuestOfCharacter(Long characterId, List<Todo> todoDtoList) throws NotFoundException;
}
