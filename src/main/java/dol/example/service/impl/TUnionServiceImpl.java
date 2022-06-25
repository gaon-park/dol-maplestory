package dol.example.service.impl;

import com.sun.xml.bind.v2.schemagen.xmlschema.Union;
import dol.example.common.info.UnionInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TUnion;
import dol.example.dto.common.UnionDetail;
import dol.example.repository.TUnionRepository;
import dol.example.repository.TUserRepository;
import dol.example.service.TUnionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TUnionServiceImpl implements TUnionService {

    @Autowired
    TUnionRepository tUnionRepository;

    @Autowired
    TUserRepository tUserRepository;

    /**
     * union 적용 최대 캐릭터 수
     */
    private final static Integer UNION_RAIDERS_MAX_NUMBER = 42;

    @Override
    public TUnion saveTUnion(TUnion tUnion) {
        return tUnionRepository.save(tUnion);
    }

    /**
     * 캐릭터 리스트를 통해 유니온 시스템 계산
     *
     * @param list
     * @return
     */
    @Override
    public UnionDetail getUnionDetail(List<TCharacter> list) {
        UnionPair unionPair = calcUnionPair(list);
        return UnionDetail
                .builder()
                .unionStepName(unionPair.unionInfo.getName())
                .sumOfLev(unionPair.sumOfLev)
                .isHighestStep((UnionInfo.getNextUnionInfo(unionPair.unionInfo) == null) ? true : false)
                .nextUnionStepInfo(UnionInfo.getNextUnionInfo(unionPair.unionInfo))
                .build();
    }

    /**
     * 유니온 시스템 계산 로직
     * @param list
     * @return
     */
    private UnionPair calcUnionPair(List<TCharacter> list) {
        List<Integer> level = list.stream()
                .map(o -> o.getLev())
                .filter(o -> (o >= 60))
                .toList();

        // int sumOfLev = level.stream().reduce(0, Integer::sum);
        int sumOfLev = 0;
        int raidersNumber = (UNION_RAIDERS_MAX_NUMBER.compareTo(level.size()) > 0) ? level.size() : UNION_RAIDERS_MAX_NUMBER;
        for (int i = 0; i < raidersNumber; i++) {
            sumOfLev += level.get(i);
        }
        boolean formerOf5 = level.stream()
                .filter(o -> (o >= 200))
                .toList().size() > 0 ? true : false;

        UnionInfo unionInfo = null;

        // NOVICE1
        if ((level.size() >= 3 && sumOfLev >= 500) || formerOf5) {
            unionInfo = UnionInfo.NOVICE1;
        } else {
            for (UnionInfo info : UnionInfo.values()) {
                // 위에서 이미 return 했을 것
                if (info == UnionInfo.NOVICE1)
                    continue;

                if (sumOfLev < info.getSumOfLev()) {
                    unionInfo = UnionInfo.getFrontUnionInfo(info);
                    break;
                }
            }

            // SUPREME5
            if (sumOfLev >= UnionInfo.SUPREME5.getSumOfLev()) {
                unionInfo = UnionInfo.SUPREME5;
            }
        }
        return new UnionPair(unionInfo, sumOfLev, raidersNumber);
    }

    /**
     * UnionInfo, sumOfLev pair
     */
    class UnionPair {
        private UnionInfo unionInfo;
        private int sumOfLev;

        public UnionPair(
                UnionInfo unionInfo,
                int sumOfLev,
                int raidersNumber
        ) {
            this.unionInfo = unionInfo;
            this.sumOfLev = sumOfLev;
        }
    }
}
