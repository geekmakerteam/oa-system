package org.geeklub.fragments.student;

import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.utils.OaBitmapUtils;
import org.geeklub.vass.R;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//@ContentView(R.layout.student_part_time_job_stage_1)

public class StudentPartTimeJobStage_1 extends Fragment implements OnClickListener{

	private Activity mactivity;
	private TextView tv_title;
	private ImageView iv_picture;
	private Button btn_submit;
	private Button btn_take_photo;
	//	private Bundle args;
	private String tag = "StudentPartTimeJobStage_1";
	//	@InjectView(R.id.student_part_time_job_stage_1_tv_title) TextView tv_title;
	//	@InjectView(R.id.student_part_time_job_stage_1_iv_picture) ImageView iv_picture;
	//	@InjectView(R.id.student_part_time_job_stage_1_btn_submit) Button btn_submit;
	private Bitmap mPicture;

	private int STATUS;

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
		View rootView = inflater.inflate(R.layout.student_part_time_job_stage_1, null);

		tv_title = (TextView) rootView.findViewById(R.id.student_part_time_job_stage_1_tv_title);
		btn_submit = (Button) rootView.findViewById(R.id.student_part_time_job_stage_1_btn_submit);
		iv_picture = (ImageView) rootView.findViewById(R.id.student_part_time_job_stage_1_iv_picture);
		btn_take_photo = (Button) rootView.findViewById(R.id.student_part_time_job_stage_1_btn_take_picture);

		iv_picture.setVisibility(View.INVISIBLE);

		btn_submit.setOnClickListener(this);
		btn_take_photo.setOnClickListener(this);

		return rootView;
	}



	@Override
	public void onStart() {
		initView();
		super.onStart();




	}



	private void initView() {
		STATUS = getArguments().getInt("status_1");

		Log.i(tag, "现在的状态是===>"+STATUS);

		switch (STATUS) {

		case 0:
			tv_title.setText("您还没有上传过贫困证明图片，请先上传图片");


			break;
		case 1:
			tv_title.setText("贫困证明正在审核中，请耐心等待...");
			btn_submit.setVisibility(View.INVISIBLE);
			btn_take_photo.setVisibility(View.INVISIBLE);

			break;
		case 2:
			tv_title.setText("贫困证明已经通过审核,请填写志愿");
			btn_submit.setVisibility(View.INVISIBLE);
			btn_take_photo.setVisibility(View.INVISIBLE);

			break;
		case 3:
			tv_title.setText("抱歉，您的申请被拒绝了...");
			btn_submit.setVisibility(View.INVISIBLE);
			btn_take_photo.setVisibility(View.INVISIBLE);

			break;

		default:
			break;
		}



	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.student_part_time_job_stage_1_btn_take_picture:
			Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			takePhotoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(takePhotoIntent, Values.TAKE_PHOTO_RESULT_CODE);

			break;
		case R.id.student_part_time_job_stage_1_btn_submit:
			if(mPicture != null){
				RequestParams params = new RequestParams();
				params.put("picture", OaBitmapUtils.Bitmap2stream(mPicture));



				OaAsyncHttpClient.post(Url.Student_PART_JOB_REQUEST_SUBMIT_PICTURE, params, new JsonHttpResponseHandler(){


					@Override
					public void onSuccess(int statusCode, JSONObject response) {
						super.onSuccess(statusCode, response);

						try {
							if(response.get("status").equals("0")){
								Toast.makeText(mactivity, "照片上传成功...", Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {

							e.printStackTrace();
						}

					}
				}, mactivity);
			}

			break;

		default:
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i(tag, "requestCode"+requestCode);

		if(requestCode == Values.TAKE_PHOTO_RESULT_CODE){
			if(resultCode == Activity.RESULT_OK){
				mPicture = (Bitmap) data.getExtras().get("data");
				Toast.makeText(mactivity, "拍照成功...", Toast.LENGTH_SHORT).show();

				iv_picture.setVisibility(View.VISIBLE);
				iv_picture.setImageBitmap(mPicture);
			}

		}
	}





}
