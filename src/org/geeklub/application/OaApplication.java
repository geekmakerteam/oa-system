package org.geeklub.application;

import java.util.HashMap;
import java.util.Map;
import org.geeklub.beans.MetroButton;
import org.geeklub.constant.Values;
import org.geeklub.vass.R;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.baidu.frontia.FrontiaApplication;

public class OaApplication extends FrontiaApplication{

	private SharedPreferences sharedPreferences = null;
	private Editor editor = null;


	private static Map<String,MetroButton> mdata = null;



	private static OaApplication OaApplication;


	public static OaApplication getOaApplication(){
		return OaApplication;
	}





	public static Map<String,MetroButton> getmMetroButtonMap(){
		if(mdata == null || mdata.size() == 0){
			mdata = new HashMap<String, MetroButton>();
			mdata.put("公共财产报修申请",new MetroButton("公共财产报修申请", R.drawable.student_repair_form_activity_repair_fuction_icon, R.drawable.main_activity_fuction_icon_bg_1,Values.STUDENT_REPAIR_ACTIVITY));
			mdata.put("学生签到",new MetroButton("学生签到", R.drawable.student_signin_activity_fuction_icon, R.drawable.main_activity_fuction_icon_bg_1,Values.STUDENT_SIGN_IN_ACTIVITY));
			mdata.put("老师点名",new MetroButton("老师点名", R.drawable.teacher_callnames_activity_fuction_icon, R.drawable.main_activity_fuction_icon_bg_1,Values.TEACHER_CALL_NAMES_ACTIVITY));
			mdata.put("公共财产报修审核",new MetroButton("公共财产报修审核", R.drawable.lifeteacher_check_activity_fuction_icon, R.drawable.main_activity_fuction_icon_bg_2,Values.LIFE_TEACHER_CHECK_ACTIVITY));
			mdata.put("公共财产报修确认",new MetroButton("公共财产报修确认", R.drawable.main_activity_fucation_icon_5, R.drawable.main_activity_fuction_icon_bg_2,Values.REPAIR_MAN_CONFIRM_ACTIVITY));
			mdata.put("勤工助学岗位申请", new MetroButton("勤工助学岗位申请", R.drawable.main_activity_fucation_icon_10, R.drawable.main_activity_fuction_icon_bg_1,Values.STUDENT_PART_TIME_JOB_ACTIVITY));
			mdata.put("勤工助学岗位审核", new MetroButton("勤工助学岗位审核", R.drawable.main_activity_fucation_icon_11, R.drawable.main_activity_fuction_icon_bg_1, Values.TEACHER_TIME_JOB_CHECK_ACTIVITY));
		}
		return mdata;
	}










	public Editor getOaEditor(String name,int mode){
		sharedPreferences = getSharedPreferences(name, mode);
		editor = sharedPreferences.edit();
		return editor;
	}

	public SharedPreferences getOaSharedPreferences(String name,int mode){
		sharedPreferences = getSharedPreferences(name, mode);
		return sharedPreferences;
	}




	@Override
	public void onCreate() {
		super.onCreate();
		OaApplication = this;
		
		
		
	}





}
