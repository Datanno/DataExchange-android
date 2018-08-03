package com.datanno.data.exchange.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by xionglh on 2018/6/14
 */
public abstract class BaseCommonFragment<V extends BaseView, T extends BaseCommonPersenter<V>> extends BaseTitleFragment {

    protected T mPersenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPersenter = createPersenter();
        mPersenter.attachView((V) this);
    }

    protected abstract T createPersenter();

    @Override
    public void onDestroy() {
        mPersenter.detachView();
        super.onDestroy();
    }
}
