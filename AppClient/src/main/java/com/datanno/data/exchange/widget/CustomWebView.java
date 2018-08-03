package com.datanno.data.exchange.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.xiong.common.lib.utils.DensityUtils;
import com.xiong.common.lib.utils.ToastUtils;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.webview.CustomHostJsScope;

import cn.pedant.SafeWebViewBridge.InjectedChromeClient;


@SuppressWarnings("uncheked")
/**
 * Created by xionglh on 2017/6/14
 */
public class CustomWebView extends WebView {

    private ProgressBar mProgressBar;

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new WebView.LayoutParams(WebView.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(getContext(), 3), 0, 0));
        mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.common_webview_progressbar));
        addView(mProgressBar);
        WebSettings setting = this.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        this.removeJavascriptInterface("searchBoxJavaBredge_");
        setWebChromeClient(new WebChromeClient("android", CustomHostJsScope.class));
        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }
        });

    }

    private class WebChromeClient extends InjectedChromeClient {


        WebChromeClient(String injectedName, Class injectedCls) {
            super(injectedName, injectedCls);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress >= 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                mProgressBar.setVisibility(VISIBLE);
                mProgressBar.setProgress(newProgress);
            }

            super.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage cm) {
            return true;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            ToastUtils.getInstance().showToast(message);
            result.cancel();
            return true;
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
