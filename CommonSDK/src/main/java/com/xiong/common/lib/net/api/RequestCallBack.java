package com.xiong.common.lib.net.api;

/**
 * Created by xionglh on 2017/6/14
 */
public interface RequestCallBack<T> {

    void onCompleted();
    void onError(ApiException apiexception);
    void onNext(T t);
}
