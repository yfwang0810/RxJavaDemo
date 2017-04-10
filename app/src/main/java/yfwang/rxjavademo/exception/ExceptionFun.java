package yfwang.rxjavademo.exception;


import rx.Observable;
import rx.functions.Func1;

/**
 * Description: 异常处理
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/10 10:13
 */
public class ExceptionFun implements Func1<Throwable, Observable>{
    @Override
    public Observable call(Throwable throwable) {

        return Observable.error(FactoryException.analysisExcetpion(throwable));
    }
}
