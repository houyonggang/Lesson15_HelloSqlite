package com.example.lesson15_hellosqlite.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/19 16:47
 * desc   : Toast 封装类
 * version: 1.0
 */
public class ToastUtils {
    private static Toast mToast = null;//全局唯一的Toast

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    @SuppressLint("ShowToast")
    public static void showShort(Context context,  CharSequence message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    @SuppressLint("ShowToast")
    public static void showLong(Context context, CharSequence message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }
}
