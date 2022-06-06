package dol.example.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum TodoInfo {

    ARCANE_QUEST0(0, "소멸의 여로(일일 퀘스트)", true, false),
    ARCANE_MINIGAME0(1, "소멸의 여로(에르다 스펙트럼)", true, false),

    ARCANE_QUEST1(10, "츄츄 아일랜드(일일 퀘스트)", true, false),
    ARCANE_MINIGAME1(11, "츄츄 아일랜드(배고픈 무토)", true, false),

    ARCANE_QUEST2(20, "레헬른(일일 퀘스트)", true, false),
    ARCANE_MINIGAME2(21, "레헬른(드림 브레이커)", true, false),

    ARCANE_QUEST3(30, "아르카나(일일 퀘스트)", true, false),
    ARCANE_MINIGAME3(31, "아르카나(스피릿 세이비어)", true, false),

    ARCANE_QUEST4(40, "모라스(일일 퀘스트)", true, false),
    ARCANE_MINIGAME4(41, "모라스(엔하임 디펜스)", true, false),

    ARCANE_QUEST5(50, "에스페라(일일 퀘스트)", true, false),
    ARCANE_MINIGAME5(51, "에스페라(프로텍트 에스페라)", true, false),

    TENEBRIS0(60, "테네브리스(일일 퀘스트)", true, false),

    AUTHENTIC_QUEST0(100, "세르니움(일일 퀘스트)", true, false),

    AUTHENTIC_QUEST1(110, "호텔 아르크스(일일 퀘스트)", true, false),

    HAVEN0(1000, "기계무덤(주간 퀘스트)", false, true),
    DARK_WORLD_TREE0(1001, "타락한 세계수(주간 퀘스트)", false, true),

    KRITIAS0(1010, "크리티아스(주간 퀘스트)", false, true),

    MONSTER_PARK0(1020, "몬스터 파크", true, false),

    UNION0(1030, "유니온(일일 퀘스트)", true, false),

    GUILD0(2000, "길드 컨텐츠(수로)", false, true),
    GUILD1(2001, "길드 컨텐츠(플래그)", false, true),
    ;

    private Integer id;
    private String name;
    private Boolean isDaily;
    private Boolean isWeekly;

    private TodoInfo(
            Integer id,
            String name,
            Boolean isDaily,
            Boolean isWeekly
    ){
        this.id = id;
        this.name = name;
        this.isDaily = isDaily;
        this.isWeekly = isWeekly;
    }

    /**
     * 아케인 심볼과 관련된 메할일 리스트를 취득
     * @return
     */
    static public List<TodoInfo> getTodoInfoListForArcaneSymbol(){
        List<TodoInfo> result = new ArrayList<>();

        // 각 지역 아케인 심볼 몹잡기 퀘스트
        result.add(ARCANE_QUEST0);
        result.add(ARCANE_QUEST1);
        result.add(ARCANE_QUEST2);
        result.add(ARCANE_QUEST3);
        result.add(ARCANE_QUEST4);
        result.add(ARCANE_QUEST5);

        // 각 지역 아케인 심볼 미니 게임
        result.add(ARCANE_MINIGAME0);
        result.add(ARCANE_MINIGAME1);
        result.add(ARCANE_MINIGAME2);
        result.add(ARCANE_MINIGAME3);
        result.add(ARCANE_MINIGAME4);
        result.add(ARCANE_MINIGAME5);

        return result;
    }

    static public List<TodoInfo> getTodoInfoListForAuthenticSymbol(){
        List<TodoInfo> result = new ArrayList<>();

        // 각 지역 어센틱 심볼 몹잡기 퀘스트
        result.add(AUTHENTIC_QUEST0);
        result.add(AUTHENTIC_QUEST1);

        return result;
    }
}
