package com.datanno.data.exchange.common.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.xiong.common.arouter.url.ARouterPageUrl;
import com.xiong.common.lib.dialog.CustomProgressDialog;
import com.xiong.common.lib.utils.ActivityPageManager;
import com.xiong.common.lib.utils.LanguageUtils;
import com.xiong.common.lib.utils.StatusBarUtil;
import com.datanno.data.exchange.ui.main.view.MainActivity;

import org.greenrobot.eventbus.EventBus;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by xionglh on 2017/2/7.
 */
public abstract class BaseActivity extends RxFragmentActivity {


    public static final String SKIP_ACTIVITY_WHERE_FROM = "fromWhere";


    protected String mFromWhere;
    protected View mContentView;
    protected CompositeSubscription mCompositeSubscription;
    public CustomProgressDialog mProgressDialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new CustomProgressDialog(this, false, false);
        ActivityPageManager.getInstance().addActivity(this);
        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setStatusBar();
        mContentView = view;
        init();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtils.loacalAttachBaseContext(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null);

    }


    public void init() {
        initFromWhere();
    }

    protected abstract void initViews();


    protected void initFromWhere() {
        if (null != getIntent().getExtras()) {
            if (getIntent().getExtras().containsKey(SKIP_ACTIVITY_WHERE_FROM)) {
                mFromWhere = getIntent().getStringExtra(SKIP_ACTIVITY_WHERE_FROM);
            }
        }
    }

    public String getFromWhere() {
        return mFromWhere;
    }

    public void startAct(String routerUrl) {
        ARouter.getInstance().build(routerUrl).withString(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName()).navigation();
    }


    public void startAct(String routerUrl, int requestCode) {
        ARouter.getInstance().build(routerUrl).withString(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName()).navigation(this, requestCode);
    }

    public void startAct(String routerUrl, Bundle bundle) {
        ARouter.getInstance().build(routerUrl).with(bundle).withString(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName()).navigation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityPageManager.unbindReferences(mContentView);
        ActivityPageManager.getInstance().removeActivity(this);
        mContentView = null;
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mProgressDialog = null;
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void showProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }





    public void gotoMainActivity() {
        ARouter.getInstance().build(ARouterPageUrl.APP_ACTIVITY_MAIN).withInt(MainActivity.CHECK_PAGE_TAG, MainActivity.CHECK_MAIN_VALUES)
                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP).navigation();
    }


    @Override
    public void onBackPressed() {
        finish();
    }

}
