package com.datanno.data.exchange.net.api;

import com.xiong.common.lib.net.response.CommonResponse;
import com.datanno.data.exchange.entity.UserInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xionglh on 2018/6/14
 */
public interface ApiService {


    @POST("/test/login")
    Observable<CommonResponse<UserInfo>> requestLogin(@Body RequestBody requestBody);


}
