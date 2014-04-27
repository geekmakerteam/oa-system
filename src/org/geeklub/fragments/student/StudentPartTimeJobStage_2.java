package org.geeklub.fragments.student;

import java.util.ArrayList;
import java.util.List;
import org.geeklub.beans.parttimejob.DepartMent;
import org.geeklub.beans.parttimejob.DepartMentBean;
import org.geeklub.constant.Url;
import org.geeklub.http.FastJsonParse;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.iangclifton.android.floatlabel.FloatLabel;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class StudentPartTimeJobStage_2 extends Fragment implements OnClickListener,OnItemSelectedListener{

	private Activity     mactivity;
	private TextView     tv_title;
	private Button       btn_submit;
	private FloatLabel   fl_reason;
	private Spinner      spinner_wish_one;
	private Spinner      spinner_wish_two;


	private List<String> departMentNameList;
	private List<String> departMentIdList;

	private String departMentNameID_1 = null;
	private String departMentNameID_2 = null;



	private String tag = "StudentPartTimeJobStage_2";
	private int STATUS;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mactivity = activity;
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		departMentIdList = new ArrayList<String>();
		departMentNameList = new ArrayList<String>();
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.student_part_time_job_stage_2, null);

		tv_title = (TextView) rootView.findViewById(R.id.student_part_time_job_stage_2_tv_title);
		btn_submit = (Button) rootView.findViewById(R.id.student_part_time_job_stage_2_btn_submit);
		fl_reason = (FloatLabel) rootView.findViewById(R.id.student_part_time_job_stage_2_fl_reason);
		spinner_wish_one = (Spinner) rootView.findViewById(R.id.student_part_time_job_stage_2_spinner_wish_one);
		spinner_wish_two = (Spinner) rootView.findViewById(R.id.student_part_time_job_stage_2_spinner_wish_two);
		tv_title.setText("请选择您的第一志愿和第二志愿...");

		spinner_wish_one.setOnItemSelectedListener(this);
		spinner_wish_two.setOnItemSelectedListener(this);
		btn_submit.setOnClickListener(this);


		return rootView;
	}



	@Override
	public void onStart() {
		super.onStart();
		initView();




	}



	private void initView() {
		STATUS = getArguments().getInt("status_2");
		
		
		if(STATUS==3){
			btn_submit.setVisibility(View.INVISIBLE);
		}
		Log.i(tag, "现在的状态是===>"+STATUS);

		OaAsyncHttpClient.get(Url.Student_PART_JOB_REQUEST_DEPARTMENT_LIST, null, new AsyncHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, String content) {
				super.onSuccess(statusCode, content);

				List<DepartMent> datas = FastJsonParse.getJsonObject(content, DepartMentBean.class).getDatas();

				//				List<String> list = new ArrayList<String>();
				for(DepartMent departMent : datas){
					departMentNameList.add(departMent.getDepartName());
					departMentIdList.add(departMent.getDepartId());
				}
				spinner_wish_one.setAdapter(new ArrayAdapter<String>(mactivity, android.R.layout.simple_spinner_item, departMentNameList));
				spinner_wish_two.setAdapter(new ArrayAdapter<String>(mactivity, android.R.layout.simple_spinner_item, departMentNameList));
			}
		}, mactivity);


	}






	@Override
	public void onClick(View v) {
		String reason = fl_reason.getEditText().getText().toString();

		if(TextUtils.isEmpty(reason)){
			Toast.makeText(mactivity, "原因不能为空...", Toast.LENGTH_SHORT).show();

		}else{
			if(departMentNameID_1.equals(departMentNameID_2)){
				Log.i(tag, "departMentNameID_1===>"+departMentNameID_1);
				Log.i(tag, "departMentNameID_2===>"+departMentNameID_2);
				Toast.makeText(mactivity, "第一志愿和第二志愿不能相同...", Toast.LENGTH_SHORT).show();
			}else{
				Log.i(tag, "departMentNameID_1===>"+departMentNameID_1);
				Log.i(tag, "departMentNameID_2===>"+departMentNameID_2);


				sendMyWish(reason,departMentNameID_1,departMentNameID_2);



			}

		}



	}



	private void sendMyWish(String reason, String departMentNameID_1,
			String departMentNameID_2) {

		RequestParams params = new RequestParams();
		params.put("firstWish", departMentNameID_1);
		params.put("secondWish", departMentNameID_2);
		params.put("reason", reason);

		OaAsyncHttpClient.post(Url.Student_PART_JOB_SUBMIT_WISH, params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				super.onSuccess(statusCode, response);


				try {
					if(response.getString("status").equals("0")){
						Toast.makeText(mactivity, "您的志愿提交成功...", Toast.LENGTH_SHORT).show();

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, mactivity);

	}



	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		switch (parent.getId()) {

		case R.id.student_part_time_job_stage_2_spinner_wish_one:
			Log.i(tag, "student_part_time_job_stage_2_spinner_wish_one   id====>"+parent.getId());
			Log.i(tag, "选择了第1个spinner,"+"选择了志愿一:"+departMentNameList.get(position));
			//			Log.i(tag,"ID====》"+ departMentIdList.get(position));

			departMentNameID_1 = departMentIdList.get(position);


			break;

		case R.id.student_part_time_job_stage_2_spinner_wish_two:
			Log.i(tag, "student_part_time_job_stage_2_spinner_wish_two   id====>"+parent.getId());
			Log.i(tag, "选择了第2个spinner,"+"选择了志愿二："+departMentNameList.get(position));

			departMentNameID_2 = departMentIdList.get(position);

			break;

		default:
			break;
		}

	}



	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}



}
