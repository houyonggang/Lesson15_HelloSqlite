package com.example.lesson15_hellosqlite.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
 * 注册
 */
public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.gotoRegisterBtn)
    TextView gotoRegisterBtn;
    @BindView(R.id.iv_saoyisao)
    ImageView ivSaoyisao;
    @BindView(R.id.phone_number_text)
    EditText phoneNumberText;
    @BindView(R.id.password_text)
    EditText passwordText;
    @BindView(R.id.confirm_pwd_text)
    EditText confirmPwdText;
    @BindView(R.id.registerBtn)
    TextView registerBtn;
    @BindView(R.id.content)
    RelativeLayout content;
    /**
     * DATA
     */
    private Context mContext;
    private IUserPreferences iUserPreferences;
    private String userName;
    private String password;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);
        ButterKnife.bind(this);
        mContext = RegisterActivity.this;
        initLayout();
    }

    private void initLayout() {
        iUserPreferences = SharedPreferencesManager.getInstance().getUserPreferences();
    }

    @OnClick({R.id.back, R.id.registerBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.registerBtn://注册
                if (confirmUserInfo()) {
                    ToastUtils.showShort(mContext,"注册成功！");
                    iUserPreferences.saveUserName(Session.USER_NAME, userName);
                    iUserPreferences.savePassword(Session.USER_PASSWORD, password);
                    iUserPreferences.saveLoginStatus(Session.LOGIN_STATUS, true);
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    private boolean confirmUserInfo() {
        userName = phoneNumberText.getText().toString().trim();
        password = passwordText.getText().toString().trim();
        confirmPassword = confirmPwdText.getText().toString().trim();
        if (!TelephoneUtil.checkCellphone(userName)) {
            ToastUtils.showShort(mContext, "请输入正确的手机号");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort(mContext, "密码不能为空！");
            return false;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            ToastUtils.showShort(mContext, "确认密码不能为空！");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            ToastUtils.showShort(mContext, "两次密码不一致！");
            return false;
        }
        return true;
    }
}