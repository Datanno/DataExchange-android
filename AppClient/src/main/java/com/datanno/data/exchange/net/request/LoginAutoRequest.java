package com.datanno.data.exchange.net.request;

import com.xiong.common.lib.tools.CortyTool;

public class LoginAutoRequest   extends  BasePramasRequest {

    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = CortyTool.encryptMD5(password);
    }
}
