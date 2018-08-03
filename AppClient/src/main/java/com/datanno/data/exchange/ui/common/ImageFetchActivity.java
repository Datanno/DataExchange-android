package com.datanno.data.exchange.ui.common;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Window;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiong.common.arouter.url.ARouterPageUrl;
import com.xiong.common.lib.config.Constants;
import com.xiong.common.lib.utils.FileUtil;
import com.datanno.data.exchange.R;
import com.datanno.data.exchange.common.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


@Route(path = ARouterPageUrl.APP_ACTIVITY_IMAGE_FETCH)
public class ImageFetchActivity extends BaseActivity {
    public static final String ACTION_TYPE = "com.augmentum.ball.common.activity.ImageFetchActivity.ACTION_TYPE";
    public static final String IMG_NAME_TAG = "IMG_NAME_TAG";
    public final static int ACTION_TYPE_CAMERA = 0x001;
    public final static int ACTION_TYPE_PICTURE = 0x002;
    public final static int ACTION_TYPE_CROP = 0x003;
    private final static int ACTION_TYPE_FINISH = 0x004;
    private int mActionType = 0;
    private String mPath;

    private Uri mImgeCameraUri;
    private Uri mImageCaptureUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_image_fetch);
        initData();

    }

    @Override
    protected void initViews() {

    }

    private void initData() {
        mImgeCameraUri = Constants.IMAGE_CAMERA_URI;
        mPath = mImgeCameraUri.getPath();
        String mImageName = getIntent().getStringExtra(IMG_NAME_TAG);
        mActionType = getIntent().getIntExtra(ACTION_TYPE, ACTION_TYPE_CAMERA);
        mImageCaptureUri = Uri.fromFile(new File(
                Environment.getExternalStorageDirectory() + Constants.PATH_TEMP,
                mImageName));
        switch (mActionType) {
            case ACTION_TYPE_CAMERA:
                gotoCameraActivity();
                break;
            case ACTION_TYPE_PICTURE:
                gotoPicturePickActivity();
                break;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTION_TYPE_PICTURE:
                    String fileRealPath;
                    Uri uriPath = intent.getData();
                    if (uriPath != null) {
                        String imgUriPath = uriPath.getPath();
                        Cursor cursor = getContentResolver().query(
                                intent.getData(), null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            int columnIndex = cursor
                                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            fileRealPath = cursor.getString(columnIndex);
                            cursor.close();
                        } else {
                            fileRealPath = imgUriPath;
                        }
                    } else {
                        fileRealPath = mImageCaptureUri.getPath();
                    }
                    mPath = fileRealPath;
                case ACTION_TYPE_CROP:
                    if (FileUtil.exists(mPath)) {
                        gotoCropImage();
                    } else {
                        finishActivity(false);
                    }
                    break;
                case ACTION_TYPE_FINISH:
                    if (!FileUtil.exists(mImageCaptureUri
                            .getPath())) {
                        Bitmap bmap = intent.getParcelableExtra("data");
                        FileOutputStream foutput = null;
                        try {
                            foutput = new FileOutputStream(
                                    mImageCaptureUri.getPath());
                            bmap.compress(Bitmap.CompressFormat.JPEG, 100, foutput);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            if (null != foutput) {
                                try {
                                    foutput.close();
                                    bmap.recycle();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    finishActivity(true);
                    break;
                default:
                    finishActivity(false);
                    break;
            }
        } else {
            finishActivity(false);
        }

    }

    private void gotoCameraActivity() {
        startCameraActivity(ACTION_TYPE_CROP);
        mActionType = ACTION_TYPE_CROP;
    }

    private void gotoPicturePickActivity() {
        startPicturePickActivity(ACTION_TYPE_PICTURE);
        mActionType = ACTION_TYPE_CROP;
    }

    private void gotoCropImage() {
        startCropImage(Uri.fromFile(new File(mPath)), ACTION_TYPE_FINISH);
        mActionType = ACTION_TYPE_FINISH;
    }

    private void finishActivity(boolean isOk) {
        if (isOk) {
            Intent data = new Intent();
            data.setData(mImageCaptureUri);
            setResult(RESULT_OK, data);
            finish();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private void startPicturePickActivity(int requestCode) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, requestCode);
    }


    private void startCameraActivity(int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                mImgeCameraUri);
        startActivityForResult(intent, requestCode);
    }


    private void startCropImage(Uri uri, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", Constants.USER_IMAGE_SIZE_WIDTH);
        intent.putExtra("outputY", Constants.USER_IMAGE_SIZE_HEIGHT);
        intent.putExtra("output", mImageCaptureUri);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("return-data", false);
        startActivityForResult(intent, requestCode);
    }
}