package com.xiong.common.lib.config;

import android.net.Uri;
import android.os.Environment;

import com.xiong.common.lib.tools.SystemLog;

import java.io.File;

public class Constants {


    private Constants() {
        // empty
    }

    public static final int SDCARD_SMALLESET_LEVEL = SystemLog.LEVEL_WARNING;
    public static final String  PUSH_SECRET="bd2194cee17f32891d07a2f4d68f4006";
    // log path
    public static final String PATH_LOG = "/bottos/log/";
    public static final String LOG_FILE_DIR = Environment.getExternalStorageDirectory() + PATH_LOG;
    public static final String LOG_FILE_NAME = "bottos_print.log";
    // Image path
    public static final String PATH_TEMP = "/bottos/images/temp/";
    public static final String PATH_IMAGE_CACHE = "/bottos/images/cache/";
    public static final String IMAGE_CAMERA_NAME = "tmp_camera.jpg";
    public static final Uri IMAGE_CAMERA_URI = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + PATH_TEMP, IMAGE_CAMERA_NAME));
    // Image size
    public static final int USER_IMAGE_SIZE_WIDTH = 320;
    public static final int USER_IMAGE_SIZE_HEIGHT = 320;

    public static final String INVEST_SOURCE_APP = "Android";



}
