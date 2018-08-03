package com.datanno.data.exchange.common.base;

import android.os.Bundle;

/**
 * Created by xionglh on 2018/2/7.
 */
public abstract class  BaseCommonActivity<V  extends BaseView, P extends BaseCommonPersenter<V>> extends BaseTitleBarActivity {

    protected P mPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPersenter = createPersenter();
        mPersenter.attachView((V)this);
    }


    protected abstract P createPersenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPersenter.detachView();
    }
}
