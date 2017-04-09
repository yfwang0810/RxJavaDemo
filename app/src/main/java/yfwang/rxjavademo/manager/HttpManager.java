package yfwang.rxjavademo.manager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
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
        //手动创建一个OkHttpClient并设置超时时间缓存等设置
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(api.getConnectionTime(), TimeUnit.SECONDS);

         /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder().client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(api.getBaseUrl()).build();

//        Observable observable = api.getObservable(retrofit)
//                //失败后retry配置
//                .retryWhen(new RetryWhenNetworkException(api.getRetryCount(),
//                        api.getRetryDelay(),api.getRetryIncreaseDelay()))
//                .onErrorResumeNext();


    }


}
