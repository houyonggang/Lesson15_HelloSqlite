package com.example.lesson15_hellosqlite.http;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/17 14:29
 * desc   :
 * version: 1.0
 */
public interface HttpCallback {
    void onSuccess(CommonJson commonJson);

    void onFailure(String error);
}
