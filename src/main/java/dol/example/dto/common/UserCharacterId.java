package dol.example.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserCharacterId implements Serializable {
    private Long userId;
    private Long characterId;
}
