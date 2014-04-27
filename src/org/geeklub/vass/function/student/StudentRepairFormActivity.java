package org.geeklub.vass.function.student;

import java.util.Calendar;
import java.util.Date;
import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.utils.OaBitmapUtils;
import org.geeklub.utils.OaCalendar;
import org.geeklub.vass.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class StudentRepairFormActivity extends SherlockFragmentActivity implements OnDateSetListener,OnClickListener{

	private String tag = "StudentRepairFormActivity";

	private TextView tvDisplayDate;
	private ImageButton imageButtonDate;

	private ActionBar actionBar;


	private final Calendar calendar = Calendar.getInstance();
	private DatePickerDialog mdatePickerDialog;


	private Button btnTakePhoto;
	private Button btnUploading;

	private ProgressBar progressBar;


	private StringBuilder datestringBuilder;

	private Bitmap mPicture;


	private EditText et_baoxiuxiangmu;

	private Activity mactivity;







	public static final String DATEPICKER_TAG = "datepicker";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_repair_form_activity);

		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		mactivity = this;

		initView();


		if (savedInstanceState != null) {
			Log.i(tag, "savedInstanceState != null");
			DatePickerDialog datePickerDialog = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
			if (datePickerDialog != null) {
				datePickerDialog.setOnDateSetListener(this);
			}
		}

	}

	private void initView() {

		progressBar = (ProgressBar) findViewById(R.id.student_repair_form_activity_progressbar);

		mdatePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		tvDisplayDate = (TextView) findViewById(R.id.student_repair_form_activity_tv_Date);

		resetDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

		imageButtonDate = (ImageButton) findViewById(R.id.student_repair_form_activity_imbtn_ChangeDate);

		imageButtonDate.setOnClickListener(this);

		btnTakePhoto = (Button) findViewById(R.id.student_repair_form_activity_btn_photo);
		btnTakePhoto.setOnClickListener(this);

		btnUploading = (Button) findViewById(R.id.student_repair_form_activity_btn_uploading);
		btnUploading.setOnClickListener(this);


		et_baoxiuxiangmu = (EditText) findViewById(R.id.student_repair_form_activity_tv_baoxiuxiangmu);





	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			this.setResult(Values.QUIT_SAFE);
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onDateSet(DatePickerDialog datePickerDialog, int year,
			int month, int day) {
		Log.i(tag, "重新设定时间");
		Log.i(tag, "年="+year+";月="+month+";日="+day);
		resetDate(year, month, day);
	}

	@Override
	public void onClick(View v) {
		String baoxiuxiangmu = et_baoxiuxiangmu.getText().toString();

		switch (v.getId()) {

		case R.id.student_repair_form_activity_imbtn_ChangeDate:
			mdatePickerDialog.setYearRange(2000, 2028);
			mdatePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);

			break;
		case R.id.student_repair_form_activity_btn_photo:
			Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			takePhotoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(takePhotoIntent, Values.TAKE_PHOTO_RESULT_CODE);

			break;

		case R.id.student_repair_form_activity_btn_uploading:
			upLoadingMessage(baoxiuxiangmu);

		default:
			break;
		}

	}



	private void upLoadingMessage(String baoxiuxiangmu) {


		if(!TextUtils.isEmpty(baoxiuxiangmu)){
			Log.i(tag, "上传信息");

			progressBar.setVisibility(View.VISIBLE);

			RequestParams requestParams = new RequestParams();
			//故障发生日期
			requestParams.put(Values.REP_ACCIDENT_DATE, datestringBuilder.toString());
			//故障描述
			requestParams.put(Values.REP_DESCRIPE, baoxiuxiangmu);
			//系统时间
			requestParams.put("date", new Date().toString());

			if(mPicture != null){
				requestParams.put(Values.TAKE_PICTURE,OaBitmapUtils.Bitmap2stream(mPicture));
			}


			//			Log.i(tag, "http://192.168.137.158:8080/oa-system/"+Url.MY_REPAIR_SUBMIT + requestParams.toString());
			OaAsyncHttpClient.post(Url.STUDENT_REPAIR_SUBMIT_FORM,requestParams, new AsyncHttpResponseHandler(){



				@Override
				public void onSuccess(int statusCode, String content) {
					super.onSuccess(statusCode, content);
					Log.i(tag, "statusCODE==="+statusCode+"成功:" + content);
					progressBar.setVisibility(View.GONE);
					Toast.makeText(mactivity, "提交成功...", Toast.LENGTH_SHORT).show();

					Intent intent = new Intent();
					intent.putExtra(Values.MENUDRAWER_FRAGMENT_STATUS, -1);
					mactivity.setResult(Values.SUBMIT_FORM_CODE,intent);

					finish();
				}


				@Override
				public void onFailure(Throwable error, String content) {
					super.onFailure(error, content);
					Log.i(tag, "失败:"+content);
					progressBar.setVisibility(View.GONE);
					Toast.makeText(StudentRepairFormActivity.this, "提交失败...", Toast.LENGTH_SHORT).show();

				}

			}, this);

		}else{
			Toast.makeText(this, "请将信息填写完整...", Toast.LENGTH_SHORT).show();
		}
	}










	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == Values.TAKE_PHOTO_RESULT_CODE){
			if(resultCode == Activity.RESULT_OK){
				mPicture = (Bitmap) data.getExtras().get("data");
				Toast.makeText(this, "拍照成功", Toast.LENGTH_SHORT).show();
				Log.i(tag, "拍照成功");
			}
		}
	}



	private void resetDate(int year,int month,int day) {
		datestringBuilder = new StringBuilder().append(OaCalendar.pad(year))
				.append("-").append(OaCalendar.pad(month+1)).append("-").append(OaCalendar.pad(day));
		tvDisplayDate.setText(datestringBuilder);
		tvDisplayDate.setTextColor(getResources().getColor(android.R.color.darker_gray));
	}

}
