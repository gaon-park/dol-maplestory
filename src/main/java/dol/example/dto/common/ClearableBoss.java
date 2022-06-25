package dol.example.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClearableBoss implements Serializable {
    Integer bossId;
    Integer numberOfPartyMembers;
}
