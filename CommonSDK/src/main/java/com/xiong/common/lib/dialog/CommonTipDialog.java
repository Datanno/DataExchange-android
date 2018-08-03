package com.xiong.common.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiong.common.lib.R;
import com.xiong.common.lib.utils.StrUtils;
/**
 * Created by xionglh on 2018/6/14
 */
public class CommonTipDialog extends Dialog {

    public CommonTipDialog(Context context) {
        super(context);
    }

    public CommonTipDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {

        private Context context;
        private String title;
        private String message;
        private String message2;
        private String positiveButtonText;
        private String negativeButtonText;
        private ImageView mImageView;
        private int mImageRes;
        private ImageView mImgTopCloose;
        private TextView mTitleView;
        private int mBtngBg1;
        private int mBtnBg2;

        private SpannableString spannableString;

        private int mMsgGravity = Gravity.CENTER;

        private int mMsgGravity2 = Gravity.CENTER;

        private OnClickListener positiveButtonClickListener,
                negativeButtonClickListener,mclooseButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setImageViewShow(boolean isShow) {
            if (mImageView == null)
                return this;
            if (isShow)
                mImageView.setVisibility(View.VISIBLE);
            else
                mImageView.setVisibility(View.GONE);
            return this;
        }

        public Builder setImageResId(int resId) {
            mImageRes = resId;
            return this;
        }

        public void setMsgGravity(int gravity) {
            mMsgGravity = gravity;
        }

        public void setMsgGravity2(int gravity) {
            mMsgGravity2 = gravity;
        }

        public Builder setImageViewTopClooseShow(boolean isShow) {
            if (mImgTopCloose == null)
                return this;
            if (isShow)
                mImgTopCloose.setVisibility(View.VISIBLE);
            else
                mImgTopCloose.setVisibility(View.INVISIBLE);
            return this;
        }

        public Builder setTitleTextViewShow(boolean isShow) {
            if (mTitleView == null)
                return this;
            if (isShow)
                mTitleView.setVisibility(View.VISIBLE);
            else
                mTitleView.setVisibility(View.GONE);
            return this;
        }

        public Builder setSpannableString(SpannableString spannableString) {
            this.spannableString = spannableString;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setMessage2(String message) {
            this.message2 = message;
            return this;
        }

        public Builder setMessag2(int message) {
            this.message2 = (String) context.getText(message);
            return this;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, int btnBg1,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            this.mBtngBg1 = btnBg1;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, int btnBg1,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            this.mBtngBg1 = btnBg1;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }



        public Builder setClooseButton(OnClickListener listener) {
            this.mclooseButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, int btnBg2,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            this.mBtnBg2 = btnBg2;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, int btnBg2,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            this.mBtnBg2 = btnBg2;
            return this;
        }


        public CommonTipDialog create() {
            final CommonTipDialog dialog = new CommonTipDialog(context,
                    R.style.common_sdk_dialog);
            View layout = LayoutInflater.from(context).inflate(R.layout.view_common_tip_dialog, null);

            mTitleView = (TextView) layout.findViewById(R.id.common_tip_dialog_title);
            // set the dialog title
            if (title != null)
                ((TextView) layout.findViewById(R.id.common_tip_dialog_title))
                        .setText(title);
            else
                ((TextView) layout.findViewById(R.id.common_tip_dialog_title))
                        .setText(context.getString(R.string.dialog_title));
            mImageView = (ImageView) layout.findViewById(R.id.common_tip_dialog_title_success);
            mImgTopCloose = (ImageView) layout.findViewById(R.id.common_tip_dialog_title_closse);

            LinearLayout llCommCag = (LinearLayout) layout.findViewById(R.id.ll_tip_dialog_cancle_button);
            LinearLayout llCommPag = (LinearLayout) layout.findViewById(R.id.ll_tip_dialog_positive_button);
            if (mImageRes > 0)
                mImageView.setImageResource(mImageRes);
            // set the confirm button
            TextView positive = (TextView) layout.findViewById(R.id.common_tip_dialog_positive_button);
            if (positiveButtonText != null) {
                positive.setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    positive.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_POSITIVE);
                        }
                    });

                }
                if (mBtngBg1 != 0) {
                    llCommPag.setBackgroundResource(mBtngBg1);
                    positive.setBackgroundResource(mBtngBg1);
                    positive.setTextColor(context.getResources().getColor(R.color.white));
                }
            } else {
                positive.setVisibility(View.GONE);
                llCommPag.setVisibility(View.GONE);
            }
            // set the cancel button
            TextView cancel = (TextView) layout.findViewById(R.id.common_tip_dialog_cancle_button);
            if (negativeButtonText != null) {
                cancel.setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    cancel.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
                if (mBtnBg2 != 0) {
                    cancel.setTextColor(context.getResources().getColor(R.color.black_big_text));
                    llCommCag.setBackgroundResource(mBtnBg2);
                    cancel.setBackgroundResource(mBtnBg2);

                }
            } else {
                cancel.setVisibility(View.GONE);
                llCommCag.setVisibility(View.GONE);
            }
            LinearLayout llBtnShow = (LinearLayout) layout.findViewById(R.id.ll_btn_buttom_show);
            if (StrUtils.isEmpty(positiveButtonText) && StrUtils.isEmpty(negativeButtonText)) {
                llBtnShow.setVisibility(View.GONE);
            } else {
                llBtnShow.setVisibility(View.VISIBLE);
            }
            View viewLine = layout.findViewById(R.id.view_common_tip_dialog_message);
            View viewBtnShow = layout.findViewById(R.id.view_common_tip_dialog_btn_show);

            if ((cancel.getVisibility()==View.VISIBLE&&positive.getVisibility()==View.VISIBLE))
                viewBtnShow.setVisibility(View.VISIBLE);
            else
                viewBtnShow.setVisibility(View.GONE);

            if (negativeButtonText == null && positiveButtonText == null)
                viewLine.setVisibility(View.GONE);
            else
                viewLine.setVisibility(View.VISIBLE);
            TextView txtMsg1 = (TextView) layout.findViewById(R.id.common_tip_dialog_message_1);
            if (message != null || spannableString != null) {
                if (spannableString != null)
                    txtMsg1.setText(spannableString);
                else
                    txtMsg1.setText(message);
                txtMsg1.setGravity(mMsgGravity);
            } else {
                txtMsg1.setVisibility(View.GONE);
            }
            TextView txtMsg = (TextView) layout.findViewById(R.id.common_tip_dialog_message_2);
            if (message2 != null) {
                txtMsg.setText(message2);
                txtMsg1.setGravity(mMsgGravity2);
                txtMsg.setVisibility(View.VISIBLE);
            } else {
                txtMsg.setVisibility(View.GONE);
            }
            mImgTopCloose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if(mclooseButtonClickListener!=null)
                        mclooseButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEUTRAL);
                }
            });
            Display display = dialog.getWindow().getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            dialog.addContentView(layout, new ViewGroup.LayoutParams(width, height));
            return dialog;
        }


    }
}
