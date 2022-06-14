package dol.example.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 잘못된 값을 넣으려는 경우
 * ex1. not null인데 null값을 넘겼을 경우
 * ex3. String인데 Integer를 넘겼을 경우
 */
public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }
}
