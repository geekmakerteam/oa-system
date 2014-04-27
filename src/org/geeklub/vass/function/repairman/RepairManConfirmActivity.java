package org.geeklub.vass.function.repairman;

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

public class RepairManConfirmActivity extends SherlockFragmentActivity{
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private RepairManConfirmActivityPagerAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_cardsui_activity_style_1);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.common_cardsui_activity_style_1_tabs);
		pager = (ViewPager) findViewById(R.id.common_cardsui_activity_style_1_pager);
		adapter = new RepairManConfirmActivityPagerAdapter(getSupportFragmentManager());

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


	public class RepairManConfirmActivityPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "已维修",
				"未维修"
		};

		private final String[] URL = {
				Url.REPAIR_MAN_HAVE_REPAIR_LIST,
				Url.REPAIR_MAN_HAVE_NOT_REPAIR_LIST
		};

		public RepairManConfirmActivityPagerAdapter(FragmentManager fm) {
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
			return CommonRepairFragment.newInstance(URL[position], Url.REPAIR_MAN_SUBMIT_IS_FINISH, false, "维修是否完成？", false, true);
		}

	}

}
