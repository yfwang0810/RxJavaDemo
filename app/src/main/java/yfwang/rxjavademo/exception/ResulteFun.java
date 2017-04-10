package yfwang.rxjavademo.exception;

import rx.functions.Func1;

/**
 * Description: 服务器返回数据判断
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/10 14:08
 */
public class ResulteFun implements Func1<Object,Object>{
    @Override
    public Object call(Object o) {
        if (o == null || "".equals(o.toString())) {
            throw new HttpTimeException("数据错误");
        }
        return o;
    }
}
