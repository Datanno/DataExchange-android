package com.xiong.common.lib.net.api;

/**
 * Created by xionglh on 2017/6/14
 */
public abstract class RequestCallBackImp<T> implements RequestCallBack<T> {

    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(ApiException apiexception) {

    }
}
