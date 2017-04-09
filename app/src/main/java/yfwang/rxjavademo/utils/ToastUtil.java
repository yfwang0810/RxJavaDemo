package yfwang.rxjavademo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Author     : yfwang
 * Date       : 2016/6/20 0020 上午 10:29
 */
public class ToastUtil {

    public static void showShortToast(Context context, String message) {
        if (context == null) return;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(Context context, String message, int gravity) {
        if (context == null) return;
        Toast mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    public static void showLongToast(Context context, String message) {
        if (context == null) return;
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(Context context, String message, int gravity) {
        if (context == null) return;
        Toast mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }
}
