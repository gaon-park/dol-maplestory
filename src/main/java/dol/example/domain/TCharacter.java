package dol.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import dol.example.common.JobInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "t_character")
@Entity
@JsonRootName("t_character")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TCharacter implements Serializable {

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
    private String characterName;

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

    @OneToOne(mappedBy = "character")
    @PrimaryKeyJoinColumn
    private TQuestOfCharacter quest;

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
        if(this.clearableBoss == null) return null;
        List<String> bossSetList = Arrays.stream(this.clearableBoss.split("/")).toList();
        Map<Integer, Integer> bossMap = new HashMap<>();
        for(String bossSet : bossSetList){
            Integer bossId = Integer.parseInt(bossSet.split("_")[0]);
            Integer person = Integer.parseInt(bossSet.split("_")[1]);
            bossMap.put(bossId, person);
        }
        return bossMap;
    }


    /**
     * 기본 생성자
     */
    public TCharacter(){ }

    /**
     * 역직렬화 전용 생성자
     * @param avatarImgUrl
     * @param worldName
     * @param characterName
     * @param lev
     * @param exp
     * @param jobDetail
     * @param pop
     * @param totRank
     * @param worldRank
     */
    @JsonCreator
    public TCharacter(
            @JsonProperty("AvatarImgURL") String avatarImgUrl,
            @JsonProperty("WorldName") String worldName,
            @JsonProperty("CharacterName") String characterName,
            @JsonProperty("Lev") Integer lev,
            @JsonProperty("Exp") Long exp,
            @JsonProperty("JobDetail") String jobDetail,
            @JsonProperty("Pop") Integer pop,
            @JsonProperty("TotRank") Integer totRank,
            @JsonProperty("WorldRank") Integer worldRank
    ){
        this.avatarImgUrl = avatarImgUrl;
        this.worldName = worldName;
        this.characterName = characterName;
        this.lev = lev;
        this.exp = exp;
        this.jobId = JobInfo.getJobInfoByJobDetail(jobDetail).getId();
        this.pop = pop;
        this.totRank = totRank;
        this.worldRank = worldRank;
    }
}
