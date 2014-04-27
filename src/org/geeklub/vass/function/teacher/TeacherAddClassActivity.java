package org.geeklub.vass.function.teacher;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TeacherAddClassActivity extends SherlockFragmentActivity implements OnClickListener,OnWheelChangedListener{
	private static final String tag = "TeacherAddClassActivity";
	private WheelView wv_zhou;
	private WheelView wv_xingqi;
	private WheelView wv_jie;
	private FloatLabel fl_course_name ;
	private Button btn_submit;
	private Activity mactivity;
	private String str_zhou;
	private String str_xing_qi;
	private String str_jie;





	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		mactivity = this;

		setContentView(R.layout.teacher_add_class_activity);

		initView();
	}
	private void initView() {
		fl_course_name = (FloatLabel) findViewById(R.id.teacher_add_class_activity_tv_course_name);
		wv_zhou = (WheelView) findViewById(R.id.teacher_add_class_activity_zhou);
		wv_xingqi = (WheelView) findViewById(R.id.teacher_add_class_activity_xingqi);
		wv_jie = (WheelView) findViewById(R.id.teacher_add_class_activity_jie);
		btn_submit = (Button) findViewById(R.id.teacher_add_class_activity_btn_submit);
		btn_submit.setOnClickListener(this);

		//
		wv_zhou.setViewAdapter(new ZhouArrayAdapter(this, Values.ZHOU, 1));
		wv_zhou.setCurrentItem(1);
		wv_zhou.addChangingListener(this);

		//		
		wv_xingqi.setViewAdapter(new XingQiArrayAdapter(this, Values.XING_QI, 1));
		wv_xingqi.setCurrentItem(1);
		wv_xingqi.addChangingListener(this);


		wv_jie.setViewAdapter(new JieArrayAdapter(this, Values.JIE, 1));
		wv_jie.setCurrentItem(1);
		wv_jie.addChangingListener(this);
		//



		updateCourseTime(wv_zhou, wv_xingqi, wv_jie);



	}




	class ZhouArrayAdapter extends ArrayWheelAdapter<String>{
		private int currentValue;
		private int currentItem;

		public ZhouArrayAdapter(Context context, String[] items, int current) {
			super(context, items);
			this.currentValue = current;
			setTextSize(16);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);

			if (currentItem == currentValue) {
				view.setTextColor(0xFF0000F0);
			}
			view.setTypeface(Typeface.SANS_SERIF);
		}

		@Override
		public View getItem(int index, View convertView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, convertView, parent);
		}

	}


	class XingQiArrayAdapter extends ArrayWheelAdapter<String>{
		private int currentValue;
		private int currentItem;

		public XingQiArrayAdapter(Context context, String[] items, int current) {
			super(context, items);
			this.currentValue = current;
			setTextSize(16);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);

			if (currentItem == currentValue) {
				view.setTextColor(0xFF0000F0);
			}
			view.setTypeface(Typeface.SANS_SERIF);
		}

		@Override
		public View getItem(int index, View convertView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, convertView, parent);
		}

	}


	class JieArrayAdapter extends ArrayWheelAdapter<String>{
		private int currentValue;
		private int currentItem;

		public JieArrayAdapter(Context context, String[] items, int current) {
			super(context, items);
			this.currentValue = current;
			setTextSize(16);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);

			if (currentItem == currentValue) {
				view.setTextColor(0xFF0000F0);
			}
			view.setTypeface(Typeface.SANS_SERIF);
		}

		@Override
		public View getItem(int index, View convertView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, convertView, parent);
		}

	}





	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		Log.i(tag, "updateCourseTime");
		updateCourseTime(wv_zhou, wv_xingqi, wv_jie);
	}





	private void updateCourseTime(WheelView zhou, WheelView xingqi, WheelView jie) {
		Log.i(tag,"老师选择了"+Values.ZHOU[zhou.getCurrentItem()]+";"+Values.XING_QI[xingqi.getCurrentItem()]+";"+Values.JIE[jie.getCurrentItem()]);
		//
		//		wv_zhou.setCurrentItem(zhou.getCurrentItem());
		str_zhou = Values.ZHOU[zhou.getCurrentItem()];
		//		
		//		wv_xingqi.setCurrentItem(xingqi.getCurrentItem());
		str_xing_qi = Values.XING_QI[xingqi.getCurrentItem()];
		//		
		//		wv_jie.setCurrentItem(jie.getCurrentItem());
		str_jie = Values.JIE[jie.getCurrentItem()];
	}
	@Override
	public void onClick(View v) {

		Log.i(tag, "课程名："+fl_course_name.getEditText().getText().toString());

		String courseName = fl_course_name.getEditText().getText().toString();

		if(!TextUtils.isEmpty(courseName)){

			RequestParams params = new RequestParams();
			params.put(Values.TEACHER_ADD_CLASS_ZHOU, str_zhou);
			params.put(Values.TEACHER_ADD_CLASS_JIE, str_jie);
			params.put(Values.TEACHER_ADD_CLASS_XING_QI, str_xing_qi);
			params.put("courseName",courseName);

			OaAsyncHttpClient.post(Url.TEACHER_SUBMIE_COURSE_INFO, params, new JsonHttpResponseHandler(){

				@Override
				public void onSuccess(int statusCode, JSONObject response) {
					super.onSuccess(statusCode, response);

					try {
						if(response.get("status").equals("0")){
							Toast.makeText(mactivity, "添加课程成功...", Toast.LENGTH_SHORT).show();
							finish();
						}

					} catch (Exception e) {

					}

				}

			}, mactivity);

		}else{
			Toast.makeText(mactivity, "请填写课程名...", Toast.LENGTH_SHORT).show();
		}



	}

}
