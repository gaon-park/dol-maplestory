package dol.example.dto.common;

import dol.example.common.info.JobInfo;
import dol.example.domain.TCharacter;
import dol.example.domain.TQuestOfCharacter;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterDetail implements Serializable {
    private Long id;
    private String avatarImgUrl;
    private String worldName;
    private String characterName;
    private Integer lev;
    private Long exp;
    private JobInfo job;
    private Integer pop;
    private Integer totRank;
    private Integer worldRank;
    private String guild;
    private List<CharacterBossDetail> clearableBossList;
    private List<CharacterBossDetail> salesListForBestWeeklyRevenue;
    private Integer weeklyRevenue;
}
