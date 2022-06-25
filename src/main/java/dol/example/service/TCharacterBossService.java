package dol.example.service;

import dol.example.domain.TCharacterBoss;
import dol.example.dto.common.CharacterBossDetail;

import java.util.List;

public interface TCharacterBossService {
    List<TCharacterBoss> saveTCharacterBossList(List<TCharacterBoss> tCharacterBossList);
    List<TCharacterBoss> findTCharacterBossListByCharacterId(Long characterId);
    List<CharacterBossDetail> convertToDetail(List<TCharacterBoss> tCharacterBossList);
    List<CharacterBossDetail> getBestWeeklyEarnings(List<CharacterBossDetail> characterBossDetailList);
}
