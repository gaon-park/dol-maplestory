package dol.example.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dol.example.common.info.JobInfo;
import dol.example.common.info.WorldInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "t_character",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "characterName"})
        }
)
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@ToString
public class TCharacter implements Serializable {

    /**
     * 캐릭터 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private TUser user;

    /**
     * 아바타 이미지 url
     */
    @Lob
    @Column
    private String avatarImgUrl;

    /**
     * 월드
     */
    @Column(name = "world_info_id")
    @JsonProperty("worldName")
    private WorldInfo worldInfo;

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
    private JobInfo job;

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

    @Column
    private String guild;

    @OneToOne(mappedBy = "character")
    @PrimaryKeyJoinColumn
    private TQuestOfCharacter quest;
}
