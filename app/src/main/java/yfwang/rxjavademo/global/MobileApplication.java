package yfwang.rxjavademo.global;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import java.util.ArrayList;

import yfwang.rxjavademo.utils.SharedpreferencesUtil;

/**
 * Description: 全局Application
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/8 10:59
 */
public class MobileApplication extends Application {

    private static MobileApplication mobileApplication;

    public static MobileApplication getInstance() {
        return mobileApplication;
    }

    private ArrayList<Activity> mList;

    @Override
    public void onCreate() {
        super.onCreate();
        mobileApplication = this;
        mList = new ArrayList<>();
    }

    /**
     * 添加一个activity到列表中<br/>
     * add Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    /**
     * 从列表中删除一个activity<br/>
     * remove Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        try {
            mList.remove(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否已经运行该activity<br/>
     *
     * @param activity
     * @return
     */
    public boolean containActivity(Class activity) {
        for (Activity act : mList) {
            if (act.getClass() == activity) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取已经运行的Activity<br/>
     *
     * @param activity
     * @return
     */
    public Activity getActivity(Class activity) {
        for (Activity act : mList) {
            if (act.getClass() == activity) {
                return act;
            }
        }
        return null;
    }

    /**
     * 关闭list内的每一个activity<br/>
     * close all activity
     */
    public void closeAllActivity() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
            mList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void reLogin() {

        SharedpreferencesUtil.clear(MobileApplication.getInstance());
        closeAllActivity();
        Intent intent = MobileApplication.getInstance().getPackageManager()
                .getLaunchIntentForPackage(MobileApplication.getInstance().getPackageName());
        MobileApplication.getInstance().startActivity(intent);

    }
}
