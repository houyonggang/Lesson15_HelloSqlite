package com.example.lesson15_hellosqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lesson15_hellosqlite.R;
import com.example.lesson15_hellosqlite.utils.Session;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        mContext = SplashActivity.this;
        initLayout();
    }

    private void initLayout(){
        final SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);//创建一个SharedPreferences对象
    }
}