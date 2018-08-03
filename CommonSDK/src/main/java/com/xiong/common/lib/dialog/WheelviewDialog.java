package com.xiong.common.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.xiong.common.lib.R;
import com.xiong.common.lib.view.wheel.ChooseWheelAdapter;
import com.xiong.common.lib.view.wheel.OnWheelChangedListener;
import com.xiong.common.lib.view.wheel.WheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xionglh on 2018/6/14
 */
public class WheelviewDialog extends Dialog {


    public interface WheelviewDialogOnCliclListener {
        void onSureListener(String values, int postion);

        void onChangedListener(String values, int postion);
    }


    private static final int mMaxTextSize = 15;
    private static final int mMinTextSize = 14;


    private TextView mTxtDialogCancle, mTxtDialogSure, mTxtTitle;

    private WheelviewDialogOnCliclListener mDialogOnCliclListener;


    public WheelviewDialog(@NonNull Context context) {
        super(context, R.style.common_sdk_dialog);
        init(context);
    }

    private ChooseWheelAdapter mChooseWheelAdapter;
    private List<String> mData = new ArrayList<>();
    private WheelView mWheelView;

    private String mCurrentData = "";
    private int mCurrentPostion = 0;

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_wheel_view, null);
        Window window = this.getWindow();
        window.setWindowAnimations(R.style.common_sdk_dialog_anima);
        Display display = window.getWindowManager().getDefaultDisplay();
        window.setGravity(Gravity.BOTTOM);
        int width = display.getWidth();
        addContentView(view, new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        mTxtDialogCancle = (TextView) view.findViewById(R.id.btn_wheel_clan);
        mTxtDialogSure = (TextView) view.findViewById(R.id.btn_wheel_sure);
        mTxtTitle = (TextView) view.findViewById(R.id.txt_wheel_title);
        mWheelView = (WheelView) view.findViewById(R.id.wl_view_show);
        mWheelView.setVisibleItems(8);
        mWheelView.setCurrentItem(0);
        mTxtDialogSure.setOnClickListener(mSureOnClisnter);
        mTxtDialogCancle.setOnClickListener(mOnWeelChanlOnclcick);
    }

    private View.OnClickListener mSureOnClisnter = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mDialogOnCliclListener.onSureListener(mCurrentData, mCurrentPostion);
            WheelviewDialog.this.dismiss();
        }
    };

    private View.OnClickListener mOnWeelChanlOnclcick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            WheelviewDialog.this.dismiss();
        }
    };

    public void setData(List<String> data, WheelviewDialogOnCliclListener dialogOnCliclListener) {
        if (data.size() < 1)
            return;
        mData.clear();
        mData.addAll(data);
        mCurrentData = data.get(0);
        this.mDialogOnCliclListener = dialogOnCliclListener;
        mChooseWheelAdapter = new ChooseWheelAdapter(this.getContext(), data, 0, mMaxTextSize, mMinTextSize);
        mWheelView.setViewAdapter(mChooseWheelAdapter);
        mWheelView.addChangingListener(mOnWheelChangedListener);
    }

    @Override
    public void show() {
        if (mData.size() < 1 || mDialogOnCliclListener == null) {
            try {
                throw new Exception("Please add value and add WheelviewDialogOnCliclListener ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.show();
    }

    public void setTxtColor(@IdRes int colors) {
        mTxtDialogCancle.setTextColor(colors);
        mTxtDialogSure.setTextColor(colors);
        mTxtTitle.setTextColor(colors);
    }


    private OnWheelChangedListener mOnWheelChangedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            String currentText = (String) mChooseWheelAdapter.getItemText(wheel.getCurrentItem());
            setTextviewSize(currentText, mChooseWheelAdapter);
            mCurrentData = mData.get(newValue);
            mCurrentPostion = newValue;
            mDialogOnCliclListener.onChangedListener(mCurrentData, mCurrentPostion);
        }
    };

    private void setTextviewSize(String curriteItemText, ChooseWheelAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            } else {
                textvew.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            }
        }
    }
}
