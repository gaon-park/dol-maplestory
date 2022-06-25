package dol.example.common.exception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class APIExceptionAdvice {

    @ExceptionHandler({APIException.class})
    public ResponseEntity<APIExceptionEntity> exceptionHandler(HttpServletRequest request, final APIException e) {
        return ResponseEntity
                .status(e.getApiExceptionInfo().getStatus())
                .body(APIExceptionEntity.builder()
                        .code(e.getApiExceptionInfo().getCode())
                        .message(e.getApiExceptionInfo().getMessage())
                        .detail(e.getDetail())
                        .build());
    }
}
