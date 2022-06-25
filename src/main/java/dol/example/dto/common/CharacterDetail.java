package dol.example.dto.common;

import dol.example.domain.TCharacter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CharacterDetail extends TCharacter implements Serializable {
    private List<ClearableBoss> clearableBossList;
}
