package com.xiong.common.lib.view.wheel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xiong.common.lib.R;

import java.util.List;

/**
 * Created by xionglh on 2018/6/22
 */
public class ChooseWheelAdapter extends AbstractWheelTextAdapter {

    private List<String> mList;

    public ChooseWheelAdapter(Context context, List<String> list, int currentItem, int maxsize, int minsize) {
        super(context, R.layout.view_wheel_list_item, NO_RESOURCE, currentItem, maxsize, minsize);
        this.mList = list;
        setItemTextResource(R.id.tempValue);
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        return super.getItem(index, cachedView, parent);
    }

    @Override
    public int getItemsCount() {
        return mList.size();
    }

    @Override
    public CharSequence getItemText(int index) {
        return mList.get(index) ;
    }
}

