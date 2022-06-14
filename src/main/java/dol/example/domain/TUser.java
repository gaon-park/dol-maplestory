package dol.example.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator. class , property = "id" )
@Entity
public class TUser implements Serializable {

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
     * 유저가 육성하고 있는 캐릭터 리스트
     */
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
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

    public void addTCharacter(TCharacter tCharacter){
        tCharacterList.add(tCharacter);
        tCharacter.setUser(this);
    }
}
