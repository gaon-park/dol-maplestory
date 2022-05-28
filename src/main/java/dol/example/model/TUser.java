package dol.example.model;

import lombok.*;

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
     * 넥슨에서 가져온 id
     */
    @Column(unique = true)
    private Long accountId;

    /**
     * 대표 캐릭터 id
     */
    @Column
    private Long representativeCharacterId;

    /**
     * 유저가 육성하고 있는 캐릭터 리스트
     */
    @OneToMany(mappedBy = "user", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<TCharacter> tCharacterList = new ArrayList<>();

    /**
     * 유니온 리스트(월드당 1개)
     */
    @OneToMany(mappedBy = "user", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @Column
    private List<TUnion> tUnionList = new ArrayList<>();
}
