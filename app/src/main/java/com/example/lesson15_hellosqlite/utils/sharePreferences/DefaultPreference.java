package com.example.lesson15_hellosqlite.utils.sharePreferences;

import android.content.Context;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/18 17:02
 * desc   : 接口实现类
 * version: 1.0
 */
public class DefaultPreference implements IPreference{
    private String TAG = "DefaultPreference";
    private ISharePreferences sharePreferences;

    public DefaultPreference(Context context){
        sharePreferences = new AnchePreference(context,getPrefenceName());
    }

    public String getPrefenceName() {
        return TAG;
    }

    @Override
    public ISharePreferences getPreferences() {
        return sharePreferences;
    }

    @Override
    public void commit() {
        getPreferences().commit();
    }
}
