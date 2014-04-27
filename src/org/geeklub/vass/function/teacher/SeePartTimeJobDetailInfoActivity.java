package org.geeklub.vass.function.teacher;

import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.json.JSONException;
import org.json.JSONObject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

@ContentView(R.layout.teacher_see_part_time_job_detail_info_activity)

public class SeePartTimeJobDetailInfoActivity extends RoboActivity implements OnClickListener,OnCheckedChangeListener{

	private static final String tag = "TeacherSeePartTimeJobDetailInfoActivity";
	private Bundle data;
	//	private Bundle args;
	private Activity mactivity;

	private String wish_status = null;

	@InjectView(R.id.teacher_see_part_time_job_detail_info_btn_submit)        Button      btn_submit;
	@InjectView(R.id.teacher_see_part_time_job_detail_info_iv_picture)        ImageView   iv_picture;

	@InjectView(R.id.teacher_see_part_time_job_detail_info_tv_name)           TextView    tv_name;
	@InjectView(R.id.teacher_see_part_time_job_detail_info_tv_teacher_choice) TextView    tv_choice;
	@InjectView(R.id.teacher_see_part_time_job_detail_info_tv_title)          TextView    tv_title;

	@InjectView(R.id.teacher_see_part_time_job_detail_info_rb_wish_1)         RadioButton rb_wish_1;
	@InjectView(R.id.teacher_see_part_time_job_detail_info_rb_wish_2)         RadioButton rb_wish_2;
	@InjectView(R.id.teacher_see_part_time_job_detail_info_rb_wish_refuse)         RadioButton rb_wish_refuse;

	@InjectView(R.id.teacher_see_part_time_job_detail_info_rg_all_wish)       RadioGroup  rg_all_wish;







	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mactivity = this;

		data = getIntent().getBundleExtra("data");
		//		args = data.getBundle("args");
		//		args = getIntent().getBundleExtra("args");

		if(data.getBoolean("hide_tv_choice")){
			tv_choice.setVisibility(View.INVISIBLE);
		}

		if(data.getBoolean("hide_rg_all_wish")){
			rg_all_wish.setVisibility(View.INVISIBLE);
		}

		if(data.getBoolean("hide_btn_submit")){
			btn_submit.setVisibility(View.INVISIBLE);
		}


		tv_title.setText(data.getString("title"));


		Log.i(tag, data.getString("firstWish"));
		rb_wish_1.setText(data.getString("firstWish"));
		Log.i(tag, data.getString("secondWish"));
		rb_wish_2.setText(data.getString("secondWish"));
		tv_name.setText(data.getString("userName"));
		Picasso.with(this).load(data.getString("imageAddress")).into(iv_picture);


		//		rg_all_wish.check(R.id.teacher_see_part_time_job_detail_info_rb_wish_1);
		btn_submit.setOnClickListener(this);
		rg_all_wish.setOnCheckedChangeListener(this);







	}







	@Override
	public void onClick(View v) {
		submit();

	}







	private void submit() {
		if(TextUtils.isEmpty(wish_status)){
			Toast.makeText(mactivity, "请先选择该同学的归宿...",0).show();
		}else{
			RequestParams params = new RequestParams();
			params.put("hwstudyId",data.getString("hwstudyId"));
			params.put("status", wish_status);
			OaAsyncHttpClient.post(data.getString("sub_url"), params, new JsonHttpResponseHandler(){



				@Override
				public void onSuccess(int statusCode, JSONObject response) {
					super.onSuccess(statusCode, response);
					Log.i(tag, response.toString());

					try {
						if(response.getString("status").equals("0")){
							Toast.makeText(mactivity, "提交成功...",0).show();
						}else if(response.getString("status").equals("1")){
							Toast.makeText(mactivity, "该操作无法执行...",0).show();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}


				@Override
				public void onFailure(Throwable e, JSONObject errorResponse) {
					super.onFailure(e, errorResponse);
					Log.i(tag, errorResponse.toString());
				}
			}, mactivity);
		}

	}







	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(group.getId()==R.id.teacher_see_part_time_job_detail_info_rg_all_wish){

			switch (checkedId) {

			case R.id.teacher_see_part_time_job_detail_info_rb_wish_1:
				wish_status = "5";
				Log.i(tag, "wish_status = 5");

				break;

			case R.id.teacher_see_part_time_job_detail_info_rb_wish_2:

				wish_status = "6";
				Log.i(tag, "wish_status = 6");

				break;

			default:
				wish_status = "7";
				Log.i(tag, "wish_status = 7");
				break;
			}
		}
	}

}
