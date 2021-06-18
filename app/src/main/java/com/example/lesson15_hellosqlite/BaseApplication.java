package com.example.lesson15_hellosqlite;

import android.app.Application;

import com.example.lesson15_hellosqlite.http.HttpUtils;
import com.example.lesson15_hellosqlite.utils.sharePreferences.SharedPreferencesManager;
import com.taobao.sophix.SophixManager;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/5/25 14:46
 * desc   :
 * version: 1.0
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SophixManager.getInstance().queryAndLoadNewPatch();
        SharedPreferencesManager.getInstance().initPreferences(this);
    }

}
