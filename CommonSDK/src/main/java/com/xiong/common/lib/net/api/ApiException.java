package com.xiong.common.lib.net.api;
/**
 * Created by xionglh on 2017/6/14
 */
public class ApiException extends Exception {

    public String code;
    public String message;

    public ApiException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
