package com.example.lesson15_hellosqlite.utils.sharePreferences;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/18 16:56
 * desc   : SharedPreferences 接口管理类
 * version: 1.0
 */
public interface ISharePreferences {
    SharedPreferences.Editor putString(String key, String value);

    SharedPreferences.Editor putStringSet(String key, Set<String> values);

    SharedPreferences.Editor putInt(String key, int value);

    SharedPreferences.Editor putLong(String key, long value);

    SharedPreferences.Editor putFloat(String key, float value);

    SharedPreferences.Editor putBoolean(String key, boolean value);

    SharedPreferences.Editor remove(String key);

    SharedPreferences.Editor clear();

    boolean commit();//同步提交，可以确保代码的完整性，在保存数据后立即使用时用

    void apply();//数据同步会有延迟

    Map<String, ?> getAll();

    String getString(String key, String defValue);

    Set<String> getStringSet(String key, Set<String> defValues);

    int getInt(String key, int defValue);

    long getLong(String key, long defValue);

    float getFloat(String key, float defValue);

    boolean getBoolean(String key, boolean defValue);

    boolean contains(String key);

    void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);

    void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);
}
