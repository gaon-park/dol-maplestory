package dol.example.domain;

import dol.example.common.info.DifficultyOfArcaneMinigame1;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_quest_of_character")
@Entity
public class TQuestOfCharacter {

    @Id
    @Column(name = "character_id")
    private Long characterId;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private TCharacter character;

    @ColumnDefault("false")
    private Boolean arcaneQuest0;

    @ColumnDefault("false")
    private Boolean arcaneQuest1;

    @ColumnDefault("false")
    private Boolean arcaneQuest2;

    @ColumnDefault("false")
    private Boolean arcaneQuest3;

    @ColumnDefault("false")
    private Boolean arcaneQuest4;

    @ColumnDefault("false")
    private Boolean arcaneQuest5;

    @ColumnDefault("false")
    private Boolean arcaneMinigame0;

    @ColumnDefault("false")
    private Boolean arcaneMinigame1;

    @ColumnDefault("null")
    private DifficultyOfArcaneMinigame1 difficultyOfArcaneMinigame1;

    @ColumnDefault("false")
    private Boolean arcaneMinigame2;

    @ColumnDefault("0")
    private Integer floorOfArcaneMinigame2;

    @ColumnDefault("false")
    private Boolean arcaneMinigame3;

    @ColumnDefault("0")
    private Integer scoreOfArcaneMinigame3;

    @ColumnDefault("false")
    private Boolean arcaneMinigame4;

    @ColumnDefault("false")
    private Boolean arcaneMinigame5;

    @ColumnDefault("false")
    private Boolean tenebris0;

    @ColumnDefault("false")
    private Boolean authenticQuest0;

    @ColumnDefault("false")
    private Boolean authenticQuest1;

    @ColumnDefault("false")
    private Boolean authenticQuest2;

    @ColumnDefault("false")
    private Boolean haven0;

    @ColumnDefault("false")
    private Boolean darkWorldTree0;

    @ColumnDefault("false")
    private Boolean kritias0;

    @ColumnDefault("false")
    private Boolean monsterPark0;

    @ColumnDefault("false")
    private Boolean union0;

    @ColumnDefault("false")
    private Boolean guild0;

    @ColumnDefault("false")
    private Boolean guild1;
}
