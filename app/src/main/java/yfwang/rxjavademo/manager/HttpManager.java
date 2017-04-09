package yfwang.rxjavademo.manager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;

import yfwang.rxjavademo.base.BaseApi;
import yfwang.rxjavademo.listener.HttpOnNextListener;
import yfwang.rxjavademo.listener.HttpOnNextSubListener;

/**
 * Description: 网络请求管理类
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/9 10:27
 */
public class HttpManager {
    private SoftReference<HttpOnNextListener> onNextListener;
    private SoftReference<HttpOnNextSubListener> onNextSublistener;
    private SoftReference<RxAppCompatActivity> appCompatActivity;

    public HttpManager(HttpOnNextListener listener, RxAppCompatActivity appCompatActivity) {
        this.onNextListener = new SoftReference<>(listener);
        this.appCompatActivity = new SoftReference<>(appCompatActivity);

    }

    public HttpManager(HttpOnNextSubListener listener, RxAppCompatActivity appCompatActivity) {
        this.onNextSublistener = new SoftReference<>(listener);
        this.appCompatActivity = new SoftReference<>(appCompatActivity);
    }

    /**
     * 处理http请求
     */
    public void doRequest(BaseApi api) {



    }


}
