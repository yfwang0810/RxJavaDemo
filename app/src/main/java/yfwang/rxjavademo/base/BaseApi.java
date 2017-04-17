package yfwang.rxjavademo.base;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Description: 请求数据统一封装
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/9 15:02
 */
public abstract class BaseApi {


    /*是否能取消加载框*/
    private boolean cancel = false;
    /*是否显示加载框*/
    private boolean showProgress = true;
    /*超时时间-默认6秒*/
    private int connectionTime = 6;

    /*请求的基础url*/
    private String baseUrl = "www.baidu.com";

    /* retry次数*/
    private int retryCount = 1;
    /*retry延迟*/
    private long retryDelay = 100;
    /*retry叠加延迟*/
    private long retryIncreaseDelay = 100;
    /*时候缓存数据*/
    private boolean isCache;
    /*方法-如果需要缓存必须设置这个参数；不需要不用設置*/
    private String method = "";


    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }



    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public long getRetryIncreaseDelay() {
        return retryIncreaseDelay;
    }

    public void setRetryIncreaseDelay(long retryIncreaseDelay) {
        this.retryIncreaseDelay = retryIncreaseDelay;
    }

    public String getUrl(){
        return getBaseUrl()+getMethod();
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);

}
