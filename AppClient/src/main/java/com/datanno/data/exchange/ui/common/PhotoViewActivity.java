package com.datanno.data.exchange.ui.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiong.common.arouter.url.ARouterPageUrl;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseActivity;

import java.util.List;


@Route(path = ARouterPageUrl.APP_ACTIVITY_PHOTO_VIEW)
public class PhotoViewActivity extends BaseActivity {


    private ViewPager mPager;
    private TextView mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo_view);
        initViews();
    }

    @Override
    protected void initViews() {
        int mPagerPosition = getIntent().getIntExtra("index", 0);
        List<String> urls = getIntent().getStringArrayListExtra("photo_views");

        mPager = (ViewPager) findViewById(R.id.vp_phone_view_pager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(
                getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        mIndicator = (TextView) findViewById(R.id.vp_phone_view_indicator);

        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager
                .getAdapter().getCount());
        mIndicator.setText(text);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator,
                        arg0 + 1, mPager.getAdapter().getCount());
                mIndicator.setText(text);
            }

        });
        mPager.setCurrentItem(mPagerPosition);
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        private List<String> fileList;

        private ImagePagerAdapter(FragmentManager fm,List<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }


        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return PhotoViewFragment.newInstance(fileList.get(position));
        }

    }


}
