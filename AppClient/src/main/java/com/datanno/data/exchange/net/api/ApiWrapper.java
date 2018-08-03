package com.datanno.data.exchange.net.api;

import com.xiong.common.lib.net.api.Api;
import com.xiong.common.lib.net.response.CommonResponse;
import com.datanno.data.exchange.entity.UserInfo;
import com.datanno.data.exchange.net.request.LoginAutoRequest;

import rx.Observable;

/**
 * Created by xionglh on 2018/6/14
 */
@SuppressWarnings("unchecked")
public class ApiWrapper extends Api{


    public Observable<CommonResponse<UserInfo>> login(LoginAutoRequest loginAutoRequest) {
        return applySchedulers(getService(ApiService.class).requestLogin(toRequestBody(loginAutoRequest)));
    }




}
