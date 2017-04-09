package yfwang.rxjavademo.exception;

/**
 * Description: 回调统一请求异常
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/9 10:43
 */
public class ApiException extends Exception {

    /*错误码*/
    private int code;
    /*显示的信息*/
    private String msg;



    @CodeException.CodeEp
    public int getCode() {
        return code;
    }

    public void setCode(@CodeException.CodeEp int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public ApiException(Throwable throwable, @CodeException.CodeEp int code, String msg) {
        super(msg, throwable);
        setCode(code);
        setMsg(msg);
    }


}
