package com.xiong.common.lib.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.ObjectAnimator;
import com.xiong.common.lib.R;

/**
 * Created by xionglh on 2018/6/14
 */
public class CustomProgressDialog extends ProgressDialog {


    private ObjectAnimator mObjectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_common_progress_dialog);
        ImageView imgLoading = (ImageView) findViewById(R.id.img_customer_progress_dialog);
        LinearLayout llShow=(LinearLayout)findViewById(R.id.ll_customer_progress_dialog);
        llShow.getBackground().setAlpha(60);
        if (mObjectAnimator == null)
            mObjectAnimator = ObjectAnimator.ofFloat(imgLoading, "rotation", 0f, 360f);
        mObjectAnimator.setDuration(2000);
        mObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        mObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        mObjectAnimator.start();
    }


    public CustomProgressDialog(Context context,
                                boolean canceledOnTouchOutside, boolean cancelable) {
        super(context, R.style.loading_dialog);
        setCanceledOnTouchOutside(canceledOnTouchOutside);
        setCancelable(cancelable);
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        if (mObjectAnimator != null) {
            mObjectAnimator.cancel();
        }
        super.setOnDismissListener(listener);

    }
}
