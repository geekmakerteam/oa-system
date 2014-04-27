package org.geeklub.vass.function.lifeteacher;

import org.geeklub.constant.Url;
import org.geeklub.fragments.CommonRepairFragment;
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

public class LifeTeacherCheckActivity extends SherlockFragmentActivity {


	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private LifeTeacherCheckActivityPagerAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_cardsui_activity_style_1);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.common_cardsui_activity_style_1_tabs);
		pager = (ViewPager) findViewById(R.id.common_cardsui_activity_style_1_pager);
		adapter = new LifeTeacherCheckActivityPagerAdapter(getSupportFragmentManager());

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


	public class LifeTeacherCheckActivityPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "已审核","未审核","审核拒绝"
		};

		private final String[] URL = {
				Url.LIFE_TEACHER_HAVE_CHECK,
				Url.LIFE_TEACHER_HAVE_NOT_CHECK,
				Url.LIFE_TEACHER_CHECK_REFUSED
		};

		public LifeTeacherCheckActivityPagerAdapter(FragmentManager fm) {
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
			return CommonRepairFragment.newInstance(URL[position], Url.LIFE_TEACHER_SUBMIT_IF_PASS, false, "审核是否通过？", false, false);
		}

	}

}
