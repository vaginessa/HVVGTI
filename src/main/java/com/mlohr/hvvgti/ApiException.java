package com.mlohr.hvvgti;

public class ApiException extends Exception {

    private Integer httpStatusCode;

    public ApiException() {
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public ApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ApiException(String detailMessage, int httpStatusCode, Throwable throwable) {
        super(detailMessage, throwable);
        this.httpStatusCode = httpStatusCode;
    }

    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
