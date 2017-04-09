package yfwang.rxjavademo.base;

/**
 * Description: 请求数据统一封装
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/9 15:02
 */
public class BaseApi {

    /*超时时间-默认6秒*/
    private int connectionTime = 6;

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }


}
