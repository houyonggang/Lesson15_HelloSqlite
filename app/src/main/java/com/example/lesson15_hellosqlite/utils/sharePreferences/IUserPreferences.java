package com.example.lesson15_hellosqlite.utils.sharePreferences;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/18 16:59
 * desc   : 用户信息接口类
 * version: 1.0
 */
public interface IUserPreferences {

    String getUserName(String key);

    void saveUserName(String key, String value);

    String getPassword(String key);

    void savePassword(String key, String value);

}
