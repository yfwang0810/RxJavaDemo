package yfwang.rxjavademo.exception;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Description: 异常解析工厂 输出自定义ApiException
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/10 10:22
 */
public class FactoryException {
    private static final String HttpException_MSG = "网络错误";
    private static final String ConnectException_MSG = "连接失败";
    private static final String JSONException_MSG = "fastjeson解析失败";
    private static final String UnknownHostException_MSG = "无法解析该域名";
    /**
     * 解析异常
     *
     * @param e
     * @return
     */
    public static ApiException analysisExcetpion(Throwable e) {
        ApiException apiException = new ApiException(e);
        if (e instanceof HttpException) {
             /*网络异常*/
            apiException.setCode(CodeException.HTTP_ERROR);
            apiException.setMsg(HttpException_MSG);
        } else if (e instanceof HttpTimeException) {
             /*自定义运行时异常*/
            HttpTimeException exception = (HttpTimeException) e;
            apiException.setCode(CodeException.RUNTIME_ERROR);
            apiException.setMsg(exception.getMessage());
        } else if (e instanceof ConnectException ||e instanceof SocketTimeoutException) {
             /*链接异常*/
            apiException.setCode(CodeException.HTTP_ERROR);
            apiException.setMsg(ConnectException_MSG);
        } else if ( e instanceof JSONException || e instanceof ParseException) {
            apiException.setCode(CodeException.JSON_ERROR);
            apiException.setMsg(JSONException_MSG);
        }else if (e instanceof UnknownHostException){
            /*无法解析该域名异常*/
            apiException.setCode(CodeException.UNKOWNHOST_ERROR);
            apiException.setMsg(UnknownHostException_MSG);
        } else {
            /*未知异常*/
            apiException.setCode(CodeException.UNKNOWN_ERROR);
            apiException.setMsg(e.getMessage());
        }
        return apiException;
    }



}
