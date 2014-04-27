package org.geeklub.vass.activitys;


import org.geeklub.constant.Values;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.json.JSONException;
import org.json.JSONObject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

@ContentView(R.layout.repair_detail_activity)
public class RepairDetailActivity extends RoboActivity implements OnClickListener{
	private static final String tag = "RepairDetailActivity";
	private Bundle data = null;

	private Activity mactivity;

	@InjectView(R.id.repair_detail_activity_tv_ifpass) TextView tv_ifpass;
	@InjectView(R.id.repair_detail_activity_address) TextView tv_address;
	@InjectView(R.id.repair_detail_activity_baoxiuren) TextView tv_baoxiuren;
	@InjectView(R.id.repair_detail_activity_accidentDate) TextView tv_accidentDate;
	@InjectView(R.id.repair_detail_activity_repairDate) TextView tv_repairDate;
	@InjectView(R.id.repair_detail_activity_repDescripe) TextView tv_repDescripe;
	@InjectView(R.id.repair_detail_activity_photo) ImageView im_photo;
	@InjectView(R.id.repair_detail_activity_btn_pass) Button btn_pass;
	@InjectView(R.id.repair_detail_activity_btn_nopass) Button btn_nopass;


	private String repairId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mactivity = this;

		btn_pass.setOnClickListener(this);
		btn_nopass.setOnClickListener(this);

		Intent intent = getIntent();
		data = intent.getBundleExtra(Values.REP_DATA);

		//		repairId = (String) data.get("repairId");
		//		Log.i(tag, "repair===>"+repairId);

		repairId = data.getString(Values.REP_ID);

		//		if(data.getBoolean("title_isHide"))
		if(data.getBoolean(Values.HIDE_TITLE))
			tv_ifpass.setVisibility(View.GONE);

		//		if(data.getBoolean("btn_not_pass_isHide"))
		if(data.getBoolean(Values.HIDE_NOT_PASS_BUTTON))
			btn_nopass.setVisibility(View.GONE);


		//		if(data.getBoolean("btn_pass_isHide"))
		if(data.getBoolean(Values.HIDE_PASS_BUTTON))
			btn_pass.setVisibility(View.GONE);

		//		tv_baoxiuren.setText(data.getString("userName"));
		//		tv_address.setText(	data.getString("address"));
		//		tv_accidentDate.setText(data.getString("accidentDate"));
		//		tv_repairDate.setText(data.getString("repairDate"));
		//		tv_repDescripe.setText(data.getString("repDescripe"));
		//		tv_ifpass.setText(data.getString("title"));

		tv_baoxiuren.setText(data.getString(Values.REP_ID));
		tv_address.setText(	data.getString(Values.REP_ADDRESS));
		tv_accidentDate.setText(data.getString(Values.REP_ACCIDENT_DATE));
		tv_repairDate.setText(data.getString(Values.REP_REPAIR_DATE));
		tv_repDescripe.setText(data.getString(Values.REP_DESCRIPE));
		tv_ifpass.setText(data.getString(Values.TITLE));


		//		Log.i(tag, "是否要隐藏？"+data.getBoolean("ishide"));








		//		if(data.getString("imageAddress") != null){
		if(data.getString(Values.REP_IMAGE_ADDRESS) != null){
			im_photo.setVisibility(View.VISIBLE);
			Picasso.with(this).load(data.getString(Values.REP_IMAGE_ADDRESS)).into(im_photo);
		}


	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.repair_detail_activity_btn_pass:
			ifPass(repairId,"1");


			break;
		case R.id.repair_detail_activity_btn_nopass:
			ifPass(repairId,"3");

			break;

		default:
			break;
		}

	}


	private void ifPass(String repairId, String status) {
		RequestParams params = new RequestParams();
		params.put("repairId", repairId);
		params.put("status", status);

		Log.i(tag, params.toString());

		OaAsyncHttpClient.get(data.getString(Values.SUBMIT_URL), params, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				super.onSuccess(statusCode, response);
				Log.i(tag, response.toString());

				try {
					if(response.get("status").equals("0")){
						Toast.makeText(RepairDetailActivity.this, "提交成功...", 0).show();
						finish();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				super.onFailure(e, errorResponse);
			}
		}, mactivity);

	}











}
