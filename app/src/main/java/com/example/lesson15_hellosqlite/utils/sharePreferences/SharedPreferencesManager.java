package com.example.lesson15_hellosqlite.utils.sharePreferences;

import android.content.Context;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/18 15:12
 * 缓存类的管理，并在app启动时单例实例化，防止频繁获取缓存对象，导内存问题
 * desc   :SharedPreferences 一个轻量级应用程序内部轻量级的存储方案,特别适合用于保存软件配置参数,
 * version: 1.0
 */
public class SharedPreferencesManager {
    private static volatile SharedPreferencesManager instance;
    private IUserPreferences userPreferences;

    public static SharedPreferencesManager getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesManager.class) {
                if (instance == null) {
                    instance = new SharedPreferencesManager();
                }
            }
        }
        return instance;
    }

    public IUserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(IUserPreferences userPreferences) {
        this.userPreferences = userPreferences ;
    }


    public void initPreferences(Context context){
        SharedPreferencesManager.getInstance().setUserPreferences(new UserPreferences(context));
    }
}
