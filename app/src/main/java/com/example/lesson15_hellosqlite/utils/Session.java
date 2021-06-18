package com.example.lesson15_hellosqlite.utils;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/17 15:41
 * desc   : 用于保存app所使用的的全局数据
 * version: 1.0
 */
public class Session {

    public static final String SESSION_PREFERENCE_NAME = "session";
    public static final String IS_LOGIN = "is_login";//是否已登录
    public static final String USER_NAME = "user_name";//用户名
    public static final String USER_PASSWORD = "user_password";//登录密码
    private String token;
    private static Session instance = new Session();

    /**
     * 会话单实例
     *
     * @return 会话对象
     */
    public synchronized static Session getInstance() {
        return instance;
    }

    public Session() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
