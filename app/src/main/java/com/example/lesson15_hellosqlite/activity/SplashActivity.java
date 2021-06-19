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
import com.example.lesson15_hellosqlite.utils.sharePreferences.IUserPreferences;
import com.example.lesson15_hellosqlite.utils.sharePreferences.SharedPreferencesManager;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity {

    public static final String TAG = "SplashActivity";
    private Context mContext;
    private IUserPreferences iUserPreferences;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case 2:
                    Intent intent1 = new Intent(mContext, MainActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        mContext = SplashActivity.this;
        iUserPreferences = SharedPreferencesManager.getInstance().getUserPreferences();
        initLayout();
    }

    private void initLayout() {
        //name 为存储的xml文件名
        boolean isLogin = iUserPreferences.getLoginStatus(Session.LOGIN_STATUS);
        if (isLogin) {//true 已登录
            handler.sendEmptyMessageDelayed(2, 1000);
        } else {
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    }
}