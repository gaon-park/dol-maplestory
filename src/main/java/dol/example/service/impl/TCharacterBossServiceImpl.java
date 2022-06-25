package dol.example.service.impl;

import dol.example.domain.TCharacterBoss;
import dol.example.repository.TCharacterBossRepository;
import dol.example.service.TCharacterBossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCharacterBossServiceImpl implements TCharacterBossService {
    @Autowired
    TCharacterBossRepository tCharacterBossRepository;

    @Override
    public List<TCharacterBoss> saveTCharacterBossList(List<TCharacterBoss> tCharacterBossList) {
        return tCharacterBossRepository.saveAll(tCharacterBossList);
    }
}
