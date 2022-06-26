package dol.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dol.example.common.info.WorldInfo;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_union")
@Entity
public class TUnion {

    /**
     * 유니온 고유 id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 유니온 데이터의 소유자
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private TUser user;

    /**
     * 월드
     */
    @Column(name = "world_info_id")
    private WorldInfo worldInfo;

    /**
     * 유니온에 소속된 캐릭터 id
     * ex)
     * 1/2/3/4
     */
    @Column
    @JsonIgnore
    private String characterIds;

    /**
     * 유니온에 소속된 캐릭터 id를 리스트 형태로 가져옴
     *
     * @return
     */
    public List<Long> getCharacterIdList() {
        return Arrays.stream(characterIds.split("/")).map(i -> Long.parseLong(i)).toList();
    }

    /**
     * setter
     *
     * @param idList
     */
    public void setCharacterIdList(List<Long> idList) {
        this.characterIds = String.join("/", idList.stream().map(i -> Long.toString(i)).toList());
    }
}
