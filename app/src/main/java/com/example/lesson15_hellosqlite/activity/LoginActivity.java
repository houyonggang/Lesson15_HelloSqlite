package com.example.lesson15_hellosqlite.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson15_hellosqlite.R;
import com.example.lesson15_hellosqlite.utils.Session;
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
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.yanzhengmatv)
    TextView yanzhengmatv;
    @BindView(R.id.Right_line)
    ImageView RightLine;
    @BindView(R.id.yanzhengmalogin)
    RelativeLayout yanzhengmalogin;
    @BindView(R.id.mimatv)
    TextView mimatv;
    @BindView(R.id.left_line)
    ImageView leftLine;
    @BindView(R.id.mimalogin)
    RelativeLayout mimalogin;
    @BindView(R.id.yanzhengmaloginName)
    EditText yanzhengmaloginName;
    @BindView(R.id.yanzhengmavc)
    EditText yanzhengmavc;
    @BindView(R.id.yanzhengmavcBtn)
    TextView yanzhengmavcBtn;
    @BindView(R.id.yanzhengmaloginBtn)
    TextView yanzhengmaloginBtn;
    @BindView(R.id.yanzhengma)
    LinearLayout yanzhengma;
    @BindView(R.id.loginName)
    EditText loginName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.autoLogin)
    CheckBox autoLogin;
    @BindView(R.id.forgetPW)
    TextView forgetPW;
    @BindView(R.id.loginBtn)
    TextView loginBtn;
    @BindView(R.id.mima)
    LinearLayout mima;
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


    @OnClick({R.id.yanzhengmalogin, R.id.mimalogin, R.id.yanzhengmavcBtn, R.id.yanzhengmaloginBtn, R.id.loginBtn, R.id.wxLogin, R.id.sinaLogin, R.id.QQlogin, R.id.taobaoLogin, R.id.gotoRegisterBtn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yanzhengmalogin:
                break;
            case R.id.mimalogin:
                break;
            case R.id.yanzhengmavcBtn://获取验证码
                break;
            case R.id.yanzhengmaloginBtn://验证码登录btn
                break;
            case R.id.loginBtn://密码登录
                //注册时使用
//                iUserPreferences.saveUserName(Session.USER_NAME,loginName.getText().toString().trim());
//                iUserPreferences.savePassword(Session.USER_PASSWORD,password.getText().toString().trim());

                if (!getUserName.equals(loginName.getText().toString().trim())) {
                    return;
                }
                if (getUserPassword.equals(password.getText().toString().trim())) {
                    return;
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
                break;
        }
    }
}