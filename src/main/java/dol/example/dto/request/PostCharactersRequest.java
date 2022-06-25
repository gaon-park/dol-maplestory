package dol.example.dto.request;

import dol.example.dto.common.CharacterDetail;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PostCharactersRequest implements Serializable {
    private Long userId;
    private List<CharacterDetail> characterDetailList;
}
