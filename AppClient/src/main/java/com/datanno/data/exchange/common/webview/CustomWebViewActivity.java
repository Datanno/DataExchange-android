package com.datanno.data.exchange.common.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiong.common.arouter.config.Constants;
import com.xiong.common.arouter.url.ARouterPageUrl;
import com.xiong.common.lib.utils.SharedPreferencesKey;
import com.xiong.common.lib.utils.SharedPreferencesUtil;
import com.xiong.common.lib.utils.StrUtils;
import com.datanno.data.exchange.MyAppAplicition;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseTitleBarActivity;
import com.datanno.data.exchange.libs.GlobalConstants;
import com.datanno.data.exchange.widget.CustomWebView;


/**
 * Created by xionglh on 2015/8/27.
 */
@SuppressLint("SetJavaScriptEnabled")
@Route(path = ARouterPageUrl.APP_ACTIVITY_CUSTOM_WEB_VIEW, extras = Constants.NEED_LOGIN)
public class CustomWebViewActivity extends BaseTitleBarActivity {


    @Autowired
    public String custTitle;
    @Autowired
    public String custUrl;

    private CustomWebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_common_webview);
    }


    @Override
    protected void initViews() {
        super.initViews();
        showLeftButton();
        showLeftButtonWithListener(t -> {
            if (mWebView.canGoBack())
                mWebView.goBack();
            else
                finish();
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        String title = custTitle;
        setTitle(title);
        mWebView = (CustomWebView) findViewById(R.id.wb_products_loding);
        mWebView.loadUrl(loadingUrl());
    }

    private String loadingUrl() {
        String roolUrl = custUrl;
        if (roolUrl.contains("?")) {
            roolUrl = roolUrl + "&";
        } else {
            roolUrl = roolUrl + "?";
        }
        roolUrl = roolUrl + "source=" + GlobalConstants.INVEST_SOURCE_APP;
        String token = SharedPreferencesUtil.getStringValue(MyAppAplicition.getInstance(),
                SharedPreferencesKey.TOKEN_KEY);
        if (!StrUtils.isEmpty(token)) {
            roolUrl = roolUrl + "&token=" + token;
        }
        return roolUrl;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();

        }
    }

}
