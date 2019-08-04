package com.xihua.wx.weixiao.bean;

public class ApiResult<T> {
    private String message;
    private T data;
    private int code;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ApiResult success() {
        ApiResult result = new ApiResult();
        result.setData(null);
        result.setMessage("");
        result.setCode(ErrorStatus.SUCCESS.getErrorCode());
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setData(data);
        result.setMessage("");
        result.setCode(ErrorStatus.SUCCESS.getErrorCode());
        return result;
    }

    public static <T> ApiResult<T> success(T data, String message) {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(data);
        result.setMessage(message);
        result.setCode(ErrorStatus.SUCCESS.getErrorCode());
        return result;
    }


    public static ApiResult failure(String message) {
        ApiResult result = new ApiResult<>();
        result.setData(null);
        result.setMessage(message);
        result.setCode(ErrorStatus.UNKNOWN_ERROR.getErrorCode());
        return result;
    }

    public static ApiResult failure(ErrorStatus errorStatus) {
        ApiResult result = new ApiResult();
        result.setData(null);
        result.setMessage(errorStatus.getUserMessage());
        result.setCode(errorStatus.getErrorCode());
        return result;
    }

    public static ApiResult failure(String message, ErrorStatus errorStatus) {
        ApiResult result = new ApiResult<>();
        result.setData(null);
        result.setMessage(message);
        result.setCode(errorStatus.getErrorCode());
        return result;
    }

    public static <T> ApiResult<T> failure(T data, String message) {
        ApiResult<T> result = new ApiResult<T>();
        result.setData(data);
        result.setMessage(message);
        result.setCode(ErrorStatus.DEVICE_CONFIG_PART_UPDATE.getErrorCode());
        return result;
    }
}

