package dol.example.common.info;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Getter
public enum WorldInfo {

    // normal
    ALL_0(0, "전체월드", false),
    WORLD_3(3, "오로라", false, "/world_icon/icon_4.png"),
    WORLD_4(4, "레드", false, "/world_icon/icon_5.png"),
    WORLD_5(5, "이노시스", false, "/world_icon/icon_6.png"),
    WORLD_6(6, "유니온", false, "/world_icon/icon_7.png"),
    WORLD_7(7, "스카니아", false, "/world_icon/icon_8.png"),
    WORLD_8(8, "루나", false, "/world_icon/icon_9.png"),
    WORLD_9(9, "제니스", false, "/world_icon/icon_10.png"),
    WORLD_10(10, "크로아", false, "/world_icon/icon_11.png"),
    WORLD_11(11, "베라", false, "/world_icon/icon_12.png"),
    WORLD_12(12, "엘리시움", false, "/world_icon/icon_13.png"),
    WORLD_13(13, "아케인", false, "/world_icon/icon_14.png"),
    WORLD_14(14, "노바", false, "/world_icon/icon_15.png"),

    // reboot
    ALL_1(254, "전체월드/리부트", true),
    WORLD_1(1, "리부트2", true, "/world_icon/icon_2.png"),
    WORLD_2(2, "리부트", true, "/world_icon/icon_3.png");

    private Integer id;

    @JsonValue
    private String name;
    private Boolean isReboot;
    private String iconURL;

    private WorldInfo(
            Integer id,
            String name,
            Boolean isReboot
    ) {
        this.id = id;
        this.name = name;
        this.isReboot = isReboot;
    }

    private WorldInfo(
            Integer id,
            String name,
            Boolean isReboot,
            String iconURL
    ) {
        this.id = id;
        this.name = name;
        this.isReboot = isReboot;
        this.iconURL = iconURL;
    }

    static public WorldInfo getWorldInfoById(Integer id) {
        for (WorldInfo worldInfo : WorldInfo.values()) {
            if (worldInfo.id.equals(id)) {
                return worldInfo;
            }
        }
        return null;
    }

    static public WorldInfo getWorldInfoByWorldName(String name) {
        for (WorldInfo worldInfo : WorldInfo.values()) {
            if (worldInfo.name.equals(name)) {
                return worldInfo;
            }
        }

        return null;
    }

    static public List<WorldInfo> getNormalWorldInfoList() {
        List<WorldInfo> list = new ArrayList<>();
        for (WorldInfo worldInfo : WorldInfo.values()) {
            if (worldInfo.isReboot) {
                list.add(worldInfo);
            }
        }
        return list;
    }

    static public List<WorldInfo> getRebootWorldInfoList() {
        List<WorldInfo> list = new ArrayList<>();
        list.add(WORLD_1);
        list.add(WORLD_2);
        return list;
    }
}
