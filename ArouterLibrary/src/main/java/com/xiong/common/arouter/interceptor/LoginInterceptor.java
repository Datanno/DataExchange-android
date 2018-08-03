package com.xiong.common.arouter.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiong.common.arouter.config.Constants;
import com.xiong.common.arouter.url.ARouterPageUrl;
import com.xiong.common.lib.application.XlhApplication;
import com.xiong.common.lib.utils.SharedPreferencesKey;
import com.xiong.common.lib.utils.SharedPreferencesUtil;
import com.xiong.common.lib.utils.StrUtils;

/**
 * Created by xionglh on 2018/6/14
 */
@Interceptor(priority = 1, name = "登录拦截")
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String userInfoStr = SharedPreferencesUtil.getStringValue(XlhApplication.getInstance(), SharedPreferencesKey.LGOIN_INFO_TAG);
        if (Constants.NEED_LOGIN == postcard.getExtra() && StrUtils.isEmpty(userInfoStr)) {
            ARouter.getInstance().build(ARouterPageUrl.APP_ACTIVITY_LOGIN).navigation();
            callback.onInterrupt(null);
        } else {
            callback.onContinue(postcard);
        }

    }

    @Override
    public void init(Context context) {

    }
}
