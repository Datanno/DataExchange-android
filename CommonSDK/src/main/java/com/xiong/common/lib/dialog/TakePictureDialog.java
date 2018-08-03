package com.xiong.common.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.xiong.common.lib.R;

/**
 * Created by xionglh on 2018/6/14
 */
public class TakePictureDialog extends Dialog {


    public interface TakePictureDialogOnlickLiseter {

        void photographClick();

        void selectFromTheAlbum();
    }

    private TakePictureDialogOnlickLiseter mTakePictureDialogOnlickLiseter;

    public TakePictureDialog(@NonNull Context context) {
        super(context, R.style.common_sdk_dialog);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_take_picture, null);
        Window window = this.getWindow();
        window.setWindowAnimations(R.style.common_sdk_dialog_anima);
        Display display = window.getWindowManager().getDefaultDisplay();
        window.setGravity(Gravity.BOTTOM);
        int width = display.getWidth();
        addContentView(view, new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.findViewById(R.id.txt_pop_account_photograph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTakePictureDialogOnlickLiseter != null)
                    mTakePictureDialogOnlickLiseter.photographClick();
                TakePictureDialog.this.dismiss();
            }
        });
        view.findViewById(R.id.txt_pop_account_albums).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTakePictureDialogOnlickLiseter != null)
                    mTakePictureDialogOnlickLiseter.selectFromTheAlbum();
                TakePictureDialog.this.dismiss();
            }
        });
        view.findViewById(R.id.ll_pop_account_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakePictureDialog.this.dismiss();
            }
        });
    }

    public void setTakePictureDialogOnlickLiseter(TakePictureDialogOnlickLiseter takePictureDialogOnlickLiseter) {
        this.mTakePictureDialogOnlickLiseter = takePictureDialogOnlickLiseter;
    }
}
