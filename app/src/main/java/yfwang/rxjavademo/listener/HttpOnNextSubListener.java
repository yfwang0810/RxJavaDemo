package yfwang.rxjavademo.listener;

import java.util.Observable;

/**
 * Description: Subscriber回调
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/9 13:54
 */
public interface HttpOnNextSubListener {
    /**
     * ober成功回调
     * @param observable
     * @param method
     */
      void OnNext(Observable observable,String method);
}
