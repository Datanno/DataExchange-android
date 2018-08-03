package com.datanno.data.exchange.common.webview;

import android.webkit.WebView;

import com.xiong.common.arouter.url.ARouterPageUrl;

/**
 * android.testA()
 * Created by xionglh on 2018/3/23.
 */
public class CustomHostJsScope {

    public static void testA(WebView webView) {
        CustomWebViewActivity baseActivity = (CustomWebViewActivity) webView.getContext();
        baseActivity.startAct(ARouterPageUrl.APP_ACTIVITY_LOGIN);
    }


}
