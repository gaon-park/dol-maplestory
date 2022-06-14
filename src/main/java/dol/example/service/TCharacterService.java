package dol.example.service;

import dol.example.domain.TCharacter;

import java.util.List;

public interface TCharacterService {
    public List<TCharacter> saveTCharacterList(List<TCharacter> tCharacterList);
    public TCharacter saveTCharacter(TCharacter tCharacter);
}
