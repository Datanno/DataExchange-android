package com.datanno.data.exchange.common.base;

import com.xiong.common.lib.utils.GJsonUtil;
import com.xiong.common.lib.utils.SharedPreferencesUtil;
import com.datanno.data.exchange.MyAppAplicition;

import rx.Observable;

/**
 * Created by xionglh on 2018/6/14
 */
public class BaseCommonModle<T> implements BaseModle<T> {

    @Override
    public void savaDataInfo(T t) {
        String s = GJsonUtil.objToJson(t);
        SharedPreferencesUtil.saveStringValue(MyAppAplicition.getInstance(), BaseCommonModle.this.getClass().getName(), s);
    }

    @Override
    public Observable<T> getDataInfo(final Class<?> cl) {
        String cacheInfoStr = SharedPreferencesUtil.getStringValue(MyAppAplicition.getInstance(), BaseCommonModle.this.getClass().getName());
        return Observable.just(cacheInfoStr).map(t->GJsonUtil.toObject(t, cl));
    }


}
