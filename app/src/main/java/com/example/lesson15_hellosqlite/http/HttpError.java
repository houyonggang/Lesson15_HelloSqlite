package com.example.lesson15_hellosqlite.http;

import java.io.Serializable;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/17 14:31
 * desc   : 网络请求错误信息数据类
 * version: 1.0
 */
public class HttpError implements Serializable {
    private String message;
    private int status_code;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
