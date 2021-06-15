package com.example.lesson15_hellosqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/5/6 16:50
 * desc   : 数据库操作辅助类
 * version: 1.0
 */
public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //辅助类建立时运行该方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table pic (_id integer primary key autoincrement not null , fileName varchar, description varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
