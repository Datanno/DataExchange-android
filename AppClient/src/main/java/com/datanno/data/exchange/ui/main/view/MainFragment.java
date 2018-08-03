package com.datanno.data.exchange.ui.main.view;

import android.view.View;
import android.widget.LinearLayout;

import com.xiong.common.lib.utils.SystemInfoUtil;
import com.xiong.common.lib.utils.VersionUtil;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseCommonFragment;
import com.datanno.data.exchange.ui.main.contract.MainContract;
import com.datanno.data.exchange.ui.main.presenter.MainPersenter;


/**
 * 首页
 * Created by xionglh on 2018/6/20
 */
public class MainFragment extends BaseCommonFragment<MainContract.View, MainPersenter> implements MainContract.View {



    @Override
    protected MainPersenter createPersenter() {
        return new MainPersenter();
    }


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View contentView) {
        View mViewStausBar = contentView.findViewById(R.id.view_fragment_main_status_show);
        if (VersionUtil.sdkVersion19()) {
            int statusHeght = SystemInfoUtil.getStatusHeight(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, statusHeght);
            mViewStausBar.setLayoutParams(layoutParams);
            mViewStausBar.setBackgroundColor(getResources().getColor(R.color.transparent));
        }

    }

    @Override
    public void showProgress() {
        mContext.showProgressDialog();
    }

    @Override
    public void dissProgress() {
        mContext.dismissProgressDialog();
    }
}

