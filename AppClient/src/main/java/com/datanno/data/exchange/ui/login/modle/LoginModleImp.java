package com.datanno.data.exchange.ui.login.modle;

import com.xiong.common.lib.utils.SharedPreferencesKey;
import com.xiong.common.lib.utils.SharedPreferencesUtil;
import com.datanno.data.exchange.MyAppAplicition;
import com.datanno.data.exchange.cache.UserInfoCache;
import com.datanno.data.exchange.common.base.BaseCommonModle;
import com.datanno.data.exchange.entity.UserInfo;


public class LoginModleImp extends BaseCommonModle<UserInfo> implements LoginModle {


    @Override
    public void savaDataInfo(UserInfo userInfo) {


        UserInfoCache.getInstance().setUserInfo(userInfo);
    }


    @Override
    public String getMobile() {
        return SharedPreferencesUtil.getStringValue(MyAppAplicition.getInstance(), SharedPreferencesKey.SAVE_LOGIN_NAME);
    }

    @Override
    public String getPwd() {
        return SharedPreferencesUtil.getStringValue(MyAppAplicition.getInstance(), SharedPreferencesKey.SAVE_LOGIN_PWD);
    }

    @Override
    public String getUserImgUrl() {
        return SharedPreferencesUtil.getStringValue(MyAppAplicition.getInstance(), SharedPreferencesKey.SAVE_IMGEVIEW_LOGIN_ICON);
    }

    @Override
    public void saveLange(String la) {
        SharedPreferencesUtil.saveStringValue(MyAppAplicition.getInstance(), SharedPreferencesKey.LANGUAGE_TAG,la);

    }
}
