package com.datanno.data.exchange.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by xionglh on 2018/6/14
 */
public abstract class BaseFragment extends com.trello.rxlifecycle.components.support.RxFragment {


    public BaseActivity mContext;

    public View mContentView = null;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getBaseActivity();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(onSetLayoutId(), container, false);
        }
        initView(mContentView);
        return mContentView;
    }


    public abstract int onSetLayoutId();

    public abstract void initView(View contentView);

    public void startAct(String routerUrl) {
        mContext.startAct(routerUrl);
    }

    public void startAct(String routerUrl, int requestCode) {
        mContext.startAct(routerUrl, requestCode);
    }

    public void startAct(String routerUrl, Bundle bundle) {
        mContext.startAct(routerUrl, bundle);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mContentView = null;
    }
}
