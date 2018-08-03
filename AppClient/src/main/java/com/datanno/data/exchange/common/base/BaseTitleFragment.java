package com.datanno.data.exchange.common.base;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.datanno.data.exchange.MyAppAplicition;
import com.datanno.data.exchange.R;


/**
 * Created by xionglh on 2016/8/1.
 */
public abstract class BaseTitleFragment extends BaseFragment {
    protected TextView mTextViewTitle, mTextViewLeft, mTextViewRight;
    protected RelativeLayout mLayoutTitleBar;
    private FrameLayout mContentLayout;

    protected RelativeLayout mRlViewTitile;

    public void resetTitleBar() {
        hideLeftButton();
        hideRightButton();
        mTextViewRight.setCompoundDrawables(null, null, null, null);
        mTextViewRight.setText("");
    }

    public void setTitle(int titleResId) {
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



    public void showLeftButtonWithTextListener(String text,
                                               View.OnClickListener listener) {
        if (mTextViewLeft != null) {
            mTextViewLeft.setText(text);
            mTextViewLeft.setOnClickListener(listener);
            mTextViewLeft.setVisibility(View.VISIBLE);
        }
    }

    public void showLeftButtonWithListener(View.OnClickListener listener) {
        if (mTextViewLeft != null) {
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setOnClickListener(listener);
        }
    }


    public void showRightButtonWithImageAndListener(int imageId,
                                                    View.OnClickListener listener) {
        if (mTextViewRight != null) {
            Drawable nav_up = MyAppAplicition.getInstance().getResources().getDrawable(imageId);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
                    nav_up.getMinimumHeight());
            mTextViewRight.setCompoundDrawables(null, null, nav_up, null);
            mTextViewRight.setOnClickListener(listener);
            mTextViewRight.setVisibility(View.VISIBLE);
        }
    }


    public void showRightButtonWithTextAndListener(String text,
                                                   View.OnClickListener listener) {
        if (mTextViewRight != null) {
            mTextViewRight.setText(text);
            mTextViewRight.setOnClickListener(listener);
            mTextViewRight.setVisibility(View.VISIBLE);
        }
    }

    public void showRightButtonWithTextAndListener(int textId,
                                                   View.OnClickListener listener) {
        showRightButtonWithTextAndListener(MyAppAplicition.getInstance().getString(textId), listener);
    }


    protected void initTitleView(View contentView){
        mRlViewTitile = (RelativeLayout) contentView.findViewById(R.id.title_bar_layout);
        mTextViewTitle = (TextView) contentView.findViewById(R.id.title_bar_center_text_title);
        mTextViewLeft = (TextView) contentView.findViewById(R.id.title_bar_left_button_back);
        mTextViewRight = (TextView) contentView.findViewById(R.id.title_bar_right_button);
        hideLeftButton();
        hideRightButton();
    }

    @Override
    public void initView(View contentView) {
        initTitleView(contentView);
    }
}


