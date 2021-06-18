package com.example.lesson15_hellosqlite.utils.sharePreferences;

import android.content.Context;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/18 17:41
 * desc   : 保存用户信息并提交
 * version: 1.0
 */
public class UserPreferences extends DefaultPreference implements IUserPreferences{
    private static final String TAG ="UserPreferences";

    public UserPreferences(Context context) {
        super(context);
    }

    @Override
    public String getPrefenceName() {
        return TAG;
    }

    @Override
    public String getUserName(String key) {
        return getPreferences().getString(key,null);
    }

    @Override
    public void saveUserName(String key, String value) {
        getPreferences().putString(key,value).commit();
    }

    @Override
    public String getPassword(String key) {
        return getPreferences().getString(key,null);
    }

    @Override
    public void savePassword(String key, String value) {
        getPreferences().putString(key,value).commit();
    }
}
