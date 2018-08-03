package com.xiong.common.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xiong.common.lib.R;
import com.xiong.common.lib.dialog.CommonTipDialog;

public class DialogUtil {

    public interface DialogMyClickListener {
        void doPositive();

        void doNegative();

    }


    public static void ShowDialog(Context context, String content) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(
                context);
        alterDialog.setPositiveButton(R.string.dialog_sure, (t, v) -> t.dismiss());
        alterDialog.setMessage(content);
        alterDialog.create().show();
    }


    public static void ShowDialogTip(Context context, String content) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(
                context);
        alterDialog.setMessage(content);
        alterDialog.create().show();
        alterDialog.setImageViewTopClooseShow(true);

    }


    public static void ShowDialog(final Context context, String content,
                                  final Intent intent) {

        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(
                context);
        alterDialog.setImageResId(R.drawable.icon_dialog_tanhao);
        alterDialog.setPositiveButton(R.string.dialog_sure, (t, v) -> {
            context.startActivity(intent);
            t.dismiss();
        });
        alterDialog.setNegativeButton(R.string.dialog_cancle, (t, v) -> t.dismiss());
        alterDialog.setMessage(content);
        alterDialog.create().show();
    }

    public static void ShowDialog(final Context context, String content,
                                  final boolean isFish) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(
                context);

        alterDialog.setPositiveButton(R.string.dialog_sure, (t, v) -> {
            if (isFish) {
                Activity activity = (Activity) context;
                activity.finish();
            }
            t.dismiss();
        });
        alterDialog.setMessage(content);
        alterDialog.create().show();
    }

    public static void ShowDialogJu(final Context context, int StringId, final Intent intent) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(R.string.dialog_sure, (t, v) -> {
            t.dismiss();
            context.startActivity(intent);
            ((Activity) context).finish();
        });
        alterDialog.setNegativeButton(R.string.dialog_cancle, (t, v) -> ((Activity) context).finish());
        alterDialog.setMessage(StringId);
        alterDialog.setImageResId(R.drawable.icon_dialog_tanhao);
        CommonTipDialog dialog = alterDialog.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    public static void showTipDialog(Context context, int imgRes, String content, String btnStr, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnStr, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });
        alterDialog.setImageResId(imgRes);
        alterDialog.setMessage(content);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewShow(true);
        alterDialog.setImageViewTopClooseShow(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(Context context, int imgRes, String content, String btnPostiviy,
                                     String negatView, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnPostiviy, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });

        alterDialog.setNegativeButton(negatView, (t, v) -> {
            t.dismiss();
            dialogClickListener.doNegative();
        });
        alterDialog.setImageResId(imgRes);
        alterDialog.setMessage(content);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewShow(true);
        alterDialog.setImageViewTopClooseShow(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(Context context, int imgRes, String content) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setImageResId(imgRes);
        alterDialog.setMessage(content);
        CommonTipDialog dialog = alterDialog.create();
        if (imgRes > 0) {
            alterDialog.setImageViewShow(true);
            alterDialog.setImageResId(imgRes);
        }else{
            alterDialog.setImageViewShow(false);
        }
        alterDialog.setImageViewTopClooseShow(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(Context context, int imgRes, String content, int showGraivte) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setImageResId(imgRes);
        alterDialog.setMessage(content);
        alterDialog.setMsgGravity(showGraivte);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewShow(true);
        alterDialog.setImageViewTopClooseShow(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(Context context, int imgRes, String title, String content, int showGraivte) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setImageResId(imgRes);
        alterDialog.setMessage(content);
        alterDialog.setTitle(title);
        alterDialog.setMsgGravity(showGraivte);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewShow(true);
        alterDialog.setImageViewTopClooseShow(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(Context context, int imgRes, String title, String btnPostiviy, int positiveBg, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnPostiviy, positiveBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });
        alterDialog.setImageResId(imgRes);
        alterDialog.setTitle(title);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewShow(true);
        alterDialog.setImageViewTopClooseShow(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(Context context, int imgRes, String title, String content, String btnPostiviy, int positiveBg, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnPostiviy, positiveBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });
        alterDialog.setImageResId(imgRes);
        alterDialog.setTitle(title);
        alterDialog.setMessage(content);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewShow(true);
        if (imgRes > 0) {
            alterDialog.setImageViewShow(true);
            alterDialog.setImageResId(imgRes);
        }else{
            alterDialog.setImageViewShow(false);
        }
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(final Context context, int imgRes, String title, final String content, String btnPostiviy, int positiveBg, final boolean isFhis, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnPostiviy, positiveBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });
        alterDialog.setClooseButton((t, v) -> {
            if (isFhis) {
                Activity activity = (Activity) context;
                activity.finish();
            }
        });
        alterDialog.setImageResId(imgRes);
        alterDialog.setTitle(title);
        alterDialog.setMessage(content);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewShow(true);
        alterDialog.setImageViewTopClooseShow(false);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public static void showTipDialog(Context context, int imgRes, String title, String content, String btnPostiviy,
                                     String negatView, int positiveBg, int negativeBg, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnPostiviy, positiveBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });
        alterDialog.setNegativeButton(negatView, negativeBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doNegative();
        });

        alterDialog.setMessage(content);
        alterDialog.setTitle(title);
        CommonTipDialog dialog = alterDialog.create();
        if (imgRes > 0) {
            alterDialog.setImageViewShow(true);
            alterDialog.setImageResId(imgRes);
        }else{
            alterDialog.setImageViewShow(false);
        }
        alterDialog.setImageViewTopClooseShow(true);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void showTipDialog(Context context, int imgRes, String title, String content, String btnPostiviy,
                                     String negatView, int positiveBg, int negativeBg,boolean isShowClooseImg, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnPostiviy, positiveBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });
        alterDialog.setNegativeButton(negatView, negativeBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doNegative();
        });

        alterDialog.setMessage(content);
        alterDialog.setTitle(title);
        CommonTipDialog dialog = alterDialog.create();
        if (imgRes > 0) {
            alterDialog.setImageViewShow(true);
            alterDialog.setImageResId(imgRes);
        }else{
            alterDialog.setImageViewShow(false);
        }
        alterDialog.setImageViewTopClooseShow(isShowClooseImg);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    public static void showTipDialog(final Context context, int imgRes, String title, final String content, int showGraivte, boolean showTopCloose, String btnPostiviy, int positiveBg, final DialogMyClickListener dialogClickListener) {
        CommonTipDialog.Builder alterDialog = new CommonTipDialog.Builder(context);
        alterDialog.setPositiveButton(btnPostiviy, positiveBg, (t, v) -> {
            t.dismiss();
            dialogClickListener.doPositive();
        });
        alterDialog.setMsgGravity(showGraivte);
        alterDialog.setImageResId(imgRes);
        alterDialog.setTitle(title);
        alterDialog.setMessage(content);
        CommonTipDialog dialog = alterDialog.create();
        alterDialog.setImageViewTopClooseShow(showTopCloose);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }
}
