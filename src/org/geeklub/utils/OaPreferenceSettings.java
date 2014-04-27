package org.geeklub.utils;

import org.geeklub.constant.Values;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


/**
 * 保存用户的偏好项设置，现在用来保存密码和用户名
 * @author hp
 *
 */
public class OaPreferenceSettings {

	/**
	 * 保存用户名
	 * @param context
	 * @param user_name
	 */

	public static void setUserName(Context context,String user_name){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString(Values.USER_NAME,user_name);
		editor.commit();
	}

	/**
	 * 得到用户名
	 * @param context
	 * @return
	 */

	public static String getUserName(Context context){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(Values.USER_NAME, "");
	}



	/**
	 * 保存用户的密码
	 * @param context
	 * @param pass_word
	 */
	public static void setPassWord(Context context,String pass_word){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.putString(Values.PASS_WORD,pass_word);
		editor.commit();
	}


	/**
	 * 得到用户的密码
	 * @param context
	 * @return
	 */
	public static String getPassWord(Context context){
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		return sharedPreferences.getString(Values.PASS_WORD, "");

	}
	
	
	

}
