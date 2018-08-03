package com.datanno.data.exchange.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiong.common.lib.utils.SystemInfoUtil;
import com.xiong.common.lib.utils.VersionUtil;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseActivity;


/**
 * Created by xionglh on 2017/4/6.
 */

public class TitleBar extends LinearLayout {

    protected TextView mTextViewTitle, mTextViewLeft, mTextViewRight;

    protected RelativeLayout mRlViewTitile;

    private boolean mIshowAddBar;
    private int mViewbg;
    private int  mTitleBg;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearLayoutTitlesBar);
        mIshowAddBar = typedArray.getBoolean(R.styleable.LinearLayoutTitlesBar_showStatusBar, true);
        mViewbg=typedArray.getResourceId(R.styleable.LinearLayoutTitlesBar_showStatusBarViewColor,R.drawable.title_bar_bg);
        mTitleBg=typedArray.getResourceId(R.styleable.LinearLayoutTitlesBar_titleBarBg,R.drawable.title_bar_bg);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        setId(R.id.titile_id);
        this.setVerticalGravity(LinearLayout.VERTICAL);
        View viewBar = new View(getContext());
        if (VersionUtil.sdkVersion19() && mIshowAddBar) {
            int statusHeght = SystemInfoUtil.getStatusHeight(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, statusHeght);
            viewBar.setLayoutParams(layoutParams);
            viewBar.setBackgroundResource(mViewbg);
            this.addView(viewBar);
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_titlebar, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getContext().getResources().getDimensionPixelSize(R.dimen.title_common_height));
        view.setLayoutParams(layoutParams);
        this.addView(view);
        mRlViewTitile = (RelativeLayout) findViewById(R.id.title_bar_layout);
        mTextViewTitle = (TextView) findViewById(R.id.title_bar_center_text_title);
        mTextViewLeft = (TextView) findViewById(R.id.title_bar_left_button_back);
        mTextViewRight = (TextView) findViewById(R.id.title_bar_right_button);
        mRlViewTitile.setBackgroundResource(mTitleBg);

    }


    public void setTitle(@StringRes int titleResId) {
        setTitle(getContext().getString(titleResId));
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (mTextViewTitle != null)
            mTextViewTitle.setText(title);
    }

    /**
     * 左边 右边 的button 都是默认隐藏的
     */
    public void hideLeftButton() {
        if (mTextViewLeft != null)
            mTextViewLeft.setVisibility(View.GONE);
    }

    /**
     * 左边 右边 的button 都是默认隐藏的
     */
    public void hideRightButton() {
        if (mTextViewRight != null)
            mTextViewRight.setVisibility(View.GONE);
    }

    /**
     * 显示返回键，点击后关闭页面
     */
    public void showLeftButton() {
        if (mTextViewLeft != null) {
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setOnClickListener(t -> ((BaseActivity) (getContext())).finish());
        }
    }

    /**
     * 显示左键，修改文字及添加listener
     */
    public void showLeftButtonWithTextListener(String text,
                                               OnClickListener listener) {
        if (mTextViewLeft != null) {
            mTextViewLeft.setText(text);
            mTextViewLeft.setOnClickListener(listener);
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setCompoundDrawables(null, null, null, null);
        }
    }

    /**
     * 重写 左键 listener
     *
     * @param listener
     */
    public void showLeftButtonWithListener(OnClickListener listener) {
        if (mTextViewLeft != null) {
            mTextViewLeft.setVisibility(View.VISIBLE);
            mTextViewLeft.setOnClickListener(listener);
        }
    }

    /**
     * 重置右键的图片及点击事件
     *
     * @param imageId
     * @param listener
     */
    public void showRightButtonWithImageAndListener(@DrawableRes int imageId,
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

    /**
     * 重置右键的文字及点击事件
     *
     * @param text
     * @param listener
     */
    public void showRightButtonWithTextAndListener(String text,
                                                   OnClickListener listener) {
        if (mTextViewRight != null) {
            mTextViewRight.setText(text);
            mTextViewRight.setOnClickListener(listener);
            mTextViewRight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 通过resourse id 重置右键的文字及点击事件
     *
     * @param textId
     * @param listener
     */
    public void showRightButtonWithTextAndListener(@StringRes int textId,
                                                   OnClickListener listener) {
        showRightButtonWithTextAndListener(getContext().getString(textId), listener);
    }
}
