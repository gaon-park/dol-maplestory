package dol.example.dto.response;

import dol.example.domain.TCharacter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SearchCharacterListResponse implements Serializable {
    private List<TCharacter> characterList;
    private List<String> notFoundCharacterList;
}
