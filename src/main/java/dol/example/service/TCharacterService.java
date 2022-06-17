package dol.example.service;

import dol.example.common.exception.advice.APIException;
import dol.example.domain.TCharacter;

import java.util.List;

public interface TCharacterService {
    public List<TCharacter> saveTCharacterList(Long userId, List<TCharacter> tCharacterList) throws APIException;
    public TCharacter saveTCharacter(TCharacter tCharacter);
}
