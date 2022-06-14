package dol.example.common.info;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionInfo {
    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "E0001", "Not Found"),
    INVALID_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "E0002", "Invalid Request"),
    ALREADY_EXIST_EXCEPTION(HttpStatus.CONFLICT, "E0003", "Already Exist"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionInfo(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
