package dol.example.dto.common;

import dol.example.common.info.JobInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class SimpleCharacter implements Serializable {
    private Long id;
    private String avatarImgUrl;
    private String characterName;
    private Integer lev;
    private JobInfo job;
}
