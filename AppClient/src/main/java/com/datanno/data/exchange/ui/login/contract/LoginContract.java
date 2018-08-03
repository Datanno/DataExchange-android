package com.datanno.data.exchange.ui.login.contract;

import com.datanno.data.exchange.common.base.BasePersenter;
import com.datanno.data.exchange.common.base.BaseView;

import java.util.List;


public interface LoginContract {


    interface View extends BaseView {

        String getMobile();
        String getPwd();
        void finshActivity();
        void showDilog(List<String> pars);

    }

    interface Presenter extends BasePersenter {

        void login( );
        String getMobile();
        String getUserImgUrl();
        void languageSwitching();
    }


}
