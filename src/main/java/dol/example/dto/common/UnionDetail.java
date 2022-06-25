package dol.example.dto.common;

import dol.example.common.info.JobInfo;
import dol.example.common.info.UnionInfo;
import dol.example.domain.TCharacter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

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
     * 전체 캐릭터 리스트
     */
    private List<TCharacter> characterList;

    /**
     * 직업이 중복되는 캐릭터들
     */
    private List<TCharacter> duplicateCharacterList;
}
