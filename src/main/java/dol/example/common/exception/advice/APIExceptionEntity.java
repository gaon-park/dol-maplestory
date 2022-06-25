package dol.example.common.exception.advice;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class APIExceptionEntity {
    private String code;
    private String message;
    private String detail;

    @Builder
    public APIExceptionEntity(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}
