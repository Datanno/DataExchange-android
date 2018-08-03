package com.xiong.common.lib.net.api;

import android.util.LruCache;

import com.xiong.common.lib.BuildConfig;
import com.xiong.common.lib.R;
import com.xiong.common.lib.application.XlhApplication;
import com.xiong.common.lib.config.Config;
import com.xiong.common.lib.config.Constants;
import com.xiong.common.lib.net.interceptor.HttpRequestInterceptor;
import com.xiong.common.lib.net.interceptor.HttpResponeseInterceptor;
import com.xiong.common.lib.net.response.CommonResponse;
import com.xiong.common.lib.utils.GJsonUtil;
import com.xiong.common.lib.utils.SystemInfoUtil;
import com.xiong.common.lib.utils.VersionUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xionglh on 2017/6/14
 */
public abstract class Api {



    public static final String CACHE_LOCAL_COOKIE_KEY = "cookie";

    private static final String BASE_URL = Config.COMMON_URL;
    private static Retrofit retrofit;
    private static final String MEDIA_TYPE = "application/json;charset=utf-8";

    private static LruCache<String, Object> apiLruCache = new LruCache<>(Integer.MAX_VALUE);


    protected <T> T getService(Class<T> cls) {
        Object o = apiLruCache.get(cls.getName());
        T t;
        if (o != null) {
            t = (T) o;
        } else {
            t = getRetrofit().create(cls);
            apiLruCache.put(cls.getName(), t);
        }
        return t;
    }


    private Map<String, String> getHttpCommHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("accept", "application/json");
        header.put("Connection", "Keep-Alive");
        header.put("Accept-Encoding", "gzip");
        return header;
    }

    private Map<String, String> getHttpCommonParamter() {
        String appVersion = SystemInfoUtil.getAppVersion(XlhApplication.getInstance());
        String appSource = Constants.INVEST_SOURCE_APP;
        String meId = SystemInfoUtil.getDeviceId(XlhApplication.getInstance().
                getApplicationContext());
        String meVersion = SystemInfoUtil.getSystemVersion();
        String channelNo = VersionUtil.getChannel(XlhApplication.getInstance().
                getApplicationContext());
        String language = SystemInfoUtil.getSystemLanguageCorE(XlhApplication.getInstance().
                getApplicationContext());
        Map<String, String> params = new HashMap<>();
        params.put("appVersion", appVersion);
        params.put("appSource", appSource);
        params.put("meId", meId);
        params.put("meVersion", meVersion);
        params.put("channelNo", channelNo);
        return params;
    }


    private Retrofit getRetrofit() {
        if (retrofit == null) {
            //  print all log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG)
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            else
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            //add common  parameter
            HttpRequestInterceptor.Builder httpCommonIntercepoter = new HttpRequestInterceptor.Builder();
            httpCommonIntercepoter.addHeaderParamsMap(getHttpCommHeader());
            httpCommonIntercepoter.addParamsMap(getHttpCommonParamter());
            //set ceche
            File cacheFile = new File(XlhApplication.getInstance().getCacheDir(), "cache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).addNetworkInterceptor(interceptor).addInterceptor(new HttpResponeseInterceptor())
                    .addInterceptor(httpCommonIntercepoter.build()).cache(cache).build();
            retrofit = new Retrofit.Builder().client(client).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return retrofit;
    }

    protected RequestBody toRequestBody(Object object) {
        String json = GJsonUtil.toJson(object, Object.class);
        return RequestBody.create(MediaType.parse(MEDIA_TYPE), json);
    }

    /***
     * Follow Restful style newwork
     *
     * @param responseObservable Observable
     * @param <T>                T
     * @return Observable T
     */
    protected <T> Observable applySchedulersRestful(Observable<T> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((T t) -> Observable.create(subscriber -> {
                    if (t != null && !subscriber.isUnsubscribed()) {
                        subscriber.onNext(t);
                    } else {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onError(new ApiException("0",
                                    XlhApplication.getInstance().getString(R.string.exception_json_error)));
                        }
                        return;
                    }
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                    }
                }));
    }


    /**
     * Follow Shanlinbao ruesult style newwork
     *
     * @param responseObservable responseObservable
     * @param <T>                T
     * @return Observable T
     */
    protected <T> Observable applySchedulers(Observable<CommonResponse<T>> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((CommonResponse<T> t) -> Observable.create(subscriber -> {
                    if (t != null && !subscriber.isUnsubscribed()) {
                        CommonResponse<T>.Result<T> result = t.getResult();
                        if (result != null && (!result.isError() || result.isSuccess())) {
                            subscriber.onNext(result.getData());
                        } else if (result != null) {
                            subscriber.onError(new ApiException(result.getCode(), result.getMessage()));//业务错误
                        }
                    } else {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onError(new ApiException("0", XlhApplication.getInstance().getString(R.string.exception_json_error)));
                        }
                        return;
                    }
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                    }
                }));
    }

}



