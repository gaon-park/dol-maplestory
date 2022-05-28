package dol.example.common;

import lombok.Getter;

@Getter
public enum JobClassificationInfo {
    WARRIOR(0, "전사"),
    WIZARD(1, "마법사"),
    ARCHER(2, "궁수"),
    THIEF(3, "도적"),
    PIRATE(4, "해적");

    private Integer id;
    private String name;

    private JobClassificationInfo(
            Integer id,
            String name
    ){
        this.id = id;
        this.name = name;
    }
}
