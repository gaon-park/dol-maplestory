package dol.example.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CharacterBossDetail implements Serializable {
    private Integer bossId;
    private String bossName;
    private Integer numberOfPartyMembers;
    // 결정석 값/파티원 수
    private Integer sellingStonePrice;
}
