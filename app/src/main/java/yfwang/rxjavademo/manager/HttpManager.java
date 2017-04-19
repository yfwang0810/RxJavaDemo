package yfwang.rxjavademo.manager;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yfwang.rxjavademo.base.BaseApi;
import yfwang.rxjavademo.exception.ExceptionFun;
import yfwang.rxjavademo.exception.ResulteFun;
import yfwang.rxjavademo.exception.RetryWhenNetworkException;
import yfwang.rxjavademo.listener.HttpOnNextListener;
import yfwang.rxjavademo.listener.HttpOnNextSubListener;
import yfwang.rxjavademo.subscribers.ProgressSubscriber;

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

        Observable observable = api.getObservable(retrofit)
                //失败后retry配置
                .retryWhen(new RetryWhenNetworkException(api.getRetryCount(),
                        api.getRetryDelay(), api.getRetryIncreaseDelay()))
                /*异常处理*/
                .onErrorResumeNext(new ExceptionFun())
                /*生命周期管理*/
                //.compose(appCompatActivity.get().bindToLifecycle())
                //Note:手动设置在activity onDestroy的时候取消订阅
                .compose(appCompatActivity.get().bindUntilEvent(ActivityEvent.DESTROY))
                /*返回数据统一处理*/
                .map(new ResulteFun())
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread());

        /*ober回调，链接式返回*/
        if (onNextSublistener != null && null != onNextSublistener.get()) {
            onNextSublistener.get().OnNext(observable, api.getMethod());
        }

        /*数据String回调*/
         if (onNextListener!=null&&onNextListener.get()!=null){
             ProgressSubscriber progressSubscriber = new ProgressSubscriber(api, onNextListener, appCompatActivity);

             observable.subscribe(progressSubscriber);



         }

    }


}
