package org.geeklub.vass;

import java.util.ArrayList;
import org.geeklub.adapter.MainActivityPagerAdapter;
import org.geeklub.beans.MetroButton;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

@ContentView(R.layout.main_activity)
public class MainActivity extends RoboActivity implements OnPageChangeListener{
	private static final String tag = "MainActivity";

	@InjectView(R.id.main_activity_viewPager) ViewPager vp_main;

	private ArrayList<MetroButton> mdata;
	private MainActivityPagerAdapter mainActivityPagerAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initData();
		mainActivityPagerAdapter = new MainActivityPagerAdapter(this, mdata);
		vp_main.setAdapter(mainActivityPagerAdapter);
		vp_main.setOnPageChangeListener(this);
		vp_main.setPageMargin(100);
	}

	private void initData() {

		mdata = new ArrayList<MetroButton>();
		mdata.add(new MetroButton("今日看点", R.drawable.main_activity_fucation_icon_1, R.drawable.main_activity_fuction_icon_bg_1));
		mdata.add(new MetroButton("新浪微博", R.drawable.main_activity_fucation_icon_2, R.drawable.main_activity_fuction_icon_bg_1));
		mdata.add(new MetroButton("我的收藏", R.drawable.main_activity_fucation_icon_3, R.drawable.main_activity_fuction_icon_bg_1));
		mdata.add(new MetroButton("新闻头条", R.drawable.main_activity_fucation_icon_4, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("科技频道", R.drawable.main_activity_fucation_icon_5, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("汽车频道", R.drawable.main_activity_fucation_icon_6, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("军事频道", R.drawable.main_activity_fucation_icon_7, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("新华炫文", R.drawable.main_activity_fucation_icon_8, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("ͨ娱乐八卦", R.drawable.main_activity_fucation_icon_9, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("体育新闻", R.drawable.main_activity_fucation_icon_10, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("互联网新闻",R.drawable.main_activity_fucation_icon_11, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("奢侈品频道",R.drawable.main_activity_fucation_icon_12, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("时尚频道", R.drawable.main_activity_fucation_icon_13, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("财经频道", R.drawable.main_activity_fucation_icon_14, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("财经新闻", R.drawable.main_activity_fucation_icon_15, R.drawable.main_activity_fuction_icon_bg_2));
		mdata.add(new MetroButton("福布斯中文网",R.drawable.main_activity_fucation_icon_16,R.drawable.main_activity_fuction_icon_bg_2));

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		mainActivityPagerAdapter.setCURRENT_PAGE(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}





}
