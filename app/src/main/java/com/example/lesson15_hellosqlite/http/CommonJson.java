package com.example.lesson15_hellosqlite.http;

import java.io.Serializable;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/17 14:30
 * desc   : 网络请求成功返回数据类
 * version: 1.0
 */
public class CommonJson implements Serializable {
    private String data;
    private HttpError error;
    private String token;
    private String msg;
    private String code;
    private String info;
    private String show;
    private boolean state;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HttpError getError() {
        return error;
    }

    public void setError(HttpError error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

