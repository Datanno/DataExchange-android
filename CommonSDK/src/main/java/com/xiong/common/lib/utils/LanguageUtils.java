package com.xiong.common.lib.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import com.xiong.common.lib.application.XlhApplication;

import java.util.Locale;

/**
 * Created by xionglh on 2017/6/14
 */
public class LanguageUtils {

    public static Locale getSystemLanguage(Context context) {
        Locale  locale ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale;
    }

    public static void setSystemLanguage(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    public static Context attachBaseContext(Context context, Locale language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        } else {
            return context;
        }
    }

    public static Context loacalAttachBaseContext(Context context){
      String la=  SharedPreferencesUtil.getStringValue(context, SharedPreferencesKey.LANGUAGE_TAG);
        if ("english".equals(la)) {
             return  attachBaseContext(context,Locale.ENGLISH);
        } else {
           return  attachBaseContext(context,Locale.SIMPLIFIED_CHINESE);
        }
    }



}
