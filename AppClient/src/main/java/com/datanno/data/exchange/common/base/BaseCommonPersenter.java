package com.datanno.data.exchange.common.base;

import com.google.gson.JsonSyntaxException;
import com.xiong.common.lib.net.api.ApiException;
import com.xiong.common.lib.net.api.RequestCallBackImp;
import com.xiong.common.lib.tools.SystemLog;
import com.xiong.common.lib.utils.ToastUtils;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.net.api.ApiWrapper;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xionglh on 2018/6/14
 */
public abstract class BaseCommonPersenter<T extends BaseView>   {

    protected WeakReference<T> mViewRef;
    protected ApiWrapper mApiWrapper;
    protected CompositeSubscription mCompositeSubscription;


    protected void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
        mCompositeSubscription = new CompositeSubscription();
        mApiWrapper = new ApiWrapper();
    }



    @SuppressWarnings("unchecked")
    protected <T> Subscriber newMySubscriber(final RequestCallBackImp requestCallBack) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                getView().dissProgress();
                requestCallBack.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                SystemLog.E("Throwable e",e.getMessage());
                getView().dissProgress();
                if (e instanceof ApiException) {
                    ApiException exception = (ApiException) e;
                    ToastUtils.getInstance().showToast(exception.message);
                    requestCallBack.onError(exception);
                    SystemLog.E("ApiException",exception.message);

                }  else if (e instanceof SocketTimeoutException) {//Server response timeout
                    ToastUtils.getInstance().showToast(R.string.exception_json_error);
                    SystemLog.E("SocketTimeoutException",e.getMessage());

                } else if (e instanceof ConnectException) {//Server request timeout
                    ToastUtils.getInstance().showToast(R.string.exception_netword_error);
                    SystemLog.E("ConnectException",e.getMessage());
                }else if (e instanceof HttpException) {
                    ToastUtils.getInstance().showToast(((HttpException) e).message());
                    SystemLog.E("ConnectException",((HttpException) e).message());
                }else if( e instanceof JsonSyntaxException){
                    ToastUtils.getInstance().showToast(R.string.exception_netword_error);
                    SystemLog.E("JsonSyntaxException",((HttpException) e).message());

                }

            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    requestCallBack.onNext(t);
                }
            }

        };
    }


    protected T getView() {
        return mViewRef.get();
    }


    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }


    public void detachView() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
