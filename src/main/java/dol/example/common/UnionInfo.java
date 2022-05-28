package dol.example.common;

import dol.example.model.TCharacter;
import lombok.Getter;

import java.util.List;

@Getter
public enum UnionInfo {
    NOVICE_1(1, 500, 0, 9, "노비스", 1),
    NOVICE_2(2, 1000, 120, 10, "노비스", 2),
    NOVICE_3(3, 1500,140, 11, "노비스", 3),
    NOVICE_4(4, 2000, 150, 12, "노비스", 4),
    NOVICE_5(5, 2500, 160, 13, "노비스", 5),

    VETERAN_1(6, 3000, 170, 18, "베테랑", 1),
    VETERAN_2(7, 3500, 430, 19, "베테랑", 2),
    VETERAN_3(8, 4000, 450, 20, "베테랑", 3),
    VETERAN_4(9, 4500, 470, 21, "베테랑", 4),
    VETERAN_5(10, 5000, 490, 22, "베테랑", 5),

    MASTER_1(11, 5500, 510, 27, "마스터", 1),
    MASTER_2(12, 6000, 930, 28, "마스터", 2),
    MASTER_3(13, 6500, 960, 29, "마스터", 3),
    MASTER_4(14, 7000, 1000, 30, "마스터", 4),
    MASTER_5(15, 7500, 1030, 31, "마스터", 5),

    GRAND_MASTER_1(16, 8000, 1060, 36, "그랜드 마스터", 1),
    GRAND_MASTER_2(17, 8500, 2200, 37, "그랜드 마스터", 2),
    GRAND_MASTER_3(18, 9000, 2300, 38, "그랜드 마스터", 3),
    GRAND_MASTER_4(19, 9500, 2350, 39, "그랜드 마스터", 4),
    GRAND_MASTER_5(20, 10000, 2400, 40, "그랜드 마스터", 5),

    SUPREME_1(21, 10500, 3000, 41, "슈프림", 1),
    SUPREME_2(22, 11000, 3400, 42, "슈프림", 2),
    SUPREME_3(23, 11500, 3900, 43, "슈프림", 3),
    SUPREME_4(24, 12000, 4400, 44, "슈프림", 4),
    SUPREME_5(25, 12500, 5000, 45, "슈프림", 5);

    private Integer step;
    private Integer sumOfLev;
    private Integer requiredCoin;
    private Integer raidersNumber;
    private String name;
    private Integer stepLev;

    private UnionInfo(
            Integer step,
            Integer sumOfLev,
            Integer requiredCoin,
            Integer raidersNumber,
            String name,
            Integer stepLev
    ){
        this.step = step;
        this.sumOfLev = sumOfLev;
        this.requiredCoin = requiredCoin;
        this.raidersNumber = raidersNumber;
        this.name = name;
        this.stepLev = stepLev;
    }

    public UnionInfo getUnionInfo(List<TCharacter> list){
        List<Integer> level = list.stream()
                .map(o -> o.getLev())
                .filter(o -> (o >= 60))
                .toList();

        int sumOfLev = level.stream().reduce(0, Integer::sum);
        boolean formerOf5 = level.stream()
                .filter(o -> (o >= 200))
                .toList().size() > 0 ? true : false;

        // NOVICE_1
        if((level.size() >= 3 && sumOfLev >= 500) || formerOf5){
            return NOVICE_1;
        }

        UnionInfo result = NOVICE_1;
        for(UnionInfo info : UnionInfo.values()){
            // 위에서 이미 return 했을 것
            if(info == NOVICE_1)
                continue;

            if(sumOfLev < info.sumOfLev){
                result = getFrontUnionInfo(info);
                break;
            }
        }

        // SUPREME_5
        if(sumOfLev >= SUPREME_5.sumOfLev){
            result = SUPREME_5;
        }

        return result;
    }

    public UnionInfo getFrontUnionInfo(UnionInfo unionInfo){
        for(UnionInfo info : UnionInfo.values()){
            if(unionInfo.step.equals(info.step + 1)){
                return info;
            }
        }

        // unionInfo = NOVICE_1
        return null;
    }

    public UnionInfo getNextUnionInfo(UnionInfo unionInfo){
        for(UnionInfo info : UnionInfo.values()){
            if(unionInfo.step.equals(info.step - 1)){
                return info;
            }
        }

        // unionInfo = SUPREME_5
        return null;
    }
}
