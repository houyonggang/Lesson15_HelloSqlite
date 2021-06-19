package com.example.lesson15_hellosqlite.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson15_hellosqlite.MainActivity;
import com.example.lesson15_hellosqlite.R;
import com.example.lesson15_hellosqlite.utils.Session;
import com.example.lesson15_hellosqlite.utils.TelephoneUtil;
import com.example.lesson15_hellosqlite.utils.ToastUtils;
import com.example.lesson15_hellosqlite.utils.sharePreferences.IUserPreferences;
import com.example.lesson15_hellosqlite.utils.sharePreferences.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * VIEW
     */
    @BindView(R.id.loginName)
    EditText loginName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.loginBtn)
    TextView loginBtn;
    @BindView(R.id.mima_layout)
    LinearLayout mimaLayout;
    @BindView(R.id.wxLogin)
    LinearLayout wxLogin;
    @BindView(R.id.orderDetailIcon)
    ImageView orderDetailIcon;
    @BindView(R.id.orderDetailOrderStatus)
    TextView orderDetailOrderStatus;
    @BindView(R.id.sinaLogin)
    LinearLayout sinaLogin;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.recieverAddress)
    TextView recieverAddress;
    @BindView(R.id.QQlogin)
    LinearLayout QQlogin;
    @BindView(R.id.recieverName)
    TextView recieverName;
    @BindView(R.id.taobaoLogin)
    LinearLayout taobaoLogin;
    @BindView(R.id.gotoRegisterBtn2)
    TextView gotoRegisterBtn2;
    /**
     * DATA
     */
    private Context mContext;
    private IUserPreferences iUserPreferences;
    private String getUserName;
    private String getUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        mContext = LoginActivity.this;
        initLayout();
    }

    private void initLayout() {
        iUserPreferences = SharedPreferencesManager.getInstance().getUserPreferences();
        getUserName = iUserPreferences.getUserName(Session.USER_NAME);
        getUserPassword = iUserPreferences.getPassword(Session.USER_PASSWORD);
    }


    @OnClick({R.id.loginBtn, R.id.wxLogin, R.id.sinaLogin, R.id.QQlogin, R.id.taobaoLogin, R.id.gotoRegisterBtn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn://密码登录
                if (!TelephoneUtil.checkCellphone(loginName.getText().toString().trim())) {
                    ToastUtils.showShort(mContext, "请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    ToastUtils.showShort(mContext, "密码不能为空！");
                    return;
                }
                if (getUserName.equals(loginName.getText().toString().trim()) && getUserPassword.equals(password.getText().toString().trim())) {
                    startActivity(new Intent(mContext, MainActivity.class));
                    iUserPreferences.saveLoginStatus(Session.LOGIN_STATUS, true);
                    finish();
                } else {
                    ToastUtils.showShort(mContext, "帐号或密码错误，请重新输入");
                }
                break;
            case R.id.wxLogin://weixin
                break;
            case R.id.sinaLogin://weibo
                break;
            case R.id.QQlogin://qq
                break;
            case R.id.taobaoLogin://taobao
                break;
            case R.id.gotoRegisterBtn2://zhuce
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
        }
    }
}