package org.geeklub.fragments.student;

import org.geeklub.constant.Url;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

public class StudentPartTimeJobStage_3 extends Fragment{
	private TextView tv_titile;
	private ImageView iv_picture;
	private TextView tv_result;

	private Activity mactivity;



	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mactivity = activity;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.student_part_time_job_stage_3, null);
		tv_titile = (TextView) rootView.findViewById(R.id.student_part_time_job_stage_3_tv_title);
		tv_result = (TextView) rootView.findViewById(R.id.student_part_time_job_stage_3_tv_result);
		iv_picture = (ImageView) rootView.findViewById(R.id.student_part_time_job_stage_3_iv_picture);

		return rootView;
	}



	@Override
	public void onStart() {
		super.onStart();


		OaAsyncHttpClient.get(Url.Student_PART_JOB_CHECK_RESULT, null, new JsonHttpResponseHandler(){



			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				super.onSuccess(statusCode, response);
				try {
					Picasso.with(mactivity).load(response.getString("imageAddress")).into(iv_picture);
					tv_result.setText(response.getJSONObject("datas").getString("status"));
				} catch (JSONException e) {

					e.printStackTrace();
				}

			}



		}, mactivity);
	}





}
