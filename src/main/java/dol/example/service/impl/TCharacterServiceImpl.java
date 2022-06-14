package dol.example.service.impl;

import dol.example.domain.TCharacter;
import dol.example.repository.TCharacterRepository;
import dol.example.service.TCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCharacterServiceImpl implements TCharacterService {

    @Autowired
    TCharacterRepository tCharacterRepository;

    @Override
    public List<TCharacter> saveTCharacterList(List<TCharacter> tCharacterList) {
        return tCharacterRepository.saveAll(tCharacterList);
    }

    @Override
    public TCharacter saveTCharacter(TCharacter tCharacter) {
        return tCharacterRepository.save(tCharacter);
    }
}
