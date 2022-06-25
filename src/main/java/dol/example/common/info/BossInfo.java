package dol.example.common.info;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum BossInfo {
    // 일반
    ZAKUM_EASY(1, "자쿰", "이지", true, false, false, 119835),
    ZAKUM_NORMAL(2, "자쿰", "노멀", true, false, false, 366997),
    ZAKUM_CHAOS(3, "자쿰", "카오스", false, true, false, 9741285),

    HORNTAIL_EASY(4, "혼테일", "이지", true, false, false, 528474),
    HORNTAIL_NORMAL(5, "혼테일", "노멀", true, false, false, 606666),
    HORNTAIL_CHAOS(6, "혼테일", "카오스", true, false, false, 810086),

    PINK_BEAN_NORMAL(7, "핑크빈", "노멀", true, false, false, 841544),
    PINK_BEAN_CHAOS(8, "핑크빈", "카오스", false, true, false, 7923110),

    KAUNG(9, "카웅", "노멀", true, false, false, 748970),

    SYGNUS_EASY(10, "시그너스", "이지", false, true, false, 5496394),
    SYGNUS_NORMAL(11, "시그너스", "노멀", false, true, false, 9039130),

    POPULATUS_EASY(12, "파풀라투스", "이지", true, false, false, 410135),
    POPULATUS_NORMAL(13, "파풀라투스", "노멀", true, false, false, 1596506),
    populatus_chaos(14, "파풀라투스", "카오스", false, true, false, 26725593),

    GUARDIAN_ANGEL_SLIME_NORMAL(15, "가디언 엔젤 슬라임", "노멀", false, true, false, 46935874),
    GUARDIAN_ANGEL_SLIME_CHAOS(16, "가디언 엔젤 슬라임", "카오스", false, true, false, 155492141),

    DUSK_NORMAL(17, "더스크", "노멀", false, true, false, 71054562),
    DUSK_CHAOS(18, "더스크", "카오스", false, true, false, 160173752),

    DUNKEL_NORMAL(19, "듄켈", "노멀", false, true, false, 76601412),
    DUNKEL_HARD(20, "듄켈", "하드", false, true, false, 168609280),

    // 루타비스
    banban_normal(21, "반반", "노멀", true, false, false, 580003),
    BANBAN_CHAOS(22, "반반", "카오스", false, true, false, 9818154),

    PIERRE_NORMAL(23, "피에르", "노멀", true, false, false, 580003),
    PIERRE_CHAOS(24, "피에르", "카오스", false, true, false, 9838932),

    BLOODY_QUEEN_NORMAL(25, "블러디퀸", "노멀", true, false, false, 580003),
    BLOODY_QUEEN_CHAOS(26, "블러디퀸", "카오스", false, true, false, 9806780),

    BELLUM_NORMAL(27, "벨룸", "노멀", true, false, false, 580003),
    BELLUM_CHAOS(28, "벨룸", "카오스", false, true, false, 12590202),

    // 군단장
    HILLA_NORMAL(29, "힐라", "노멀", true, false, false, 479343),
    HILLA_HARD(30, "힐라", "하드", false, true, false, 6936489),

    REAL_HILLA_NORMAL(31, "진 힐라", "노멀", false, true, false, 148112376),
    REAL_HILLA_HARD(32, "진 힐라", "하드", false, true, false, 190159452),

    VON_LEON_EASY(33, "반 레온", "이지", true, false, false, 633927),
    VON_LEON_NORMAL(34, "반 레온", "노멀", true, false, false, 873601),
    VON_LEON_HARD(35, "반 레온", "하드", true, false, false, 1467984),

    ARKARIUM_EASY(36, "아카이럼", "이지", true, false, false, 690246),
    ARKARIUM_NORMAL(37, "아카이럼", "노멀", true, false, false, 1510227),

    MAGNUS_EASY(38, "매그너스", "이지", true, false, false, 432605),
    MAGNUS_NORMAL(39, "매그너스", "노멀", true, false, false, 1553066),
    MAGNUS_HARD(40, "매그너스", "하드", false, true, false, 11579023),

    SUU_NORMAL(41, "스우", "노멀", false, true, false, 33942566),
    SUU_HARD(42, "스우", "하드", false, true, false, 118294192),

    DEMIAN_NORMAL(43, "데미안", "노멀", false, true, false, 35517853),
    DEMIAN_HARD(44, "데미안", "하드", false, true, false, 112480613),

    LUCID_EASY(45, "루시드", "이지", false, true, false, 48058319),
    LUCID_NORMAL(46, "루시드", "노멀", false, true, false, 57505626),
    LUCID_HARD(47, "루시드", "하드", false, true, false, 131095655),

    WILL_EASY(48, "윌", "이지", false, true, false, 52139127),
    WILL_NORMAL(49, "윌", "노멀", false, true, false, 66311463),
    WILL_HARD(50, "윌", "하드", false, true, false, 145038483),

    // 그란디스
    SEREN_NORMAL(51, "선택받은 세렌", "노멀", false, true, false, 196904752),
    SEREN_HARD(52, "선택받은 세렌", "하드", false, true, false, 267825621),
    // TODO set seren extreme mode stone price
    SEREN_EXTREME(53, "선택받은 세렌", "하드", false, true, false, 2147483647),

    KALOS(54, "감시자 칼로스", "노멀", false, true, false, 300000000),

    // 초월자
    BLACK_MAGE_NORMAL(55, "검은 마법사", "노멀", false, false, true, 1418809857),
    // TODO set black mage extreme mode stone price
    BLACK_MAGE_EXTREME(56, "검은 마법사", "노멀", false, false, true, 2147483647)
    ;

    private Integer id;
    private String name;
    private String difficulty;
    private Boolean isDailyBoss;
    private Boolean isWeeklyBoss;
    private Boolean isMonthlyBoss;
    private Integer stonePrice;

    private BossInfo(
            Integer id,
            String name,
            String difficulty,
            Boolean isDailyBoss,
            Boolean isWeeklyBoss,
            Boolean isMonthlyBoss,
            Integer stonePrice
    ){
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.isDailyBoss = isDailyBoss;
        this.isWeeklyBoss = isWeeklyBoss;
        this.isMonthlyBoss = isMonthlyBoss;
        this.stonePrice = stonePrice;
    }

    static public BossInfo getBossInfoById(Integer id){
        for(BossInfo bossInfo : BossInfo.values()){
            if(bossInfo.id.equals(id)){
                return bossInfo;
            }
        }
        return null;
    }

    static public List<BossInfo> getBossInfoByName(String name){
        List<BossInfo> result = new ArrayList<>();
        for(BossInfo bossInfo : BossInfo.values()){
            if(bossInfo.getName().equals(name)){
                result.add(bossInfo);
            }
        }
        return result;
    }

    public Integer getStonePrice(Integer party){
        return this.stonePrice / party;
    }

    public Integer getRebootStonePrice(Integer party){
        return this.stonePrice * 5 / party;
    }
}