package com.xiong.common.lib.application;

import android.app.Application;
import android.content.Context;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.xiong.common.lib.config.Constants;
import com.xiong.common.lib.tools.CrashHandlerTool;
import com.xiong.common.lib.utils.LanguageUtils;
/**
 * Created by xionglh on 2018/6/14
 */
public class XlhApplication extends Application {

    private static XlhApplication mInstance;

    public static XlhApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        UMConfigure.init(this, 12, Constants.PUSH_SECRET);
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandlerTool());
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {

            }
            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LanguageUtils.loacalAttachBaseContext(base));
    }
}
