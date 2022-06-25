package dol.example.service.impl;

import dol.example.common.info.BossInfo;
import dol.example.domain.TCharacterBoss;
import dol.example.dto.common.CharacterBossDetail;
import dol.example.repository.TCharacterBossRepository;
import dol.example.service.TCharacterBossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TCharacterBossServiceImpl implements TCharacterBossService {
    @Autowired
    TCharacterBossRepository tCharacterBossRepository;

    private static final Integer AVAILABLE_STONE_COUNTS_FOR_SALE_PER_WEEK = 180;

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
        if (tCharacterBossList != null) {
            return tCharacterBossList
                    .stream()
                    .map(o ->
                            CharacterBossDetail
                                    .builder()
                                    .bossId(o.getBossId())
                                    .bossName(BossInfo.getBossInfoById(o.getBossId()).getName())
                                    .numberOfPartyMembers(o.getNumberOfPartyMembers())
                                    .sellingStonePrice(BossInfo.getBossInfoById(o.getBossId()).getStonePrice() / o.getNumberOfPartyMembers())
                                    .build())
                    .toList();
        }
        return null;
    }

    /**
     * 최대 주간 수익을 낼 수 있게 리스트 재구성
     *
     * @param characterBossDetailList
     * @return
     */
    @Override
    public List<CharacterBossDetail> getBestWeeklyEarnings(List<CharacterBossDetail> characterBossDetailList) {
        List<CharacterBossDetail> priceList =
                characterBossDetailList
                        .stream()
                        .sorted(Comparator.comparing(CharacterBossDetail::getSellingStonePrice))
                        .toList();
        Integer maxIndex = (AVAILABLE_STONE_COUNTS_FOR_SALE_PER_WEEK.compareTo(priceList.size()) > 0) ? priceList.size() : AVAILABLE_STONE_COUNTS_FOR_SALE_PER_WEEK;
        return priceList.subList(0, maxIndex);
    }
}
