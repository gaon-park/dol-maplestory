package dol.example.common.info;

import lombok.Getter;

@Getter
public enum WorldInfo {

    // basic
    ALL_0(0, "전체월드", false),
    WORLD_3(3, "오로라", false),
    WORLD_4(4, "레드", false),
    WORLD_5(5, "이노시스", false),
    WORLD_6(6, "유니온", false),
    WORLD_7(7, "스카니아", false),
    WORLD_8(8, "루나", false),
    WORLD_9(9, "제니스", false),
    WORLD_10(10, "크로아", false),
    WORLD_11(11, "베라", false),
    WORLD_12(12, "엘리시움", false),
    WORLD_13(13, "아케인", false),
    WORLD_14(14, "노바", false),

    // reboot
    ALL_1(254, "전체월드/리부트", true),
    WORLD_1(1, "리부트2", true),
    WORLD_2(2, "리부트", true);

    private Integer id;
    private String name;
    private Boolean isReboot;

    private WorldInfo(
            Integer id,
            String name,
            Boolean isReboot
    ){
        this.id = id;
        this.name = name;
        this.isReboot = isReboot;
    }

    static public Integer getWorldInfoIdByWorldName(String name){
        for(WorldInfo worldInfo : WorldInfo.values()){
            if(worldInfo.name.equals(name)){
                return worldInfo.id;
            }
        }

        return null;
    }
}
