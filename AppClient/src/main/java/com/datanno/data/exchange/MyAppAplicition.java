package com.datanno.data.exchange;

import android.annotation.TargetApi;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.datanno.data.exchange.common.base.BaseApplication;


public class MyAppAplicition extends BaseApplication {


    private static MyAppAplicition mInstance;



    public static MyAppAplicition getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initPhotoError();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        MultiDex.install(this);
    }
    @TargetApi(18)
    private void initPhotoError() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

}
