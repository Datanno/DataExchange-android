package com.datanno.data.exchange.common.base;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.xiong.common.lib.application.XlhApplication;

import java.util.Map;

/**
 * Created by xionglh on 2018/6/14
 */
public class BaseApplication extends XlhApplication {



    public static Map<String, Map<String, Long>> mTimeDowmMap;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}
