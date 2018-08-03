package com.datanno.data.exchange.ui.login.presenter;

import com.xiong.common.lib.net.api.RequestCallBackImp;
import com.xiong.common.lib.utils.LanguageUtils;
import com.xiong.common.lib.utils.StrUtils;
import com.xiong.common.lib.utils.SystemInfoUtil;
import com.xiong.common.lib.utils.ToastUtils;
import com.datanno.data.exchange.MyAppAplicition;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseCommonPersenter;
import com.datanno.data.exchange.entity.UserInfo;
import com.datanno.data.exchange.net.request.LoginAutoRequest;
import com.datanno.data.exchange.ui.login.contract.LoginContract;
import com.datanno.data.exchange.ui.login.modle.LoginModleImp;

import java.util.Locale;



@SuppressWarnings("unchecked")
public class LoginPresenterImp extends BaseCommonPersenter<LoginContract.View> implements LoginContract.Presenter {


    private LoginModleImp mLoginModleImp;

    public LoginPresenterImp() {
        mLoginModleImp = new LoginModleImp();
    }

    @Override
    public String getMobile() {
        return mLoginModleImp.getMobile();
    }

    @Override
    public String getUserImgUrl() {
        return mLoginModleImp.getUserImgUrl();
    }

    @Override
    public void languageSwitching() {
        String language= SystemInfoUtil.getSystemLanguageCorE(MyAppAplicition.getInstance());
        if ("english".equals(language)) {
            LanguageUtils.setSystemLanguage(MyAppAplicition.getInstance(), Locale.SIMPLIFIED_CHINESE);
        } else {
            LanguageUtils.setSystemLanguage(MyAppAplicition.getInstance(), Locale.ENGLISH);
        }
        String language1= SystemInfoUtil.getSystemLanguageCorE(MyAppAplicition.getInstance());
        mLoginModleImp.saveLange(language1);
    }


    @Override
    public void login() {
        String userName = getView().getMobile();
        String userPwd = getView().getPwd();
        if (StrUtils.isEmpty(userName)) {
            ToastUtils.getInstance().showToast(
                    R.string.login_activity_edittext_username);
            return;
        }
        if (StrUtils.isEmpty(userPwd)) {
            ToastUtils.getInstance().showToast(
                    R.string.login_activity_edittext_pwd);
            return;
        }

        getView().showProgress();
        LoginAutoRequest loginAutoRequest=new LoginAutoRequest();
        loginAutoRequest.setMobile(userName);
        loginAutoRequest.setPassword(userPwd);
        mCompositeSubscription.add(mApiWrapper.login(loginAutoRequest).subscribe(newMySubscriber(mRequestCallBack)));
    }



    private RequestCallBackImp<UserInfo> mRequestCallBack = new RequestCallBackImp<UserInfo>() {
        @Override
        public void onNext(UserInfo userInfo) {
            mLoginModleImp.savaDataInfo(userInfo);
            getView().finshActivity();
        }
    };





}
