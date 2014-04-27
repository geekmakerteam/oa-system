package org.geeklub.utils;

import org.geeklub.constant.Values;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.PreferenceManager;



/**
 * 推送工具类
 * @author hp
 *
 */
public class OaPushUtil {

	/**
	 * 获取ApiKey
	 * @param context
	 * @param metaKey
	 * @return
	 */
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String apiKey = null;
		if (context == null || metaKey == null) {
			return null;
		}
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				apiKey = metaData.getString(metaKey);
			}
		} catch (NameNotFoundException e) {

		}
		return apiKey;
	}

	/**
	 * 保存userID到本地
	 * @param context
	 * @param user_id
	 */

	public static void setUserID(Context context,String user_id){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString(Values.USER_ID,user_id);
		editor.commit();
	}

	/**
	 * 得到userID
	 * @param context
	 * @return
	 */
	public static String getUserID(Context context){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(Values.USER_ID, "");
	}


	/**
	 * 保存channelID保存到本地
	 * @param context
	 * @param channel_id
	 */
	public static void setChannelID(Context context,String channel_id){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString(Values.CHANNEL_ID,channel_id);
		editor.commit();
	}


	/**
	 * 得到channelID
	 * @param context
	 * @return
	 */


	public static String getChannelID(Context context){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(Values.CHANNEL_ID, "");

	}

	/**
	 * 设置是否绑定
	 * @param context
	 * @param flag
	 */

	public static void setBind(Context context, boolean flag) {
		String flagStr = "not";
		if (flag) {
			flagStr = "ok";
		}
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sp.edit();
		editor.putString(Values.BIND_TAG, flagStr);
		editor.commit();
	}


	/**
	 * 用share preference来实现是否绑定的开关。
	 * 在ionBind且成功时设置true，
	 * unBind且成功时设置false
	 * @param context
	 * @return 是否绑定
	 */
	public static boolean hasBind(Context context) {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(context);
		String flag = sp.getString(Values.BIND_TAG, "");
		if ("ok".equalsIgnoreCase(flag)) {
			return true;
		}
		return false;
	}




}
