package dol.example.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_character")
@Entity
public class TCharacter {

    /**
     * 캐릭터 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 캐릭터 소유자
     */
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private TUser user;

    /**
     * 아바타 이미지 url
     */
    @Column
    private String avatarImgUrl;

    /**
     * 월드명
     */
    @Column
    private String worldName;

    /**
     * 닉네임
     */
    @Column(nullable = false)
    private String name;

    /**
     * 레벨
     */
    @Column
    private Integer lev;

    /**
     * 경험치
     */
    @Column
    private Long exp;

    /**
     * 직업 id
     */
    @Column
    private Integer jobId;

    /**
     * 인기도
     */
    @Column
    private Integer pop;

    /**
     * 전체 랭킹
     */
    @Column
    private Integer totRank;

    /**
     * 월드 랭킹
     */
    @Column
    private Integer worldRank;

    /**
     * 클리어 가능한 보스
     * ex)
     * 1_1/2_1/10_6
     * 설명:
     * boss id 1에 대해 1명 입장해서 클리어
     * boss id 2에 대해 1명 입장해서 클리어
     * boss id 10에 대해 6명 입장해서 클리어
     */
    @Column
    private String clearableBoss;


    /**
     * setter
     * @param bossClearMap Integer1: boss id, Integer2: person (입장인원)
     * @return
     */
    public void setClearableBoss(Map<Integer, Integer> bossClearMap){
        List<String> resultList = new ArrayList<>();
        resultList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Integer n1 = Integer.parseInt(o1.split("_")[0]);
                Integer n2 = Integer.parseInt(o2.split("_")[0]);
                return n1 - n2;
            }
        });

        this.clearableBoss = String.join("/", resultList);
    }

    /**
     * clearableBoss를 map 형태로 가져옴
     * @return
     */
    public Map<Integer, Integer> getClearableBossToMap(){
        List<String> bossSetList = Arrays.stream(this.clearableBoss.split("/")).toList();
        Map<Integer, Integer> already = new HashMap<>();
        for(String bossSet : bossSetList){
            Integer bossId = Integer.parseInt(bossSet.split("_")[0]);
            Integer person = Integer.parseInt(bossSet.split("_")[1]);
            already.put(bossId, person);
        }
        return already;
    }
}
