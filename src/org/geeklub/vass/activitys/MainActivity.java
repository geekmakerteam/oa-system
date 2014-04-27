package org.geeklub.vass.activitys;

import java.util.ArrayList;
import java.util.Map;

import org.geeklub.adapter.MainActivityPagerAdapter;
import org.geeklub.application.OaApplication;
import org.geeklub.beans.MetroButton;
import org.geeklub.beans.Result;
import org.geeklub.constant.Url;
import org.geeklub.constant.Values;
import org.geeklub.http.FastJsonParse;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.utils.OaPreferenceSettings;
import org.geeklub.vass.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

@ContentView(R.layout.main_activity)
public class MainActivity extends RoboActivity implements OnPageChangeListener{

	private static final String tag = "MainActivity";
	private Map<String, MetroButton> mMap ;
	private Activity mactivity;


	@InjectView(R.id.main_activity_viewPager) ViewPager vp_main;



	private ArrayList<MetroButton> mdata;
	private MainActivityPagerAdapter mainActivityPagerAdapter;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mactivity = this;

		initData();


	}

	private void initData() {




		//		mMap = OaApplication.getmMetroButtonMap();
		//		mdata = new ArrayList<MetroButton>();
		//		String[] fuctions = getIntent().getStringArrayExtra("fuctions");

		//		for(String title: fuctions){
		//			mdata.add(mMap.get(title));
		//		}


		RequestParams params = new RequestParams();
		params.put(Values.USER_NAME, OaPreferenceSettings.getUserName(mactivity));
		params.put(Values.PASS_WORD, OaPreferenceSettings.getPassWord(mactivity));


		OaAsyncHttpClient.post(Url.USER_LOGIN, params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, String content) {
				super.onSuccess(statusCode, content);


				Log.i(tag, "content:"+content);


				String[] fuctions = FastJsonParse.getJsonObject(content, Result.class).getDatas();

				//				Log.i(tag, "fuctions.toString()"+fuctions.toString());

				mMap = OaApplication.getmMetroButtonMap();
				mdata = new ArrayList<MetroButton>();

				for(String title: fuctions){
					mdata.add(mMap.get(title));
				}

				mainActivityPagerAdapter = new MainActivityPagerAdapter(mactivity, mdata);
				vp_main.setAdapter(mainActivityPagerAdapter);
				vp_main.setOnPageChangeListener((OnPageChangeListener) mactivity);
				vp_main.setPageMargin(100);
			}



		}, this);



		//		mdata.add(new MetroButton("报修", R.drawable.main_activity_fucation_icon_1, R.drawable.main_activity_fuction_icon_bg_1));
		//		mdata.add(new MetroButton("签到", R.drawable.main_activity_fucation_icon_2, R.drawable.main_activity_fuction_icon_bg_1));
		//		mdata.add(new MetroButton("我的点名", R.drawable.main_activity_fucation_icon_3, R.drawable.main_activity_fuction_icon_bg_1));
		//		mdata.add(new MetroButton("报修审核", R.drawable.main_activity_fucation_icon_4, R.drawable.main_activity_fuction_icon_bg_2));
		//		mdata.add(new MetroButton("报修确认", R.drawable.main_activity_fucation_icon_5, R.drawable.main_activity_fuction_icon_bg_2));
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		Log.i(tag, "onPageSelected:"+position+"");
		mainActivityPagerAdapter.setCURRENT_PAGE(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}





}
