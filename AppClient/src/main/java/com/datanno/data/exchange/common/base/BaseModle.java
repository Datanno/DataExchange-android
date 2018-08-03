package com.datanno.data.exchange.common.base;

import rx.Observable;

/**
 * Created by xionglh on 2018/6/14
 */
public interface BaseModle<T> {
    void savaDataInfo(  T dataInfo);
    Observable<T> getDataInfo(Class<?> cl);
}
