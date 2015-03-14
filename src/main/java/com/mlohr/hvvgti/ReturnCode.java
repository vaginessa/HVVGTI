package com.mlohr.hvvgti;

public enum ReturnCode {
    OK("OK"),
    ERROR_CN_TOO_MANY("ERROR_CN_TOO_MANY"),
    ERROR_COMM("ERROR_COMM"),
    ERROR_ROUTE("ERROR_ROUTE"),
    ERROR_TEXT("ERROR_TEXT");

    private String code;

    ReturnCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
