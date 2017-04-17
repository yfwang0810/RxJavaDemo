package yfwang.rxjavademo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.anye.greendao.gen.DaoMaster;

import yfwang.rxjavademo.global.MobileApplication;

/**
 * Description: 缓存数据 数据库操作类
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/17 10:30
 */
public class CookieDbUtil {

    private final Context mContext;
    private static CookieDbUtil db;
    private DaoMaster.DevOpenHelper openHelper;


    public CookieDbUtil(){
        mContext = MobileApplication.getInstance();
        openHelper = new DaoMaster.DevOpenHelper(mContext,"db");
  }


    /**
     * 获取单例
     * @return
     */
    public static CookieDbUtil getInstance() {
        if (db == null) {
            synchronized (CookieDbUtil.class) {
                if (db == null) {
                    db = new CookieDbUtil();
                }
            }
        }
        return db;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(mContext,"db");
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(mContext,"db");
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }





}
