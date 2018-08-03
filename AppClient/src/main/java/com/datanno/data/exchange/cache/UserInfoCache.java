package com.datanno.data.exchange.cache;

import android.support.annotation.NonNull;

import com.xiong.common.lib.utils.GJsonUtil;
import com.xiong.common.lib.utils.SharedPreferencesKey;
import com.xiong.common.lib.utils.SharedPreferencesUtil;
import com.xiong.common.lib.utils.StrUtils;
import com.datanno.data.exchange.MyAppAplicition;
import com.datanno.data.exchange.entity.UserInfo;

/**
 * Created by xionglh on 2018/6/14
 */
public class UserInfoCache {

    private static UserInfoCache mUserIfoCache = new UserInfoCache();

    private static UserInfo mUserInfo;

    private UserInfoCache() {

    }

    public static UserInfoCache getInstance() {
        return mUserIfoCache;
    }



    public UserInfo getUserInfo() {
        if (mUserInfo == null) {
            String userInfoStr = SharedPreferencesUtil.getStringValue(MyAppAplicition.getInstance(), SharedPreferencesKey.LGOIN_INFO_TAG);
            if (StrUtils.isEmpty(userInfoStr))
                return null;
            mUserInfo = GJsonUtil.toObject(userInfoStr, UserInfo.class);
            return mUserInfo;
        }
        return mUserInfo;
    }

    public synchronized void setUserInfo(@NonNull UserInfo userInfo) {
        String userInfoStr = GJsonUtil.toJson(userInfo, UserInfo.class);
        mUserInfo=userInfo;
        SharedPreferencesUtil.saveStringValue(MyAppAplicition.getInstance(), SharedPreferencesKey.LGOIN_INFO_TAG, userInfoStr);
    }


    public void cleanUserInfoCache() {
        mUserInfo=null;
        SharedPreferencesUtil.cleanStringValue(MyAppAplicition.getInstance(), SharedPreferencesKey.LGOIN_INFO_TAG);
    }


}
