package dol.example.domain;

import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
@Entity
public class TUser {

    /**
     * dol-maplestory 유저 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * login email
     * login은 email과 대표캐릭터 닉네임으로
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * 대표 캐릭터 닉네임
     */
    @Column(nullable = false)
    private String representativeCharacterName;

    /**
     * 대표 캐릭터 id(인게임 닉네임 변경이 가능하기 때문에 검색용)
     */
    @Column
    private Long representativeCharacterId;

    /**
     * 유저가 육성하고 있는 캐릭터 리스트
     */
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<TCharacter> tCharacterList = new ArrayList<>();

    /**
     * 유니온 리스트(월드당 1개)
     */
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @Column
    private List<TUnion> tUnionList = new ArrayList<>();
}
