package org.geeklub.vass.function.student;

import java.util.List;
import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import org.geeklub.beans.Item;
import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.fragments.CommonRepairFragment;
import org.geeklub.vass.R;
import org.geeklub.vass.baseactivity.BaseMenuDrawerActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class StudentRepairActivity extends BaseMenuDrawerActivity{





	private Activity mactivity;
	private ActionBar actionBar;
	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;

	private String mCurrentFragmentTag;
	private String TAG = "RepairActivity";
	private String tag ="RepairActivity";

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);


		Log.i(tag, "onCreate");

		mactivity = this;


		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);
		mMenuDrawer.setSlideDrawable(R.drawable.ic_drawer);
		mMenuDrawer.setDrawerIndicatorEnabled(true);



		mFragmentManager = getSupportFragmentManager();

		if (inState != null) {
			Log.i(TAG, "inState != null");
			mCurrentFragmentTag = inState.getString(Values.MENUDRAWER_STATE_CURRENT_FRAGMENT);
		} else {
			mCurrentFragmentTag = ((Item) mAdapter.getItem(0)).mUrl;
			attachFragment(mMenuDrawer.getContentContainer().getId(), getFragment(mCurrentFragmentTag),
					mCurrentFragmentTag);
			commitTransactions();
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
		return MenuDrawer.MENU_DRAG_CONTENT;
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
			fragment = CommonRepairFragment.newInstance(url,true, true, true);
		}

		return fragment;
	}

	protected void attachFragment(int layout, Fragment fragment, String tag) {
		if (fragment!= null) {
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
		getSupportMenuInflater().inflate(R.menu.student_repair_form_activity_actionbar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			mMenuDrawer.toggleMenu();
			return true;

		case R.id.stuednt_repair_form_activity_actionbar_add:
			Intent intent = new Intent();
			intent.setClass(mactivity, StudentRepairFormActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			mactivity.startActivityForResult(intent,Values.SUBMIT_FORM_CODE);

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if(resultCode == Values.SUBMIT_FORM_CODE){

			if(data != null && resultCode==Values.SUBMIT_FORM_CODE){
				Bundle bundle = data.getExtras();
				int status = (Integer) bundle.get(Values.MENUDRAWER_FRAGMENT_STATUS);

				mAdapter.setActivePosition(status);
			}
		}
	}


	@Override
	protected void onResume() {
		super.onResume();
	}


	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void initView(List<Object> items) {
		items.add(new Item("全部", R.drawable.ic_action_view_as_grid,Url.STUDENT_REPAIR_RECORD_ALL));
		items.add(new Item("报修", R.drawable.ic_action_view_as_grid,Url.STUDENT_REPAIR_RECORD_FOR_REPAIR));
		items.add(new Item("审核通过", R.drawable.ic_action_view_as_grid,Url.STUDENT_REPAIR_RECORD_PASS));
		items.add(new Item("维修完成", R.drawable.ic_action_view_as_grid,Url.STUDENT_REPAIR_RECORD_FINISHED));

	}

}
