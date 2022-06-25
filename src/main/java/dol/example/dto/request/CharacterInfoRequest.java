package dol.example.dto.request;

import dol.example.domain.TCharacter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CharacterInfoRequest extends TCharacter implements Serializable {
    private List<ClearableBoss> clearableBossList;

    @Getter
    @Setter
    public static class ClearableBoss {
        Integer bossId;
        Integer numberOfPartyMembers;
    }
}
