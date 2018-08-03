/**
 * 
 */
package com.xiong.common.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;


/**
 * Created by xionglh on 2017/6/14
 */
public class SharedPreferencesUtil {





	public static void saveStringValue(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(
				SharedPreferencesKey.SHARE_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getStringValue(Context context, String key) {
		SharedPreferences settings = context.getSharedPreferences(
				SharedPreferencesKey.SHARE_FILE_NAME, Context.MODE_PRIVATE);
		return settings.getString(key, "");
	}

	public static void cleanStringValue(Context context, String... keys) {
		for (String key : keys) {
			SharedPreferences settings = context.getSharedPreferences(
					SharedPreferencesKey.SHARE_FILE_NAME, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = settings.edit();
			if (settings.contains(key)) {
				editor.remove(key).commit();
			}
		}
	}


	public static boolean IsCanUseSdCard() {
		try {
			return Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
