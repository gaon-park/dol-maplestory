package dol.example.dto.response;

import dol.example.common.info.JobInfo;
import dol.example.domain.TCharacter;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UnionResponse implements Serializable {
    private String worldName;
    private Integer sumOfLev;
    private String step;
    private List<TCharacter> characterList;
    private List<JobInfo> existentJobList;
    private List<JobInfo> nonexistentJobList;
}
