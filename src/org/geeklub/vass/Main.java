package org.geeklub.vass;

import java.util.ArrayList;
import org.geeklub.adapter.Main_PagerAdapter;
import org.geeklub.beans.MetroButton;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

@ContentView(R.layout.activity_main)
public class Main extends RoboActivity implements OnPageChangeListener {
	@InjectView(R.id.main_pageNum) TextView tv_main_pageNum;
	@InjectView(R.id.main_viewPager) ViewPager vp_main;

	private ArrayList<MetroButton> mdata;

	//	private TextView tv_main_pageNum;
	//	private ViewPager vp_main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//		setContentView(R.layout.activity_main);

		tv_main_pageNum = (TextView) findViewById(R.id.main_pageNum);
		initData();
		vp_main.setAdapter(new Main_PagerAdapter(this, mdata));
		vp_main.setPageMargin(50);
		vp_main.setOnPageChangeListener(this);
	}

	private void initData() {
		//		ThreadPoolUtil.execute(new Runnable() {

		//		@Override
		//			public void run() {
		mdata = new ArrayList<MetroButton>();
		tv_main_pageNum.setText("1");
		mdata.add(new MetroButton("今日看点", R.drawable.icon1, R.drawable.icon_bg01));
		mdata.add(new MetroButton("新浪微博", R.drawable.icon2, R.drawable.icon_bg01));
		mdata.add(new MetroButton("我的收藏", R.drawable.icon3, R.drawable.icon_bg01));
		mdata.add(new MetroButton("新闻头条", R.drawable.icon4, R.drawable.icon_bg02));
		mdata.add(new MetroButton("科技频道", R.drawable.icon5, R.drawable.icon_bg02));
		mdata.add(new MetroButton("汽车频道", R.drawable.icon6, R.drawable.icon_bg02));
		mdata.add(new MetroButton("军事频道", R.drawable.icon7, R.drawable.icon_bg02));
		mdata.add(new MetroButton("新华炫文", R.drawable.icon8, R.drawable.icon_bg02));
		mdata.add(new MetroButton("ͨ娱乐八卦", R.drawable.icon9, R.drawable.icon_bg02));
		mdata.add(new MetroButton("体育新闻", R.drawable.icon10, R.drawable.icon_bg02));
		mdata.add(new MetroButton("互联网新闻",R.drawable.icon11, R.drawable.icon_bg02));
		mdata.add(new MetroButton("奢侈品频道",R.drawable.icon12, R.drawable.icon_bg02));
		mdata.add(new MetroButton("时尚频道", R.drawable.icon13, R.drawable.icon_bg02));
		mdata.add(new MetroButton("财经频道", R.drawable.icon14, R.drawable.icon_bg02));
		mdata.add(new MetroButton("财经新闻", R.drawable.icon15, R.drawable.icon_bg02));
		mdata.add(new MetroButton("福布斯中文网",R.drawable.icon16,R.drawable.icon_bg02));
		mdata.add(new MetroButton("旅游频道", R.drawable.icon3, R.drawable.icon_bg02));
		mdata.add(new MetroButton("游戏频道", R.drawable.icon8, R.drawable.icon_bg02));
		mdata.add(new MetroButton("开心笑话", R.drawable.icon10, R.drawable.icon_bg02));
		//			}
		//		});

	}



	@Override
	public void onPageScrollStateChanged(int position) {


	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		tv_main_pageNum.setText((int)(position+1) + "");
	}

}
