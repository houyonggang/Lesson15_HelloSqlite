package com.example.lesson15_hellosqlite.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * author : LaAmo
 * e-mail : 15991382841@163.com
 * date   : 2021/6/19 16:41
 * desc   : 手机号验证
 * version: 1.0
 */
public class TelephoneUtil {
    /**
     * 验证手机号码
     * 移动号码段:139 138 137 136 135 134 178 170 188 187 183 182 159 158 157 152 150 147 198
     * 联通号码段:130、131、132、136、185、186、145，166
     * 电信号码段:133、153、180、189
     * @param cellphone
     * @return
     */
    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[0-9])|(15([0-9]))|(16[0-9])|(17([0-3]|[5-9]))|(18[0-9])|(19[0-9]))\\d{8}$";
        return cellphone.matches(regex);
    }

    /**
     * 验证固话号码
     * @param telephone
     * @return
     */
    public static boolean checkTelephone(String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return telephone.matches(regex);
    }

    //跳转到拨号界面，同时传递电话号码
    public static void playTelephone(Context context, String phone) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        dialIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(dialIntent);
    }
}
