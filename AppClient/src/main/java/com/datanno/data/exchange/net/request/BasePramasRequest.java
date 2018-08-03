package com.datanno.data.exchange.net.request;

import com.xiong.common.lib.utils.SystemInfoUtil;
import com.datanno.data.exchange.MyAppAplicition;


public class BasePramasRequest {


    public String language=SystemInfoUtil.getSystemLanguageCorE(MyAppAplicition.getInstance().
            getApplicationContext());

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language =language ;
    }
}
