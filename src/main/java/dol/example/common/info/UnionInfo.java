package dol.example.common.info;

import dol.example.domain.TCharacter;
import lombok.Getter;

import java.util.List;

@Getter
public enum UnionInfo {
    NOVICE1(1, 500, 0, 9, "노비스", 1),
    NOVICE2(2, 1000, 120, 10, "노비스", 2),
    NOVICE3(3, 1500,140, 11, "노비스", 3),
    NOVICE4(4, 2000, 150, 12, "노비스", 4),
    NOVICE5(5, 2500, 160, 13, "노비스", 5),

    VETERAN1(6, 3000, 170, 18, "베테랑", 1),
    VETERAN2(7, 3500, 430, 19, "베테랑", 2),
    VETERAN3(8, 4000, 450, 20, "베테랑", 3),
    VETERAN4(9, 4500, 470, 21, "베테랑", 4),
    VETERAN5(10, 5000, 490, 22, "베테랑", 5),

    MASTER1(11, 5500, 510, 27, "마스터", 1),
    MASTER2(12, 6000, 930, 28, "마스터", 2),
    MASTER3(13, 6500, 960, 29, "마스터", 3),
    MASTER4(14, 7000, 1000, 30, "마스터", 4),
    MASTER5(15, 7500, 1030, 31, "마스터", 5),

    GRAND_MASTER1(16, 8000, 1060, 36, "그랜드 마스터", 1),
    GRAND_MASTER2(17, 8500, 2200, 37, "그랜드 마스터", 2),
    GRAND_MASTER3(18, 9000, 2300, 38, "그랜드 마스터", 3),
    GRAND_MASTER4(19, 9500, 2350, 39, "그랜드 마스터", 4),
    GRAND_MASTER5(20, 10000, 2400, 40, "그랜드 마스터", 5),

    SUPREME1(21, 10500, 3000, 41, "슈프림", 1),
    SUPREME2(22, 11000, 3400, 42, "슈프림", 2),
    SUPREME3(23, 11500, 3900, 43, "슈프림", 3),
    SUPREME4(24, 12000, 4400, 44, "슈프림", 4),
    SUPREME5(25, 12500, 5000, 45, "슈프림", 5);

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
            return NOVICE1;
        }

        UnionInfo result = NOVICE1;
        for(UnionInfo info : UnionInfo.values()){
            // 위에서 이미 return 했을 것
            if(info == NOVICE1)
                continue;

            if(sumOfLev < info.sumOfLev){
                result = getFrontUnionInfo(info);
                break;
            }
        }

        // SUPREME5
        if(sumOfLev >= SUPREME5.sumOfLev){
            result = SUPREME5;
        }

        return result;
    }

    public UnionInfo getFrontUnionInfo(UnionInfo unionInfo){
        for(UnionInfo info : UnionInfo.values()){
            if(unionInfo.step.equals(info.step + 1)){
                return info;
            }
        }

        // unionInfo = NOVICE1
        return null;
    }

    public UnionInfo getNextUnionInfo(UnionInfo unionInfo){
        for(UnionInfo info : UnionInfo.values()){
            if(unionInfo.step.equals(info.step - 1)){
                return info;
            }
        }

        // unionInfo = SUPREME5
        return null;
    }
}
