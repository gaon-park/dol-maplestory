package dol.example.service.impl;

import dol.example.domain.TBoss;
import dol.example.domain.TCharacterBoss;
import dol.example.dto.common.CharacterBossDetail;
import dol.example.repository.TBossRepository;
import dol.example.repository.TCharacterBossRepository;
import dol.example.service.TCharacterBossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TCharacterBossServiceImpl implements TCharacterBossService {
    @Autowired
    TCharacterBossRepository tCharacterBossRepository;

    @Autowired
    TBossRepository tBossRepository;

    @Override
    public List<TCharacterBoss> saveTCharacterBossList(List<TCharacterBoss> tCharacterBossList) {
        return tCharacterBossRepository.saveAll(tCharacterBossList);
    }

    @Override
    public List<TCharacterBoss> findTCharacterBossListByCharacterId(Long characterId) {
        return tCharacterBossRepository.findByCharacterId(characterId).orElse(null);
    }

    @Override
    public List<CharacterBossDetail> convertToDetail(List<TCharacterBoss> tCharacterBossList) {
        if(tCharacterBossList != null) {
            Map<Integer, TBoss> tBossMap = tBossRepository.findAll().stream().collect(Collectors.toMap(TBoss::getId, Function.identity()));
            return tCharacterBossList
                    .stream()
                    .map(o ->
                            CharacterBossDetail
                                    .builder()
                                    .bossId(o.getBossId())
                                    .bossName(tBossMap.get(o.getBossId()).getName())
                                    .numberOfPartyMembers(o.getNumberOfPartyMembers())
                                    .sellingStonePrice(tBossMap.get(o.getBossId()).getStonePrice() / o.getNumberOfPartyMembers())
                                    .build())
                    .toList();
        }
        return null;
    }
}
