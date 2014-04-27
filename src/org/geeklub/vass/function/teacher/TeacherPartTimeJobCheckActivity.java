package org.geeklub.vass.function.teacher;

import org.geeklub.constant.Url;
import org.geeklub.fragments.teacher.TeacherPartTimeJobFragment;
import org.geeklub.vass.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.astuetz.PagerSlidingTabStrip;

public class TeacherPartTimeJobCheckActivity extends SherlockFragmentActivity{

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private TeacherPartTimeJobCheckActivityPagerAdapter adapter;



	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.common_cardsui_activity_style_1);


		tabs = (PagerSlidingTabStrip) findViewById(R.id.common_cardsui_activity_style_1_tabs);
		pager = (ViewPager) findViewById(R.id.common_cardsui_activity_style_1_pager);
		adapter = new TeacherPartTimeJobCheckActivityPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		tabs.setIndicatorColorResource(R.color.orange);



	}


	public class TeacherPartTimeJobCheckActivityPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "贫困证明审核",
				"志愿审核",
				"全部"
		};

		private final String[] URL = {
				Url.TEACHER_PART_JOB_POVERTY_CHECK_LIST,
				Url.TEACHER_PART_JOB_WISH_CHECK_LIST,
				Url.TEACHER_PART_JOB_CHECK_ALL_LIST
		};

		public TeacherPartTimeJobCheckActivityPagerAdapter(FragmentManager fm) {
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

			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment =  TeacherPartTimeJobFragment.newInstance(URL[position], Url.TEACHER_PART_JOB_CHECK_PICTURE, "贫困证明审核...",true, true, false);
				break;

			case 1:
				fragment =  TeacherPartTimeJobFragment.newInstance(URL[position], Url.TEACHER_PART_JOB_CHECK_WISH, "志愿审核...",false, false, false);
				break;

			case 2:
				fragment =  TeacherPartTimeJobFragment.newInstance(URL[position], Url.TEACHER_PART_JOB_CHECK_PICTURE, "查看志愿...",false, false, true);
				break;
			}

			return fragment;

		}

	}

}
