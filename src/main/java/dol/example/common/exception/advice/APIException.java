package dol.example.common.exception.advice;

import dol.example.common.info.ExceptionInfo;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class APIException extends RuntimeException {
    private ExceptionInfo apiExceptionInfo;
    private String detail;

    public APIException(ExceptionInfo apiExceptionInfo) {
        super(apiExceptionInfo.getMessage());
        this.apiExceptionInfo = apiExceptionInfo;
    }

    public APIException(ExceptionInfo apiExceptionInfo, String detail) {
        super(apiExceptionInfo.getMessage());
        this.apiExceptionInfo = apiExceptionInfo;
        this.detail = detail;
    }
}
