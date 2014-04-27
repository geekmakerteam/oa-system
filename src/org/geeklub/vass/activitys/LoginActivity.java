package org.geeklub.vass.activitys;

import org.geeklub.application.OaApplication;
import org.geeklub.beans.Result;
import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.http.FastJsonParse;
import org.geeklub.http.OaSyncHttpClient;
import org.geeklub.utils.OaPreferenceSettings;
import org.geeklub.utils.OaPushUtil;
import org.geeklub.vass.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.loopj.android.http.RequestParams;


public class LoginActivity extends Activity {

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;


	private String mPassword;
	private String mUsername;
	private String tag = "LoginActivity";

	private OaApplication oaApplication = OaApplication.getOaApplication();


	private String[] fuctions;



	/**用于输入用户名的EditText控件*/
	private EditText mUsernameView;
	/**用于输入密码的EditText控件*/
	private EditText mPasswordView;

	private View mLoginFormView;

	private View mLoginStatusView;

	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.login_activity);




		mUsernameView = (EditText) findViewById(R.id.login_activity_username);
		mUsernameView.setText(OaPreferenceSettings.getUserName(this));

		mPasswordView = (EditText) findViewById(R.id.login_activity_password);
		mPasswordView.setText(OaPreferenceSettings.getPassWord(this));



		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id,
					KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		mLoginFormView = findViewById(R.id.login_activity_login_form);
		mLoginStatusView = findViewById(R.id.login_activity_login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.login_activity_sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login_activity_actionbar, menu);
		return true;
	}


	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mUsernameView.setError(null);
		mPasswordView.setError(null);

		mUsername = mUsernameView.getText().toString();
		Log.i(tag, "得到的用户名===>"+mUsername);

		mPassword = mPasswordView.getText().toString();
		Log.i(tag, "得到的用户名===>"+mPassword);

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		if (TextUtils.isEmpty(mUsername)) {
			mUsernameView.setError(getString(R.string.error_field_required));
			focusView = mUsernameView;
			cancel = true;
		} else if (mUsername.contains("@")) {
			mUsernameView.setError(getString(R.string.error_invalid_username));
			focusView = mUsernameView;
			cancel = true;
		}

		if (cancel) {
			focusView.requestFocus();
		} else {
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute(Url.USER_LOGIN);
		}
	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
			.alpha(show ? 1 : 0)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginStatusView.setVisibility(show ? View.VISIBLE
							: View.GONE);
				}
			});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
			.alpha(show ? 0 : 1)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE
							: View.VISIBLE);
				}
			});
		} else {

			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}


	public class UserLoginTask extends AsyncTask<String, Void, Boolean> {
		@Override
		protected Boolean doInBackground(String... params) {

			boolean FLAG = false;

			try {
				RequestParams login_requestParams = new RequestParams();
				login_requestParams.put(Values.USER_NAME, mUsername);
				login_requestParams.put(Values.PASS_WORD, mPassword);

				Log.i(tag, login_requestParams.toString());

				String jsonString = OaSyncHttpClient.post(params[0], login_requestParams,LoginActivity.this);



				Result result = FastJsonParse.getJsonObject(jsonString, Result.class);


				//				登陆成功

				if(result.getStatus().equals(Values.RESULT_SUCCESS)){
					//					保存密码
					Log.i(tag, "保存密码===>"+mPassword);
					OaPreferenceSettings.setPassWord(oaApplication, mPassword);
					//					保存用户名
					Log.i(tag, "保存用户名===>"+mUsername);
					OaPreferenceSettings.setUserName(oaApplication, mUsername);
					
					fuctions = result.getDatas();

					FLAG = true;
				}

			} catch (Exception e) {

				return false;
			}

			return FLAG;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			//			如果成功
			if (success) {

				//				如果还没有绑定百度的Push服务
				if (OaPushUtil.hasBind(oaApplication)) {
					Log.i(tag, "还没有绑定，现在开始绑定...");
					//					开始绑定服务
					PushManager.startWork(getApplicationContext(),
							PushConstants.LOGIN_TYPE_API_KEY, 
							OaPushUtil.getMetaValue(oaApplication, Values.API_KEY));
				}

				finish();

				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("fuctions", fuctions);

				LoginActivity.this.startActivity(intent);

			} else {
				mPasswordView
				.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
