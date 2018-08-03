package com.datanno.data.exchange.common.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.datanno.data.exchange.R;

/**
 * Created by xionglh on 2018/6/14
 */
public class BaseTitleBarActivity extends BaseActivity {

    protected TextView mTextViewTitle, mTextViewLeft, mTextViewRight;

    protected RelativeLayout mRlViewTitile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        setContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void initViews() {
        mRlViewTitile=(RelativeLayout)findViewById(R.id.title_bar_layout);
        mTextViewTitle = (TextView) findViewById(R.id.title_bar_center_text_title);
        mTextViewLeft = (TextView) findViewById(R.id.title_bar_left_button_back);
        mTextViewRight = (TextView) findViewById(R.id.title_bar_right_button);
        hideLeftButton();
        hideRightButton();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        initViews();
    }


    public void setTitle(@StringRes  int titleResId) {
        setTitle(getString(titleResId));
    }


    public void setTitle(String title) {
        if (mTextViewTitle != null)
            mTextViewTitle.setText(title);
    }

    public void hideLeftButton() {
        if (mTextViewLeft != null)
            mTextViewLeft.setVisibility(View.GONE);
    }


    public void hideRightButton() {
        if (mTextViewRight != null)
            mTextViewRight.setVisibility(View.GONE);
    }

    public void showLeftButton() {
        if (mTextViewLeft != null) {
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setOnClickListener(t->finish());
        }
    }

    public void showLeftButtonWithTextListener(String text,
                                               OnClickListener listener){
        if (mTextViewLeft != null) {
            mTextViewLeft.setText(text);
            mTextViewLeft.setOnClickListener(listener);
            mTextViewLeft.setVisibility(View.VISIBLE);
        }
    }


    public void showLeftButtonWithListener(OnClickListener listener) {
        if (mTextViewLeft != null) {
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setOnClickListener(listener);
        }
    }


    public void showRightButtonWithImageAndListener(@DrawableRes  int imageId,
                                                    OnClickListener listener) {
        if (mTextViewRight != null) {
            Drawable nav_up = getResources().getDrawable(imageId);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
                    nav_up.getMinimumHeight());
            mTextViewRight.setCompoundDrawables(null, null, nav_up, null);
            mTextViewRight.setOnClickListener(listener);
            mTextViewRight.setVisibility(View.VISIBLE);
        }
    }

    public void showRightButtonWithTextAndListener(String text,
                                                   OnClickListener listener) {
        if (mTextViewRight != null) {
            mTextViewRight.setText(text);
            mTextViewRight.setOnClickListener(listener);
            mTextViewRight.setVisibility(View.VISIBLE);
        }
    }

    public void showRightButtonWithTextAndListener(@StringRes  int textId,
                                                   OnClickListener listener) {
        showRightButtonWithTextAndListener(getString(textId), listener);
    }
}
