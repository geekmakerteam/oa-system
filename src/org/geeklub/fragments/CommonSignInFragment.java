package org.geeklub.fragments;

import org.geeklub.constant.Values;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.geeklub.vass.function.teacher.StudentSignInListActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardListView;
import com.afollestad.cardsui.CardListView.CardClickListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * 老师的点名和学生的点到模块
 * @author vass
 *
 */

public class CommonSignInFragment extends Fragment implements CardClickListener{
	//	UI控件
	private CardListView cardsListView = null;
	private ProgressBar progressBar = null;

	private CardAdapter<Card> cardAdapter;
	//当前Activity的引用
	private Activity mactivity;
	private JSONArray jsonArray = null;

	private String tag = "CommonSignInFragment";




	//	静态的函数，用来生成CommonSignInFragment
	public static Fragment newInstance(String url, boolean isClick,String jsonKey_1,String jsonKey_2) {

		Fragment fragment = new CommonSignInFragment();

		Bundle args = new Bundle();
		args.putString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL, url);
		args.putBoolean("isClick", isClick);
		args.putString("jsonKey_1", jsonKey_1);
		args.putString("jsonKey_2", jsonKey_2);
		fragment.setArguments(args);

		return fragment;
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		this.mactivity = activity;
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(tag, getArguments().getString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL) + "===>onCreate"  );

		cardAdapter = new CardAdapter<Card>(mactivity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(tag, getArguments().getString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL) + "===>onCreateView");

		View rootView = inflater.inflate(R.layout.common_repair_fragment, null);

		progressBar = (ProgressBar) rootView.findViewById(R.id.common_repair_fragment_progressbar);
		cardsListView = (CardListView) rootView.findViewById(R.id.common_repair_fragment_cardsList);

		if(getArguments().getBoolean("isClick")){
			Log.i(tag, "设置监听事件");
			cardsListView.setOnCardClickListener(this);
		}



		return rootView;
	}




	@Override
	public void onStart() {
		Log.i(tag, getArguments().getString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL) + "===>onStart");

		super.onStart();

		cardAdapter.clear();
		showCardList();



	}

	@Override
	public void onResume() {
		Log.i(tag, getArguments().getString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL) + "===>onResume");
		super.onResume();
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(tag, getArguments().getString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL) + "===>onDestroyView");
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(tag, getArguments().getString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL) + "===>onDestroy");

	}







	private void showCardList() {

		progressBar.setVisibility(View.VISIBLE);

		cardsListView.setAdapter(cardAdapter);

		OaAsyncHttpClient.get(getArguments().get(Values.COMMON_FRAGMENT_SOURCE_DATA_URL).toString(), null, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {

				super.onSuccess(statusCode, response);

				Log.i(tag, response.toString());

				progressBar.setVisibility(View.GONE);

				try {
					jsonArray = response.getJSONArray("datas");
					Log.i(tag, "url===>"+getArguments().getString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL)+";jsonArray====>"+jsonArray);

					for(int i = 0 ; i < jsonArray.length();i++){
						cardAdapter.add(new Card(jsonArray.getJSONObject(i).getString(getArguments().getString("jsonKey_1")),
								jsonArray.getJSONObject(i).getString(getArguments().getString("jsonKey_2"))));
					}
				} catch (JSONException e) {

					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable e, JSONArray errorResponse) {

				super.onFailure(e, errorResponse);
				Log.i(tag, errorResponse.toString());

				progressBar.setVisibility(View.GONE);

			}

		}, mactivity);
	}




	@Override
	public void onCardClick(int index, CardBase card, View view) {
		Intent intent = new Intent();

		try {
			JSONObject jsonObject = jsonArray.getJSONObject(index);

			Bundle data = new Bundle();

			data.putString("courseId", jsonObject.getString("courseId"));

			intent.putExtra("data", data);

			intent.setClass(mactivity, StudentSignInListActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			mactivity.startActivity(intent);

		} catch (Exception e) {

		}





	}
}
