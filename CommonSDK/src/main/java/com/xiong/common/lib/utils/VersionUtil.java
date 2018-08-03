/**
 *
 */
package com.xiong.common.lib.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;


public class VersionUtil {

    private static int version = Build.VERSION.SDK_INT;

    private static int getSdkVersion() {
        return version;
    }

    @TargetApi(8)
    public static boolean sdkVersion8() {
        return getSdkVersion() >= 8;
    }

    @TargetApi(11)
    public static boolean sdkVersion11() {
        return getSdkVersion() >= 11;
    }

    @TargetApi(19)
    public static boolean sdkVersion19() {
        return getSdkVersion() >= 19;
    }

    @TargetApi(17)
    public static boolean sdkVersion17() {
        return getSdkVersion() >= 17;
    }
    @TargetApi(21)
    public static boolean sdkVersion21() {
        return getSdkVersion() >= 21;
    }

    @TargetApi(23)
    public static boolean sdkVersion23(){
        return  getSdkVersion()>23;
    }


    public static String getChannel(Context context) {
        String channel = "";
        try {
            channel = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA).metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
