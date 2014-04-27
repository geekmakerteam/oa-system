package org.geeklub.vass.function.teacher;

import org.geeklub.constant.Url;
import org.geeklub.fragments.CommonSignInFragment;
import org.geeklub.vass.R;
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

public class StudentSignInListActivity extends SherlockFragmentActivity {
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private StudentSignInListActivityPagerAdapter adapter;
	private String courseID ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_cardsui_activity_style_1);

		courseID = getIntent().getBundleExtra("data").getString("courseId");

		tabs = (PagerSlidingTabStrip) findViewById(R.id.common_cardsui_activity_style_1_tabs);
		pager = (ViewPager) findViewById(R.id.common_cardsui_activity_style_1_pager);
		adapter = new StudentSignInListActivityPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		tabs.setIndicatorColorResource(R.color.orange);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}


	public class StudentSignInListActivityPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "已签到学生列表",

		};

		private final String[] URL = {
				Url.TEACHER_COURSE_STUDENT_HAVE_SIGN_IN_LIST + "courseId=" + courseID
		};

		public StudentSignInListActivityPagerAdapter(FragmentManager fm) {
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
			return CommonSignInFragment.newInstance(URL[position],false,"username","status");
		}

	}
}
