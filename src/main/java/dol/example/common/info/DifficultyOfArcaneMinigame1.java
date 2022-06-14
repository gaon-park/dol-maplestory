package dol.example.common.info;

import lombok.Getter;

@Getter
public enum DifficultyOfArcaneMinigame1 {
    EASY(0, "이지"),
    NORMAL(1, "보통"),
    HARD(2, "어려움");

    private Integer id;
    private String difficulty;

    private DifficultyOfArcaneMinigame1(
            Integer id,
            String difficulty
    ){
        this.id = id;
        this.difficulty = difficulty;
    }
}
