package dol.example.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum BossInfo {
    // 일반
    Zakum_easy(0, "자쿰", "이지", true, false, false, 119835),
    Zakum_normal(1, "자쿰", "노멀", true, false, false, 366997),
    Zakum_Chaos(2, "자쿰", "카오스", false, true, false, 9741285),

    Horntail_easy(3, "혼테일", "이지", true, false, false, 528474),
    Horntail_normal(4, "혼테일", "노멀", true, false, false, 606666),
    Horntail_Chaos(5, "혼테일", "카오스", true, false, false, 810086),

    Pink_Bean_normal(6, "핑크빈", "노멀", true, false, false, 841544),
    Pink_Bean_Chaos(7, "핑크빈", "카오스", false, true, false, 7923110),

    Kaung(8, "카웅", "노멀", true, false, false, 748970),

    sygnus_easy(9, "시그너스", "이지", false, true, false, 5496394),
    sygnus_normal(10, "시그너스", "노멀", false, true, false, 9039130),

    populatus_easy(11, "파풀라투스", "이지", true, false, false, 410135),
    populatus_normal(12, "파풀라투스", "노멀", true, false, false, 1596506),
    populatus_Chaos(13, "파풀라투스", "카오스", false, true, false, 26725593),

    Guardian_Angel_Slime_normal(14, "가디언 엔젤 슬라임", "노멀", false, true, false, 46935874),
    Guardian_Angel_Slime_Chaos(15, "가디언 엔젤 슬라임", "카오스", false, true, false, 155492141),

    Dusk_normal(16, "더스크", "노멀", false, true, false, 71054562),
    Dusk_Chaos(17, "더스크", "카오스", false, true, false, 160173752),

    Dunkel_normal(18, "듄켈", "노멀", false, true, false, 76601412),
    Dunkel_hard(19, "듄켈", "하드", false, true, false, 168609280),

    // 루타비스
    banban_normal(100, "반반", "노멀", true, false, false, 580003),
    banban_Chaos(101, "반반", "카오스", false, true, false, 9818154),

    Pierre_normal(102, "피에르", "노멀", true, false, false, 580003),
    Pierre_Chaos(103, "피에르", "카오스", false, true, false, 9838932),

    Bloody_Queen_normal(104, "블러디퀸", "노멀", true, false, false, 580003),
    Bloody_Queen_Chaos(105, "블러디퀸", "카오스", false, true, false, 9806780),

    bellum_normal(106, "벨룸", "노멀", true, false, false, 580003),
    bellum_Chaos(107, "벨룸", "카오스", false, true, false, 12590202),

    // 군단장
    Hilla_normal(200, "힐라", "노멀", true, false, false, 479343),
    Hilla_hard(201, "힐라", "하드", false, true, false, 6936489),

    Real_Hilla_normal(202, "진 힐라", "노멀", false, true, false, 148112376),
    Real_Hilla_hard(203, "진 힐라", "하드", false, true, false, 190159452),

    Von_Leon_easy(204, "반 레온", "이지", true, false, false, 633927),
    Von_Leon_normal(205, "반 레온", "노멀", true, false, false, 873601),
    Von_Leon_hard(206, "반 레온", "하드", true, false, false, 1467984),

    Arkarium_easy(207, "아카이럼", "이지", true, false, false, 690246),
    Arkarium_normal(208, "아카이럼", "노멀", true, false, false, 1510227),

    Magnus_easy(209, "매그너스", "이지", true, false, false, 432605),
    Magnus_normal(210, "매그너스", "노멀", true, false, false, 1553066),
    Magnus_hard(211, "매그너스", "하드", false, true, false, 11579023),

    Suu_normal(212, "스우", "노멀", false, true, false, 33942566),
    Suu_hard(213, "스우", "하드", false, true, false, 118294192),

    Demian_normal(214, "데미안", "노멀", false, true, false, 35517853),
    Demian_hard(215, "데미안", "하드", false, true, false, 112480613),

    Lucid_easy(216, "루시드", "이지", false, true, false, 48058319),
    Lucid_normal(217, "루시드", "노멀", false, true, false, 57505626),
    Lucid_hard(218, "루시드", "하드", false, true, false, 131095655),

    will_easy(219, "윌", "이지", false, true, false, 52139127),
    will_normal(220, "윌", "노멀", false, true, false, 66311463),
    will_hard(221, "윌", "하드", false, true, false, 145038483),

    // 그란디스
    seren_normal(300, "선택받은 세렌", "노멀", false, true, false, 196904752),
    seren_hard(301, "선택받은 세렌", "하드", false, true, false, 267825621),

    Kalos(302, "감시자 칼로스", "노멀", false, true, false, 300000000),

    // 초월자
    Black_Mage(400, "검은 마법사", "노멀", false, false, true, 1418809857);

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

    public BossInfo getBossInfoById(Integer id){
        for(BossInfo bossInfo : BossInfo.values()){
            if(bossInfo.id.equals(id)){
                return bossInfo;
            }
        }
        return null;
    }

    public List<BossInfo> getBossInfoByName(String name){
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
