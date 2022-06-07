package dol.example.common;

import lombok.Getter;

import static dol.example.common.JobClassificationInfo.*;

@Getter
public enum JobInfo {

    ADVENTURER_WARRIOR0(0, WARRIOR, "검사"),
    ADVENTURER_WARRIOR1(1, WARRIOR, "파이터"),
    ADVENTURER_WARRIOR2(2, WARRIOR, "크루세이더"),
    ADVENTURER_WARRIOR3(3, WARRIOR, "히어로"),
    ADVENTURER_WARRIOR4(4, WARRIOR, "페이지"),
    ADVENTURER_WARRIOR5(5, WARRIOR, "나이트"),
    ADVENTURER_WARRIOR6(6, WARRIOR, "팔라딘"),
    ADVENTURER_WARRIOR7(7, WARRIOR, "스피어맨"),
    ADVENTURER_WARRIOR8(8, WARRIOR, "버서커"),
    ADVENTURER_WARRIOR9(9, WARRIOR, "다크나이트"),

    ADVENTURER_WIZARD0(10, WIZARD, "매지션"),
    ADVENTURER_WIZARD1(11, WIZARD, "위자드(불,독)"),
    ADVENTURER_WIZARD2(12, WIZARD, "메이지(불,독)"),
    ADVENTURER_WIZARD3(13, WIZARD, "아크메이지(불,독)"),
    ADVENTURER_WIZARD4(14, WIZARD, "위자드(썬,콜)"),
    ADVENTURER_WIZARD5(15, WIZARD, "메이지(썬,콜)"),
    ADVENTURER_WIZARD6(16, WIZARD, "아크메이지(썬,콜)"),
    ADVENTURER_WIZARD7(17, WIZARD, "클레릭"),
    ADVENTURER_WIZARD8(18, WIZARD, "프리스트"),
    ADVENTURER_WIZARD9(19, WIZARD, "비숍"),

    ADVENTURER_ARCHER0(20, ARCHER, "아처"),
    ADVENTURER_ARCHER1(21, ARCHER, "헌터"),
    ADVENTURER_ARCHER2(22, ARCHER, "레인저"),
    ADVENTURER_ARCHER3(23, ARCHER, "보우마스터"),
    ADVENTURER_ARCHER4(24, ARCHER, "사수"),
    ADVENTURER_ARCHER5(25, ARCHER, "저격수"),
    ADVENTURER_ARCHER6(26, ARCHER, "신궁"),
    ADVENTURER_ARCHER7(27, ARCHER, "에이션트 아처"),
    ADVENTURER_ARCHER8(28, ARCHER, "체이서"),
    ADVENTURER_ARCHER9(29, ARCHER, "패스파인더"),

    ADVENTURER_THIEF0(30, THIEF, "로그"),
    ADVENTURER_THIEF1(31, THIEF, "어쌔신"),
    ADVENTURER_THIEF2(32, THIEF, "허밋"),
    ADVENTURER_THIEF3(33, THIEF, "나이트로드"),
    ADVENTURER_THIEF4(34, THIEF, "시프"),
    ADVENTURER_THIEF5(35, THIEF, "시프마스터"),
    ADVENTURER_THIEF6(36, THIEF, "섀도어"),
    ADVENTURER_THIEF7(37, THIEF, "듀얼블레이드"),
    ADVENTURER_THIEF8(38, THIEF, "세미듀어러"),
    ADVENTURER_THIEF9(39, THIEF, "듀어러"),
    ADVENTURER_THIEF10(40, THIEF, "듀얼마스터"),
    ADVENTURER_THIEF11(41, THIEF, "슬래셔"),
    ADVENTURER_THIEF12(42, THIEF, "듀얼블레이더"),

    ADVENTURER_PIRATE0(43, PIRATE, "해적"),
    ADVENTURER_PIRATE1(44, PIRATE, "인파이터"),
    ADVENTURER_PIRATE2(45, PIRATE, "버커니어"),
    ADVENTURER_PIRATE3(46, PIRATE, "바이퍼"),
    ADVENTURER_PIRATE4(47, PIRATE, "건슬링거"),
    ADVENTURER_PIRATE5(48, PIRATE, "발키리"),
    ADVENTURER_PIRATE6(49, PIRATE, "캡틴"),
    ADVENTURER_PIRATE7(50, PIRATE, "해적(캐논슈터)"),
    ADVENTURER_PIRATE8(51, PIRATE, "캐논슈터"),
    ADVENTURER_PIRATE9(52, PIRATE, "캐논블래스터"),
    ADVENTURER_PIRATE10(53, PIRATE, "캐논마스터"),

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
