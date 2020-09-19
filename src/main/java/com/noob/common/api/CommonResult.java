package com.noob.common.api;

import lombok.Data;

@Data
public class CommonResult<T> {
    private String message;
    private Long code;
    private T data;

    public CommonResult() {
    }

    public CommonResult(String message, Long code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getMessage(),
                ResultCode.SUCCESS.getCode(), data);
    }

    public static <T> CommonResult<T> userNotFound(T data) {
        return new CommonResult<>(ResultCode.USERNOTFOUND.getMessage(),
                ResultCode.USERNOTFOUND.getCode(), data);
    }

    public static <T> CommonResult<T> failed() {
        return new CommonResult<>(ResultCode.FAILED.getMessage(),
                ResultCode.FAILED.getCode(), null);
    }
}
