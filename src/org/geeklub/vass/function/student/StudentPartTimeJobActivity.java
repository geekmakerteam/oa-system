package org.geeklub.vass.function.student;

import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.fragments.student.StudentPartTimeJobStage_1;
import org.geeklub.fragments.student.StudentPartTimeJobStage_2;
import org.geeklub.fragments.student.StudentPartTimeJobStage_3;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.loopj.android.http.JsonHttpResponseHandler;

public class StudentPartTimeJobActivity extends SherlockFragmentActivity implements OnNavigationListener {
	private ActionBar actionbar;
	private Activity mactivity;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private ArrayAdapter<String> arrayAdapter;

	private String tag = "StudentPartTimeJobActivity";
	private int STATUS;



	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		mactivity = this;

		actionbar = getSupportActionBar();

		setContentView(R.layout.student_part_time_job_activity);

		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		//		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Values.STUDENT_PART_TIME_JOB_STATUS);
		//
		//		actionbar.setListNavigationCallbacks(arrayAdapter, this);

		initView();

	}



	private void initView() {
		Log.i(tag, "initView()");
		OaAsyncHttpClient.get(Url.Student_PART_JOB_REQUEST_STATUS, null, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				super.onSuccess(statusCode, response);
				//				Log.i(tag, response.toString());

				try {
					if(response.getString("status").equals("0")){

						//						Log.i(tag, response.getString("datas"));
						STATUS = Integer.valueOf(response.getString("datas"));
						Log.i(tag, "initView()            STATUS===>"+STATUS);


						arrayAdapter = new ArrayAdapter<String>(mactivity, android.R.layout.simple_spinner_item, Values.STUDENT_PART_TIME_JOB_STATUS);

						actionbar.setListNavigationCallbacks(arrayAdapter, (OnNavigationListener) mactivity);

						if(STATUS >= 0 && STATUS <= 2){
							actionbar.setSelectedNavigationItem(0);
						}else if(STATUS == 3 ){
							actionbar.setSelectedNavigationItem(1);
						}else if(STATUS >= 4 && STATUS <= 7){
							actionbar.setSelectedNavigationItem(2);
						}

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



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.student_part_time_job_activity_actionbar, menu);
		return true;
	}





	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Log.i(tag, "onNavigationItemSelected():            STATUS===>"+STATUS);

		fragmentManager = getSupportFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();

		switch (itemPosition) {

		case 0:
			Fragment studentPartTimeJobStage_1 = new StudentPartTimeJobStage_1();
			Bundle args_1 = new Bundle();
			Log.i(tag, "传入studentPartTimeJobStage_1中的STATUS===>"+STATUS);
			args_1.putInt("status_1", STATUS);
			studentPartTimeJobStage_1.setArguments(args_1);
			fragmentTransaction.replace(R.id.student_part_time_job_activity_content, studentPartTimeJobStage_1);
			break;

		case 1:
			Fragment studentPartTimeJobStage_2 = new StudentPartTimeJobStage_2();
			Bundle args_2 = new Bundle();
			args_2.putInt("status_2", STATUS);
			studentPartTimeJobStage_2.setArguments(args_2);
			fragmentTransaction.replace(R.id.student_part_time_job_activity_content, studentPartTimeJobStage_2);
			break;


		case 2:
			Fragment studentPartTimeJobStage_3 = new StudentPartTimeJobStage_3();
			fragmentTransaction.replace(R.id.student_part_time_job_activity_content, studentPartTimeJobStage_3);
			break;

		default:
			break;
		}

		fragmentTransaction.commit();
		return true;
	}

}
