package org.geeklub.vass.function.teacher;

import org.geeklub.constant.Url;
import org.geeklub.fragments.CommonSignInFragment;
import org.geeklub.vass.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.astuetz.PagerSlidingTabStrip;

public class TeacherCallNamesActivity extends SherlockFragmentActivity{


	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private TeacherCallNamesActivityPagerAdapter adapter;
	private Activity mactivity;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mactivity = this;
		setContentView(R.layout.common_cardsui_activity_style_1);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.common_cardsui_activity_style_1_tabs);
		pager = (ViewPager) findViewById(R.id.common_cardsui_activity_style_1_pager);
		adapter = new TeacherCallNamesActivityPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		tabs.setIndicatorColorResource(R.color.orange);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.teacher_callname_actionbar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.teacher_callname_actionbar_add_class:
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setClass(this, TeacherAddClassActivity.class);
			mactivity.startActivity(intent);


			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}


	public class TeacherCallNamesActivityPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "课程列表"
		};

		private final String[] URL = {
				Url.TEACHER_COURSE_LIST
		};

		public TeacherCallNamesActivityPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return CommonSignInFragment.newInstance(URL[position],true,"courseName","time");
		}

	}
}
