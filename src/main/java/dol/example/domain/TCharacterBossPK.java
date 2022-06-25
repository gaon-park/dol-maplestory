package dol.example.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TCharacterBossPK implements Serializable {
    private Long characterId;
    private Integer bossId;
}
