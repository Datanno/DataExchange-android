package com.xiong.common.lib.utils;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.xiong.common.lib.R;
import com.xiong.common.lib.application.XlhApplication;


public class ToastUtils {


    private static ToastUtils mToashUtils;
    private Toast mToast;

    private ToastUtils() {

    }

    public static ToastUtils getInstance() {
        if (mToashUtils == null)
            synchronized (ToastUtils.class) {
                if (mToashUtils == null)
                    mToashUtils = new ToastUtils();
            }
        return mToashUtils;
    }


    public void showToast(String message) {
        TextView textView = new TextView(XlhApplication.getInstance());
        textView.setText(message);
        textView.setBackgroundResource(R.drawable.bg_error_toast);
        textView.setTextColor(Color.WHITE);
        if (mToast == null)
            mToast = new Toast(XlhApplication.getInstance());
        mToast.setView(textView);
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mToast.show();

    }


    public void showToast(int id) {
        showToast(XlhApplication.getInstance().getString(id));
    }

    public void showToastWithPicture(int pictureResourceId) {
        ImageView imageView = new ImageView(XlhApplication.getInstance());
        imageView.setImageResource(pictureResourceId);
        if (mToast == null)
            mToast = new Toast(XlhApplication.getInstance());
        mToast.setView(imageView);
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mToast.show();
    }

}
