package com.example.lesson15_hellosqlite.utils.sharePreferences;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/18 16:56
 * desc   : 对接口 ISharePreferences 的二次封装
 * version: 1.0
 */
public interface IPreference {
    ISharePreferences getPreferences();

    void commit();
}
