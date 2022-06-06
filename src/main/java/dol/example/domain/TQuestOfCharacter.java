package dol.example.domain;

import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "character_id", nullable = false)
    private TCharacter character;

    /**
     * 소멸의 여로 퀘스트
     */
    @Column
    private Integer toDoQuest0;

    /**
     * 츄츄아일랜드 퀘스트
     */
    @Column
    private Integer toDoQuest1;

    /**
     * 레헬른 퀘스트
     */
    @Column
    private Integer toDoQuest2;

    /**
     * 아르카나 퀘스트
     */
    @Column
    private Integer toDoQuest3;

    /**
     * 모라스 퀘스트
     */
    @Column
    private Integer toDoQuest4;

    /**
     * 에스페라 퀘스트
     */
    @Column
    private Integer toDoQuest5;

    /**
     * 소멸의 여로: 에르다 스펙트럼
     */
    @Column
    private Integer toDoMiniGame0;

    /**
     * 츄츄 아일랜드: 배고픈 무토
     */
    @Column
    private Integer toDoMiniGame1;

    /**
     * 레헬른: 드림 브레이커
     */
    @Column
    private Integer toDoMiniGame2;

    /**
     * 레헬른: 드림 브레이커 1판 클리어 층
     */
    @Column
    private Integer toDoMiniGame2floor;

    /**
     * 아르카나: 스피릿 세이비어
     */
    @Column
    private Integer toDoMiniGame3;

    /**
     * 아르카나: 스피릿 세이비어 1판 점수
     */
    @Column
    private Integer toDoMiniGame3Score;

    /**
     * 모라스: 엔하임 디펜스
     */
    @Column
    private Integer toDoMiniGame4;

    /**
     * 에스페라: 프로텍스 에스페라
     */
    @Column
    private Integer toDoMiniGame5;

}
