package dol.example.common;

import lombok.Getter;

import static dol.example.common.JobClassificationInfo.*;

@Getter
public enum JobInfo {
    ADVENTURER_0(0, WARRIOR, "히어로"),
    ADVENTURER_1(1, WARRIOR, "팔라딘"),
    ADVENTURER_2(2, WARRIOR, "다크나이트"),

    ADVENTURER_3(3, WIZARD, "아크메이지(불,독)"),
    ADVENTURER_4(4, WIZARD, "아크메이지(썬,콜)"),
    ADVENTURER_5(5, WIZARD, "비숍"),

    ADVENTURER_6(6, ARCHER, "보우마스터"),
    ADVENTURER_7(7, ARCHER, "신궁"),
    ADVENTURER_8(8, ARCHER, "패스파인더"),

    ADVENTURER_9(6, THIEF, "나이트로드"),
    ADVENTURER_10(10, THIEF, "섀도어"),
    ADVENTURER_11(11, THIEF, "듀얼블레이드"),

    ADVENTURER_12(12, PIRATE, "바이퍼"),
    ADVENTURER_13(13, PIRATE, "캡틴"),
    ADVENTURER_14(14, PIRATE, "캐논슈터"),

    KNIGHTS_OF_SYGNUS_0(100, WARRIOR, "소울마스터"),
    KNIGHTS_OF_SYGNUS_1(101, WARRIOR, "미하일"),
    KNIGHTS_OF_SYGNUS_2(102, WIZARD, "플레임위자드"),
    KNIGHTS_OF_SYGNUS_3(103, ARCHER, "윈드브레이커"),
    KNIGHTS_OF_SYGNUS_4(104, THIEF, "나이트워커"),
    KNIGHTS_OF_SYGNUS_5(105, PIRATE, "스트라이커"),

    RESISTANCE_0(200, WARRIOR, "블래스터"),
    RESISTANCE_1(201, WARRIOR, "데몬슬레이어"),
    RESISTANCE_2(202, WARRIOR, "데몬어벤져"),
    RESISTANCE_3(203, WIZARD, "배틀메이지"),
    RESISTANCE_4(204, ARCHER, "와일드헌터"),
    RESISTANCE_5(205, PIRATE, "메카닉"),
    RESISTANCE_6(206, THIEF, "제논"),

    HERO_0(300, WARRIOR, "아란"),
    HERO_1(301, WIZARD, "에반"),
    HERO_2(302, WIZARD, "루미너스"),
    HERO_3(303, ARCHER, "메르세데스"),
    HERO_4(304, THIEF, "팬텀"),
    HERO_5(305, PIRATE, "은월"),

    NOVA_0(400, WARRIOR, "카이저"),
    NOVA_1(401, ARCHER, "카인"),
    NOVA_2(402, THIEF, "카데나"),
    NOVA_3(403, PIRATE, "엔젤릭버스터"),

    LEF_0(500, WARRIOR, "아델"),
    LEF_1(501, WIZARD, "일리움"),
    LEF_2(502, PIRATE, "아크"),

    ANIMA_0(600, WIZARD, "라라"),
    ANIMA_1(601, THIEF, "호영"),

    ZERO_0(700, WARRIOR, "제로"),

    KINESIS_0(800, WIZARD, "키네시스");


    private Integer id;
    private JobClassificationInfo jobClassificationInfo;
    private String jobDetail;

    private JobInfo(
            Integer id,
            JobClassificationInfo jobClassificationInfo,
            String jobDetail
    ){
        this.id = id;
        this.jobClassificationInfo = jobClassificationInfo;
        this.jobDetail = jobDetail;
    }
}
