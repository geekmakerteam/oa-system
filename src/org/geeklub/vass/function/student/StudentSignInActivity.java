package org.geeklub.vass.function.student;


import java.util.List;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import org.geeklub.beans.Item;
import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.fragments.CommonSignInFragment;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.geeklub.vass.baseactivity.BaseMenuDrawerActivity;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.loopj.android.http.JsonHttpResponseHandler;


public class StudentSignInActivity extends BaseMenuDrawerActivity {


	private static final String tag = "StudentSignInActivity";
	private ActionBar actionBar;
	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;
	private Activity mactivity;

	private String mCurrentFragmentTag;

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);



		mactivity = this;
		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
		mMenuDrawer.setSlideDrawable(R.drawable.ic_drawer);
		mMenuDrawer.setDrawerIndicatorEnabled(true);



		mFragmentManager = getSupportFragmentManager();

		if (inState != null) {
			mCurrentFragmentTag = inState.getString(Values.MENUDRAWER_STATE_CURRENT_FRAGMENT);
		} else {
			mCurrentFragmentTag = ((Item) mAdapter.getItem(0)).mUrl;
			attachFragment(mMenuDrawer.getContentContainer().getId(), getFragment(mCurrentFragmentTag),
					mCurrentFragmentTag);
			commitTransactions();
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		mMenuDrawer.setOnDrawerStateChangeListener(new MenuDrawer.OnDrawerStateChangeListener() {
			@Override
			public void onDrawerStateChange(int oldState, int newState) {
				if (newState == MenuDrawer.STATE_CLOSED) {
					commitTransactions();
				}
			}

			@Override
			public void onDrawerSlide(float openRatio, int offsetPixels) {
				// Do nothing
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(Values.MENUDRAWER_STATE_CURRENT_FRAGMENT, mCurrentFragmentTag);
	}

	@Override
	protected void onMenuItemClicked(int position, Item item) {
		if (mCurrentFragmentTag != null)
			detachFragment(getFragment(mCurrentFragmentTag));
		attachFragment(mMenuDrawer.getContentContainer().getId(), getFragment(item.mUrl), item.mUrl);
		mCurrentFragmentTag = item.mUrl;
		mMenuDrawer.closeMenu();
	}

	@Override
	protected int getDragMode() {
		return MenuDrawer.MENU_DRAG_WINDOW;
	}

	@Override
	protected Position getDrawerPosition() {
		return Position.LEFT;
	}

	protected FragmentTransaction ensureTransaction() {
		if (mFragmentTransaction == null) {
			mFragmentTransaction = mFragmentManager.beginTransaction();
			mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		}

		return mFragmentTransaction;
	}

	private Fragment getFragment(String url) {
		Fragment fragment = mFragmentManager.findFragmentByTag(url);

		if (fragment == null) {
			Log.i(tag, "CommonSignInFragment.newInstance(url,false)");
			fragment = CommonSignInFragment.newInstance(url,false,"courseName","time");
		}

		return fragment;
	}

	protected void attachFragment(int layout, Fragment fragment, String tag) {
		if (fragment != null) {
			if (fragment.isDetached()) {
				ensureTransaction();
				mFragmentTransaction.attach(fragment);
			} else if (!fragment.isAdded()) {
				ensureTransaction();
				mFragmentTransaction.add(layout, fragment, tag);
			}
		}
	}

	protected void detachFragment(Fragment fragment) {
		if (fragment != null && !fragment.isDetached()) {
			ensureTransaction();
			mFragmentTransaction.detach(fragment);
		}
	}

	protected void commitTransactions() {
		if (mFragmentTransaction != null && !mFragmentTransaction.isEmpty()) {
			mFragmentTransaction.commit();
			mFragmentTransaction = null;
		}
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.student_sign_in_activity_actionbar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			mMenuDrawer.toggleMenu();
			return true;

		case R.id.student_sign_in_activity_actionbar_add:
			Intent intent = new Intent();
			intent.setClass(mactivity, MipcaActivityCapture.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//			mactivity.startActivity(intent);
			mactivity.startActivityForResult(intent, Values.SCANNIN_GREQUEST_CODE);

		}
		return super.onOptionsItemSelected(item);
	}



	/**
	 * requestCode表示是由哪一个Activity返回过来的
	 * 第二个参数为resultCode，也是一个int类型，如果activity B有几种不同返回的结果，同样地可以通过resultCode来筛选。
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		Log.i(tag, "requestCode===>"+requestCode+";resultCode===>"+resultCode);

		if(requestCode==Values.SCANNIN_GREQUEST_CODE){
			if( data!= null && resultCode==Values.SCANNIN_GREQUEST_CODE){
				Log.i(tag, "二维码返回的值===>"+data.getExtras().getString("result"));
				String url = data.getExtras().getString("result");
				//调用签到的API
				OaAsyncHttpClient.getQR_CODE(url, null, new JsonHttpResponseHandler(){

					@Override
					public void onSuccess(int statusCode, JSONObject response) {
						super.onSuccess(statusCode, response);
						try {
							if(response.get("status").equals("0")){
								Toast.makeText(mactivity, "签到成功...", 0).show();
							}

						} catch (Exception e) {

						}
						Log.i(tag, "response"+response);
					}

				}, mactivity);

			}
		}






	}

	@Override
	protected void initView(List<Object> items) {
		items.add(new Item("签到记录", R.drawable.ic_action_view_as_grid,Url.STUDENT_HAVE_SIGN_IN_LIST));
	}






}

