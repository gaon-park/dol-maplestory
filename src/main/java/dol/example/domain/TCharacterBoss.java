package dol.example.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_character_boss")
@Entity
@IdClass(TCharacterBossPK.class)
public class TCharacterBoss implements Serializable {

    @Id
    @Column
    private Long characterId;

    @Id
    @Column
    private Integer bossId;

    @Column
    private Integer numberOfPartyMembers;
}
