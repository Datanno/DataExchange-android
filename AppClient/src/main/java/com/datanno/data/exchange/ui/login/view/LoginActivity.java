package com.datanno.data.exchange.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.xiong.common.arouter.url.ARouterPageUrl;
import com.xiong.common.lib.dialog.WheelviewDialog;
import com.xiong.common.lib.utils.ActivityPageManager;
import com.xiong.common.lib.utils.DialogUtil;
import com.xiong.common.lib.utils.GlideUtil;
import com.xiong.common.lib.utils.SlinUtil;
import com.xiong.common.lib.utils.StrUtils;
import com.xiong.common.lib.view.CircleImageView;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseCommonActivity;
import com.datanno.data.exchange.ui.login.contract.LoginContract;
import com.datanno.data.exchange.ui.login.presenter.LoginPresenterImp;
import com.jakewharton.rxbinding.view.RxView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Route(path = ARouterPageUrl.APP_ACTIVITY_LOGIN)
public class LoginActivity extends BaseCommonActivity<LoginContract.View, LoginPresenterImp> implements LoginContract.View {

    private String mIconUrl = "";
    private String mMobile = "";
    public static final int RESQUEST_CODE = 0X233;
    public static final int RESULT_CODE = 0X234;

    private TextView mTxtCountrycode;

    private EditText mEditTextUserName, mEditTextPassword;
    private CircleImageView mCircleImageView;
    private ImageView mImgDeteleUserName;


    private WheelviewDialog mWheelviewDialog;

    @Override
    protected LoginPresenterImp createPersenter() {
        return new LoginPresenterImp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitle(R.string.login_activity_title);
        showLeftButton();
        mIconUrl = mPersenter.getUserImgUrl();
        mMobile = mPersenter.getMobile();
        mWheelviewDialog = new WheelviewDialog(this);
        mEditTextUserName = (EditText) findViewById(R.id.edt_login_activity_edittext_username);
        mEditTextPassword = (EditText) findViewById(R.id.edt_login_activity_edittext_password);
        CheckBox mRbtShowPwd = (CheckBox) findViewById(R.id.rb_login_activity_edittext_password_detele);
        mCircleImageView = (CircleImageView) findViewById(R.id.crv_login_user_icon);
        mImgDeteleUserName = (ImageView) findViewById(R.id.img_login_activity_edittext_username_detele);
        Button btnLogin = (Button) findViewById(R.id.login_activity_button_login);
        TextView txtForgetPwd = (TextView) findViewById(R.id.login_activity_textview_forget_pwd);
        mTxtCountrycode = (TextView) findViewById(R.id.txt_activity_login_countrycode);
        mEditTextUserName.addTextChangedListener(new MobielTextWatcher());
        showLeftButtonWithListener(t -> finish());
        mRbtShowPwd.setOnCheckedChangeListener((t, d) -> {
            if (d)
                mEditTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            else
                mEditTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            Editable etable = mEditTextPassword.getText();
            Selection.setSelection(etable, etable.length());
        });
        RxView.clicks(btnLogin).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(t -> mPersenter.login());
        mImgDeteleUserName.setOnClickListener(t -> mEditTextUserName.setText(""));
        if (!StrUtils.isEmpty(mIconUrl))
            GlideUtil.getInstance().setDefultActivityImageView(this, mCircleImageView, R.mipmap.icon_defult_user, mIconUrl);
        if (!StrUtils.isEmpty(mMobile)) {
            mEditTextUserName.setText(SlinUtil.mobileFilter(mMobile).replace(" ", ""));
        }
        findViewById(R.id.txt_login_china_en).setOnClickListener(t -> {
            DialogUtil.showTipDialog(this, -1, getString(R.string.dialog_title), getString(R.string.app_system_anguage),
                    getString(R.string.dialog_sure), R.drawable.common_sdk_bg_buttom_r_dialog, false, new DialogUtil.DialogMyClickListener() {
                        @Override
                        public void doPositive() {
                            mPersenter.languageSwitching();
                            ActivityPageManager.getInstance().finishAllActivity();
                            startAct(ARouterPageUrl.APP_ACTIVITY_LOGIN);
                        }

                        @Override
                        public void doNegative() {

                        }
                    });
        });
        List<String> lists = Arrays.asList(getResources().getStringArray(R.array.country_code));
        mTxtCountrycode.setText(lists.get(0));
        mTxtCountrycode.setOnClickListener(view -> showDilog(lists));

    }


    private WheelviewDialog.WheelviewDialogOnCliclListener mWheelviewDialogOnCliclListener = new WheelviewDialog.WheelviewDialogOnCliclListener() {
        @Override
        public void onSureListener(String values, int postion) {
            mTxtCountrycode.setText(values);
        }

        @Override
        public void onChangedListener(String values, int postion) {

        }
    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESQUEST_CODE && resultCode == RESULT_CODE) {
            finish();
        }
    }


    @Override
    public void dissProgress() {
        dismissProgressDialog();
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public String getMobile() {
        String userName = mEditTextUserName.getText().toString();
        if (userName.contains("*"))
            userName = mMobile;
        return userName;
    }

    @Override
    public String getPwd() {
        return mEditTextPassword.getText().toString();
    }

    @Override
    public void finshActivity() {
        finish();
    }

    @Override
    public void showDilog(List<String> pars) {
        mWheelviewDialog.setData(pars, mWheelviewDialogOnCliclListener);
        mWheelviewDialog.show();
    }


    private class MobielTextWatcher implements TextWatcher {

        private int mBeforeLength;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            mBeforeLength = after;

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int strLength = s.length();
            if (s.toString().contains("*") && mBeforeLength < strLength) {
                mEditTextUserName.setText("");
                mMobile = "";
                return;
            }
            if (strLength == 11 && s.toString().equals(mMobile) || !StrUtils.isEmpty(mIconUrl)) {
                Glide.with(LoginActivity.this).load(mIconUrl).into(mCircleImageView);
            }
            if (strLength < 1) {
                mCircleImageView.setImageResource(R.mipmap.icon_account_image);
                mImgDeteleUserName.setVisibility(View.GONE);
            } else {
                mImgDeteleUserName.setVisibility(View.VISIBLE);
            }

        }
    }


}
