package yfwang.rxjavademo.listener;

import yfwang.rxjavademo.exception.ApiException;

/**
 * Description: 请求成功监听
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/9 10:29
 */
public interface HttpOnNextListener {
    /**
     * 请求成功后监听
     * @param result 返回数据
     * @param method 缓存数据
     */
    void onNext(String result,String method);

    /**
     * 请求失败监听
     * @param e 请求失败异常信息、code  msg
     */
    void onFail(ApiException e);

}
