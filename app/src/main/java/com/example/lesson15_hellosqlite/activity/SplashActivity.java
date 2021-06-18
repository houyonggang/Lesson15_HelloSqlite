package com.example.lesson15_hellosqlite.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.lesson15_hellosqlite.MainActivity;
import com.example.lesson15_hellosqlite.R;
import com.example.lesson15_hellosqlite.utils.Session;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity";
    private Context mContext;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    startToActivity(LoginActivity.class);
                    break;
                case 2:
                    startToActivity(MainActivity.class);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        mContext = SplashActivity.this;
        initLayout();
    }

    private void initLayout() {
        //name 为存储的xml文件名
        final SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);//创建一个SharedPreferences对象
        boolean isLogin = sharedPreferences.getBoolean(Session.IS_LOGIN, false);
        if (isLogin) {//true 已登录
            handler.sendEmptyMessageDelayed(2, 1000);
        } else {
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    }

    private void startToActivity(Class activity) {
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
        finish();
    }
}