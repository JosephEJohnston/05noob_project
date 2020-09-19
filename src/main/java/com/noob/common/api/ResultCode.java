package com.noob.common.api;

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    NOTFOUND(404, "找不到资源"),
    USERNOTFOUND(418, "找不到用户"),
    FAILED(500, "操作失败"),

    ;

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
