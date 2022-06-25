package dol.example.domain;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_boss")
@Entity
public class TBoss {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String difficulty;

    @Column
    private Boolean isDailyBoss;

    @Column
    private Boolean isWeeklyBoss;

    @Column
    private Boolean isMonthlyBoss;

    @Column(nullable = false)
    private Integer stonePrice;
}
