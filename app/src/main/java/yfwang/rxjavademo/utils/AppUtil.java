package yfwang.rxjavademo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Description: 全局工具类
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/12 13:38
 */
public class AppUtil {

    private static ConnectivityManager mConnectivityManager;

    /**
     * 获取手机联网状态
     *
     * @param mContext
     * @return
     */

    public static boolean isNetworkAvailable(Context mContext) {
        try {
            mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mConnectivityManager != null) {
                NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


}
