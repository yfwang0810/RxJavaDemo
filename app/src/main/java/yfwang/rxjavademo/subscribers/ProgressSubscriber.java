package yfwang.rxjavademo.subscribers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.lang.ref.SoftReference;

import rx.Subscriber;
import yfwang.rxjavademo.base.BaseApi;
import yfwang.rxjavademo.global.MobileApplication;
import yfwang.rxjavademo.listener.HttpOnNextListener;
import yfwang.rxjavademo.utils.AppUtil;

/**
 * Description: 自定义progress
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/12 9:51
 */
public class ProgressSubscriber<T> extends Subscriber<T> {

    /*是否弹框*/
    private boolean showPorgress = true;

    //    回调接口
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
    //    软引用反正内存泄露
    private SoftReference<Context> mActivity;
    //    加载框可自己定义
    private ProgressDialog progressDialog;
    /*请求数据*/
    private BaseApi api;


    /**
     * 构造
     *
     * @param api
     */
    public ProgressSubscriber(BaseApi api, SoftReference<HttpOnNextListener> listenerSoftReference, SoftReference<Context>
            mActivity) {
        this.api = api;
        this.mSubscriberOnNextListener = listenerSoftReference;
        this.mActivity = mActivity;
        setShowPorgress(api.isShowProgress());
        if (api.isShowProgress()) {
            initProgressDialog(api.isCancel());
        }
    }
    /**
     * 初始化加载框
     */
    private void initProgressDialog(boolean cancel) {

        Context context = mActivity.get();
        if (progressDialog == null && context != null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(cancel);
            if (cancel) {
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        onCancelProgress();
                    }
                });
            }
        }


    }

    /**
     * 显示加载框
     */
    public void showProgressDialog() {

        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }

    }


    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!isShowPorgress()) return;
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }



    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
        /*缓存并且有网*/
        if (api.isCache() && AppUtil.isNetworkAvailable(MobileApplication.getInstance())) {
             /*获取缓存数据*/
//            CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
//            if (cookieResulte != null) {
//                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
//                if (time < api.getCookieNetWorkTime()) {
//                    if (mSubscriberOnNextListener.get() != null) {
//                        mSubscriberOnNextListener.get().onNext(cookieResulte.getResulte(), api.getMethod());
//                    }
//                    onCompleted();
//                    unsubscribe();
//                }
//            }
        }
    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {


    }


    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }


    public boolean isShowPorgress() {
        return showPorgress;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showPorgress
     */
    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }
}
