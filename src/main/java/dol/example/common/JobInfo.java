package dol.example.common;

import lombok.Getter;

import static dol.example.common.JobClassificationInfo.*;

@Getter
public enum JobInfo {
    ADVENTURER0(0, WARRIOR, "히어로"),
    ADVENTURER1(1, WARRIOR, "팔라딘"),
    ADVENTURER2(2, WARRIOR, "다크나이트"),

    ADVENTURER3(3, WIZARD, "아크메이지(불,독)"),
    ADVENTURER4(4, WIZARD, "아크메이지(썬,콜)"),
    ADVENTURER5(5, WIZARD, "비숍"),

    ADVENTURER6(6, ARCHER, "보우마스터"),
    ADVENTURER7(7, ARCHER, "신궁"),
    ADVENTURER8(8, ARCHER, "패스파인더"),

    ADVENTURER9(6, THIEF, "나이트로드"),
    ADVENTURER10(10, THIEF, "섀도어"),
    ADVENTURER11(11, THIEF, "듀얼블레이드"),

    ADVENTURER12(12, PIRATE, "바이퍼"),
    ADVENTURER13(13, PIRATE, "캡틴"),
    ADVENTURER14(14, PIRATE, "캐논슈터"),

    KNIGHTS_OF_SYGNUS0(100, WARRIOR, "소울마스터"),
    KNIGHTS_OF_SYGNUS1(101, WARRIOR, "미하일"),
    KNIGHTS_OF_SYGNUS2(102, WIZARD, "플레임위자드"),
    KNIGHTS_OF_SYGNUS3(103, ARCHER, "윈드브레이커"),
    KNIGHTS_OF_SYGNUS4(104, THIEF, "나이트워커"),
    KNIGHTS_OF_SYGNUS5(105, PIRATE, "스트라이커"),

    RESISTANCE0(200, WARRIOR, "블래스터"),
    RESISTANCE1(201, WARRIOR, "데몬슬레이어"),
    RESISTANCE2(202, WARRIOR, "데몬어벤져"),
    RESISTANCE3(203, WIZARD, "배틀메이지"),
    RESISTANCE4(204, ARCHER, "와일드헌터"),
    RESISTANCE5(205, PIRATE, "메카닉"),
    RESISTANCE6(206, THIEF, "제논"),

    HERO0(300, WARRIOR, "아란"),
    HERO1(301, WIZARD, "에반"),
    HERO2(302, WIZARD, "루미너스"),
    HERO3(303, ARCHER, "메르세데스"),
    HERO4(304, THIEF, "팬텀"),
    HERO5(305, PIRATE, "은월"),

    NOVA0(400, WARRIOR, "카이저"),
    NOVA1(401, ARCHER, "카인"),
    NOVA2(402, THIEF, "카데나"),
    NOVA3(403, PIRATE, "엔젤릭버스터"),

    LEF0(500, WARRIOR, "아델"),
    LEF1(501, WIZARD, "일리움"),
    LEF2(502, PIRATE, "아크"),

    ANIMA0(600, WIZARD, "라라"),
    ANIMA1(601, THIEF, "호영"),

    ZERO0(700, WARRIOR, "제로"),

    KINESIS0(800, WIZARD, "키네시스");


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

    /**
     * jobDetail 값으로 JobInfo 확인
     * @param jobDetail
     * @return
     */
    static public JobInfo getJobInfoByJobDetail(String jobDetail){
        for(JobInfo jobInfo : JobInfo.values()){
            if(jobInfo.jobDetail.equals(jobDetail)){
                return jobInfo;
            }
        }
        return null;
    }
}
