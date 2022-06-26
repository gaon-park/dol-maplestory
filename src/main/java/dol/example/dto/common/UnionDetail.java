package dol.example.dto.common;

import dol.example.common.info.JobInfo;
import dol.example.common.info.UnionInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class UnionDetail implements Serializable {

    /**
     * 월드명
     */
    private String worldName;

    /**
     * 유니온 등급
     */
    private String unionStepName;

    /**
     * 캐릭터 레벨 합산
     */
    private Integer sumOfLev;

    /**
     * 가장 높은 등급인가?
     */
    private Boolean isHighestStep;

    /**
     * 다음 등급 정보
     */
    private UnionInfo nextUnionStepInfo;

    /**
     * 키운 캐릭터들의 직업 정보
     */
    private List<JobInfo> existentJobList;

    /**
     * 아직 키우지 않은 캐릭터들의 직업 정보
     */
    private List<JobInfo> nonexistentJobList;

    /**
     * 중복된 직업을 가진 캐릭터 맵
     */
    private Map<JobInfo, List<SimpleCharacter>> duplicateCharacterMap;

    /**
     * 전체 캐릭터 리스트
     */
    private List<SimpleCharacter> characterList;
}
