package dol.example.service;

import dol.example.domain.TCharacter;

import java.util.List;

public interface TCharacterService {
    List<TCharacter> saveTCharacterList(List<TCharacter> tCharacterList);

    TCharacter findTCharacterById(Long id);
}
