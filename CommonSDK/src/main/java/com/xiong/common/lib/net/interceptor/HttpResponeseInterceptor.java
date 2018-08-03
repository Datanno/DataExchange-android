package com.xiong.common.lib.net.interceptor;

import com.xiong.common.lib.application.XlhApplication;
import com.xiong.common.lib.net.api.Api;
import com.xiong.common.lib.tools.SystemLog;
import com.xiong.common.lib.utils.SharedPreferencesUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by xionglh on 2017/6/14
 */
public class HttpResponeseInterceptor implements Interceptor {

    public HttpResponeseInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        SystemLog.V("http_code",""+originalResponse.code());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.from(originalResponse.headers("Set-Cookie"))
                    .map(s -> {
                        String[] cookieArray = s.split(";");
                        return cookieArray[0];
                    }).subscribe(s -> {
                cookieBuffer.append(s).append(";");
            });
            SharedPreferencesUtil.saveStringValue(XlhApplication.getInstance(), Api.CACHE_LOCAL_COOKIE_KEY, cookieBuffer.toString());
        }
        return originalResponse;
    }
}
