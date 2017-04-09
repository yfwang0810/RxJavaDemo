package yfwang.rxjavademo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import yfwang.rxjavademo.global.MobileApplication;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Author     : yfwang
 * Date       : 2016/5/4 0004 上午 10:04
 */
public class SharedpreferencesUtil {
    private final static String SP_NAME = "config";
    private static SharedPreferences sp;


    public static void clear(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().clear().apply();
    }

    /**
     * 保存String值操作
     * key  value
     */
    public static void saveString(Context context, String key, String value) {
        //config : 是保存boolean值的文件的名称
        //mode : 权限
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取保存的String值
     *
     * @param context
     * @param key      ： 保存信息的key
     * @param defValue : 缺省默认的值
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    public static void saveToken(String tokenKey, Object token) {
        SharedPreferences preferences = MobileApplication.getInstance().getSharedPreferences("config",
                Context.MODE_PRIVATE);
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(token);
            // 将字节流编码成base64的字符窜
            String token_Base64 = new String(Base64.decode(baos
                    .toByteArray(),0));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(tokenKey, token_Base64);

            editor.apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object readToken(String tokenKey) {
        Object token = null;
        SharedPreferences preferences =  MobileApplication.getInstance().getSharedPreferences("config",
                Context.MODE_PRIVATE);
        String productBase64 = preferences.getString(tokenKey, "");

        //读取字节
        byte[] base64 = Base64.decode(productBase64.getBytes(),0);

        //封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            //再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                //读取对象
                token = bis.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }


}
