package com.datanno.data.exchange.common.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.xiong.common.lib.utils.DensityUtils;
import com.datanno.data.exchange.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class MyPtrFrameLayout extends PtrClassicFrameLayout {

    public MyPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSetparameters();
    }

    public MyPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetparameters();
    }

    public MyPtrFrameLayout(Context context) {
        super(context);
        initSetparameters();
    }

    private void initSetparameters() {
        this.setVisibility(View.VISIBLE);
        this.setResistance(1.7f);
        this.setRatioOfHeaderHeightToRefresh(1.2f);
        this.setDurationToClose(200);
        this.setDurationToCloseHeader(500);
        this.setPullToRefresh(false);
        this.setKeepHeaderWhenRefresh(true);
        this.disableWhenHorizontalMove(true);
        this.setBackgroundColor(getResources().getColor(R.color.gray_common_background));
    }

    private int color = R.color.black;

    public void setColor(int color) {
        this.color = color;
    }

    public void setUltraPullToRefresh(final OnRefreshListener refreshListener, final View InternalView) {
        final StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, DensityUtils.dp2px(getContext(), 10), 0, DensityUtils.dp2px(getContext(), 10));
        header.initWithString(InternalView.getContext().getResources().getString(R.string.app_prty_name));
        header.setTextColor(InternalView.getContext().getResources().getColor(color));
        this.setHeaderView(header);
        this.addPtrUIHandler(header);
        this.disableWhenHorizontalMove(true);
        this.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                if (refreshListener != null) {
                    refreshListener.refresh(frame);
                }
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 8000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame,
                                             View content, View header) {
                if (InternalView != null) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame,
                            InternalView, header);
                } else {
                    return false;
                }
            }
        });
    }

    public interface OnRefreshListener {
        void refresh(PtrFrameLayout frame);
    }

}
