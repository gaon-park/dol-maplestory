package dol.example.dto.todo;

import dol.example.common.info.DifficultyOfArcaneMinigame1;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Symbol implements Serializable {
    private Integer lev;
    private Integer growth;

    /**
     * 츄츄 아일랜드(배고픈 무토) 전용 변수
     */
    private DifficultyOfArcaneMinigame1 difficultyOfArcaneMinigame1;

    /**
     * 레헬른(드림 브레이커) 전용 변수
     */
    private Integer floor;

    /**
     * 아르카나(스피릿 세이비어) 전용 변수
     */
    private Integer score;
}
