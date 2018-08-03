package com.datanno.data.exchange.ui.main.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiong.common.arouter.url.ARouterPageUrl;
import com.xiong.common.lib.utils.StatusBarUtil;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseFragment;
import com.datanno.data.exchange.common.base.BaseTitleBarActivity;

import org.greenrobot.eventbus.EventBus;



@Route(path = ARouterPageUrl.APP_ACTIVITY_MAIN)
public class MainActivity extends BaseTitleBarActivity {

    public static final int CHECK_MAIN_VALUES = 0;
    public static final int CHECK_COMMOUNITY_VALUES = 1;
    public static final int CHECK_ME_VALUES = 2;

    public static final String CHECK_PAGE_TAG = "CHECK_PAGE_TAG";
    public static final String MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG";
    public static final String COMMUNITY_FRAGMENT_TAG = "COMMUNITY_FRAGMENT_TAG";
    public static final String ME_FRAGMENT_TAG = "ME_FRAGMENT_TAG";

    private String mCurrentFragmentTag = MAIN_FRAGMENT_TAG;
    private BaseFragment mCurrentFragment;
    private FragmentManager mFragmentManager;
    private BaseFragment mMainFramgent, mCommunityFragment, mMeFragment;

    private RadioButton mRbtMain, mRbtMore, mRbtInvestMent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }


    @Override
    protected void initViews() {
        super.initViews();
        mRbtMain = (RadioButton) findViewById(R.id.rbt_main_home);
        mRbtMore = (RadioButton) findViewById(R.id.rbt_main_more);
        mRbtInvestMent = (RadioButton) findViewById(R.id.rbt_main_investment);
        mRbtMain.setOnCheckedChangeListener(mCheckedChangeListener);
        mRbtMore.setOnCheckedChangeListener(mCheckedChangeListener);
        mRbtInvestMent.setOnCheckedChangeListener(mCheckedChangeListener);
    }


    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        mMainFramgent = new MainFragment();
        mCommunityFragment = new MainFragment();
        mMeFragment = new MainFragment();
        mCurrentFragment = mMainFramgent;
        mRbtMain.setChecked(true);
    }


    private CompoundButton.OnCheckedChangeListener mCheckedChangeListener = (compoundButton, b) -> {

        if (b)
            switch (compoundButton.getId()) {
                case R.id.rbt_main_home:
                    checkedFragment(MAIN_FRAGMENT_TAG);
                    break;
                case R.id.rbt_main_investment:
                    checkedFragment(COMMUNITY_FRAGMENT_TAG);
                    break;
                case R.id.rbt_main_more:
                    checkedFragment(ME_FRAGMENT_TAG);
                    break;
            }

    };


    private synchronized void checkedFragment(String tag) {
        BaseFragment baseFragment = null;
        switch (tag) {
            case COMMUNITY_FRAGMENT_TAG:
                baseFragment = mCommunityFragment;
                break;
            case ME_FRAGMENT_TAG:
                baseFragment = mMeFragment;
                break;
            default:
                baseFragment = mMainFramgent;
                break;
        }
        if (!baseFragment.isAdded()) {
            mFragmentManager.beginTransaction().add(R.id.main_activity_fragment, baseFragment).commitAllowingStateLoss();

        }
        mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        mFragmentManager.beginTransaction().show(baseFragment).commit();
        mCurrentFragmentTag = tag;
        mCurrentFragment = baseFragment;
    }

    private void checkedCurrentFragment() {
        switch (mCurrentFragmentTag) {
            case COMMUNITY_FRAGMENT_TAG:
                mRbtInvestMent.setChecked(true);
                break;
            case ME_FRAGMENT_TAG:
                mRbtMore.setChecked(true);
                break;
            default:
                mRbtMain.setChecked(true);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (MAIN_FRAGMENT_TAG.equals(mCurrentFragmentTag)) {
                finish();
            } else {
                mRbtMain.setChecked(true);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
