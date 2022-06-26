package dol.example.service;

import dol.example.domain.TCharacterQuest;
import dol.example.dto.todo.Todo;
import javassist.NotFoundException;

import java.util.List;

public interface TCharacterQuestService {
    TCharacterQuest findQuestOfCharacter(Long characterId) throws NotFoundException;

    TCharacterQuest saveQuestOfCharacter(Long characterId, List<Todo> todoDtoList) throws NotFoundException;
}
