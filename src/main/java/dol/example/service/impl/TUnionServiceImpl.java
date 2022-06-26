package dol.example.service.impl;

import com.sun.xml.bind.v2.schemagen.xmlschema.Union;
import dol.example.common.exception.advice.APIException;
import dol.example.common.info.ExceptionInfo;
import dol.example.common.info.JobInfo;
import dol.example.common.info.UnionInfo;
import dol.example.common.info.WorldInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TUnion;
import dol.example.domain.TUser;
import dol.example.dto.common.SimpleCharacter;
import dol.example.dto.common.UnionDetail;
import dol.example.repository.TCharacterRepository;
import dol.example.repository.TUnionRepository;
import dol.example.repository.TUserRepository;
import dol.example.service.TCharacterService;
import dol.example.service.TUnionService;
import org.aspectj.weaver.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TUnionServiceImpl implements TUnionService {

    @Autowired
    TUnionRepository tUnionRepository;

    @Autowired
    TCharacterRepository tCharacterRepository;

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
    public UnionDetail getUnionDetail(List<TCharacter> list, Integer worldInfoId) {
        UnionDetail calculatedDetail = calcUnion(list);
        Set<JobInfo> existentJobList = getExistentJobList(list);

        return UnionDetail
                .builder()
                .worldName(WorldInfo.getWorldInfoById(worldInfoId).getName())
                .unionStepName(calculatedDetail.getUnionStepName())
                .sumOfLev(calculatedDetail.getSumOfLev())
                .isHighestStep(calculatedDetail.getIsHighestStep())
                .nextUnionStepInfo(calculatedDetail.getNextUnionStepInfo())
                .existentJobList(existentJobList.stream().toList())
                .nonexistentJobList(getNonExistentJobList(existentJobList).stream().toList())
                .duplicateCharacterMap(getDuplicateCharacterMap(list))
                .characterList(list
                        .stream()
                        .map(o ->
                                SimpleCharacter
                                        .builder()
                                        .id(o.getId())
                                        .avatarImgUrl(o.getAvatarImgUrl())
                                        .characterName(o.getCharacterName())
                                        .lev(o.getLev())
                                        .job(o.getJob())
                                        .build())
                        .toList()
                )
                .build();
    }

    /**
     * 유저 아이디, 월드 아이디를 통해 해당 월드의 유니온 정보 획득
     *
     * @param tUser
     * @param worldInfoId
     * @return
     */
    @Override
    public UnionDetail findTUnionByUserAndWorldInfoId(TUser tUser, Integer worldInfoId) {
        return getUnionDetail(
                tCharacterRepository
                        .findAllById(
                                tUnionRepository
                                        .findByUserAndWorldInfo(tUser, WorldInfo.getWorldInfoById(worldInfoId))
                                        .orElseThrow(() -> new APIException(ExceptionInfo.NOT_FOUND_EXCEPTION, "유니온 정보가 존재하지 않습니다"))
                                        .getCharacterIdList()),
                worldInfoId);
    }

    /**
     * 유니온 계산 로직
     *
     * @param list
     * @return
     */
    private UnionDetail calcUnion(List<TCharacter> list) {
        List<Integer> level = list.stream()
                .map(o -> o.getLev())
                .filter(o -> (o >= 60))
                .toList();

        int sumOfLev = 0;
        int raidersNumber = (UNION_RAIDERS_MAX_NUMBER.compareTo(level.size()) > 0) ? level.size() : UNION_RAIDERS_MAX_NUMBER;
        for (int i = 0; i < raidersNumber; i++) {
            sumOfLev += level.get(i);
        }

        UnionInfo unionInfo = null;
        for (UnionInfo info : Arrays.stream(UnionInfo.values()).sorted(((o1, o2) -> o2.getStep() - o1.getStep())).toList()) {
            if (sumOfLev >= info.getSumOfLev()) {
                unionInfo = info;
                break;
            }
        }

        // NOVICE1 의 경우 계정 내 5차 전직을 마친 캐릭터 1개 이상 또는 500이기 때문에, 5차 전직 가능한 200레벨 캐릭터가 있을 경우 추가 설정
        if (unionInfo == null
                && level.stream().filter(o -> (o >= 200)).toList().size() > 0) {
            unionInfo = UnionInfo.NOVICE1;
        }

        return UnionDetail
                .builder()
                .unionStepName(unionInfo.getName())
                .sumOfLev(sumOfLev)
                .isHighestStep((UnionInfo.getNextUnionInfo(unionInfo) == null) ? true : false)
                .nextUnionStepInfo(UnionInfo.getNextUnionInfo(unionInfo))
                .build();
    }

    /**
     * 중복된 직업을 가진 캐릭터 맵
     *
     * @param list
     * @return
     */
    private Map<JobInfo, List<SimpleCharacter>> getDuplicateCharacterMap(List<TCharacter> list) {
        Map<JobInfo, List<SimpleCharacter>> map = new HashMap<>();
        for (TCharacter tCharacter : list) {
            List<SimpleCharacter> innerList = null;
            if (map.containsKey(JobInfo.getJobInfoById(tCharacter.getJob().getFinalJobId()))) {
                innerList = map.get(tCharacter.getJob());
            } else {
                innerList = new ArrayList<>();
            }
            innerList.add(
                    SimpleCharacter
                            .builder()
                            .id(tCharacter.getId())
                            .avatarImgUrl(tCharacter.getAvatarImgUrl())
                            .characterName(tCharacter.getCharacterName())
                            .lev(tCharacter.getLev())
                            .job(tCharacter.getJob())
                            .build());
            map.put(tCharacter.getJob(), innerList);
        }

        Map<JobInfo, List<SimpleCharacter>> result = new HashMap<>();
        for (JobInfo key : map
                .keySet()
                .stream()
                .filter(o -> map.get(o).size() > 1)
                .toList()) {
            result.put(key, map.get(key));
        }

        return result;
    }

    /**
     * 존재하는 직업 정보
     *
     * @param list
     * @return
     */
    private Set<JobInfo> getExistentJobList(List<TCharacter> list) {
        Set<JobInfo> jobInfoSet = new HashSet<>();
        for (TCharacter tCharacter : list) {
            jobInfoSet.add(JobInfo.getJobInfoById(tCharacter.getJob().getFinalJobId()));
        }
        return jobInfoSet;
    }

    /**
     * 존재하지 않는 직업 정보 리스트
     *
     * @param set
     * @return
     */
    private Set<JobInfo> getNonExistentJobList(Set<JobInfo> set) {
        Set<JobInfo> jobInfoSet = new HashSet<>();
        for (JobInfo jobInfo : JobInfo.getFinalJobInfos()) {
            if (!set.contains(jobInfo)) {
                jobInfoSet.add(jobInfo);
            }
        }
        return jobInfoSet;
    }
}
