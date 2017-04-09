package yfwang.rxjavademo.common;

import yfwang.rxjavademo.utils.CodeUtil;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/8 11:21
 */
public class ApiResponse<T> {
    /**
     * 访问状态<br/>
     * server status
     */
    private int code = CodeUtil.UNKNOWN_ERROR;

    /**
     * 返回结果（可能为单个对象也可能为对象数组更有可能是特殊对象）<br/>
     * return result：it was a bean , a beanList or a special bean...
     */
    private T data;

    /**
     * 服务器返回信息<br/>
     * server message
     */
    private String message;


    /**
     * 返回结果（可能为单个对象也可能为对象数组更有可能是特殊对象）<br/>
     * return result：it was a bean , a beanList or a special bean...
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获得josn数据的状态码<br/>
     * get status from jsonData
     *
     * @return status
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
