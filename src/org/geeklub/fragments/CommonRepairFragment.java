package org.geeklub.fragments;


import org.geeklub.constant.Values;
import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.geeklub.vass.activitys.RepairDetailActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardListView;
import com.afollestad.cardsui.CardListView.CardClickListener;
import com.loopj.android.http.JsonHttpResponseHandler;


/**
 * 学生报修，生活老师审核，报修员确认的公用的Fragment
 * @author vass
 *
 */
public class CommonRepairFragment extends Fragment implements CardClickListener{

	//	UI控件
	private CardListView cardsListView = null;
	private ProgressBar progressBar = null;

	private CardAdapter<Card> cardAdapter;
	//当前Activity的引用
	private Activity mactivity;
	private JSONArray jsonArray = null;

	//	private List<Map<String,Object>> mList;

	private String tag = "CommonRepairFragment";






	/**
	 * 维修员和生活老师公用的构造函数
	 * @param url  数据源的url
	 * @param sub_url   用于提交操作的url
	 * @param hide_title  是否隐藏标题
	 * @param title    标题名
	 * @param hide_pass_btn    是否隐藏通过按钮
	 * @param hide_not_pass_btn  是否隐藏不通过按钮
	 * @return  CommonRepairFragment
	 */
	public static Fragment newInstance(String url,
			String sub_url,
			boolean hide_title,
			String title,
			boolean hide_pass_btn,
			boolean hide_not_pass_btn) {

		Fragment fragment = new CommonRepairFragment();

		Bundle args = new Bundle();
		args.putString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL, url);
		args.putString(Values.SUBMIT_URL, sub_url);
		args.putString(Values.TITLE, title);
		args.putBoolean(Values.HIDE_TITLE, hide_title);
		args.putBoolean(Values.HIDE_PASS_BUTTON, hide_pass_btn);
		args.putBoolean(Values.HIDE_NOT_PASS_BUTTON, hide_not_pass_btn);
		fragment.setArguments(args);

		return fragment;
	}

	/**
	 * 学生的构造函数
	 * @param url 数据源的url
	 * @param hide_title  是否隐藏标题
	 * @param hide_pass_btn 是否隐藏通过按钮
	 * @param hide_not_pass_btn  是否隐藏不通过按钮
	 * @return CommonRepairFragment
	 */

	public static Fragment newInstance(String url,
			boolean hide_title,
			boolean hide_pass_btn,
			boolean hide_not_pass_btn) {

		Fragment fragment = new CommonRepairFragment();

		Bundle args = new Bundle();
		args.putString(Values.COMMON_FRAGMENT_SOURCE_DATA_URL, url);
		args.putBoolean(Values.HIDE_TITLE, hide_title);
		args.putBoolean(Values.HIDE_PASS_BUTTON, hide_pass_btn);
		args.putBoolean(Values.HIDE_NOT_PASS_BUTTON, hide_not_pass_btn);
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

		cardsListView.setOnCardClickListener(this);



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

		//		getArguments().get(Values.COMMON_FRAGMENT_SOURCE_DATA_URL).toString()得到获取数据源的url
		//		OaAsyncHttpClient.get(getArguments().get(Values.COMMON_FRAGMENT_SOURCE_DATA_URL).toString(), null, new AsyncHttpResponseHandler(){
		//
		//
		//			@Override
		//			public void onSuccess(int statusCode, String content) {
		//				super.onSuccess(statusCode, content);
		//				progressBar.setVisibility(View.GONE);
		//				Log.i(tag, content);
		//				try {
		//
		//					mList = FastJsonParse.getJsonObject(content, RepairInfo.class).getmList();
		//					Log.i(tag, mList.toString());
		//					for(Map<String,Object> map : mList){
		//						cardAdapter.add(new Card(map.get(Values.REP_REPAIR_DATE).toString(), 
		//								map.get(Values.REP_DESCRIPE).toString()
		//								));
		//					}
		//					Log.i(tag, mList.toString());
		//
		//
		//				} catch (Exception e) {
		//				}
		//			}
		//		}, mactivity);

		OaAsyncHttpClient.get(getArguments().get(Values.COMMON_FRAGMENT_SOURCE_DATA_URL).toString(), null, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, JSONObject response) {

				super.onSuccess(statusCode, response);

				progressBar.setVisibility(View.GONE);

				try {
					jsonArray = response.getJSONArray("datas");

					for(int i = 0 ; i < jsonArray.length();i++){
						cardAdapter.add(new Card(jsonArray.getJSONObject(i).getString("repairDate"),
								jsonArray.getJSONObject(i).getString("repDescripe")));
					}


				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable e, JSONArray errorResponse) {

				super.onFailure(e, errorResponse);

				progressBar.setVisibility(View.GONE);

			}

		}, mactivity);
	}




	@Override
	public void onCardClick(int index, CardBase card, View view) {
		Log.i(tag, "点击了card");
		Intent intent = new Intent();

		try {
			JSONObject jsonObject = jsonArray.getJSONObject(index);



			Bundle data = new Bundle();
			//			Map<String,Object> map = mList.get(index);

			//			data.putBoolean("title_isHide", getArguments().getBoolean("title_isHide"));
			//			data.putBoolean("btn_pass_isHide", getArguments().getBoolean("btn_pass_isHide"));
			//			data.putBoolean("btn_not_pass_isHide", getArguments().getBoolean("btn_not_pass_isHide"));
			//			data.putString("sub_url", getArguments().getString("sub_url"));
			//			data.putString("title", getArguments().getString("title"));


			data.putString(Values.SUBMIT_URL, getArguments().getString(Values.SUBMIT_URL));
			data.putString(Values.TITLE, getArguments().getString(Values.TITLE));
			data.putBoolean(Values.HIDE_TITLE, getArguments().getBoolean(Values.HIDE_TITLE));
			data.putBoolean(Values.HIDE_PASS_BUTTON, getArguments().getBoolean(Values.HIDE_PASS_BUTTON));
			data.putBoolean(Values.HIDE_NOT_PASS_BUTTON, getArguments().getBoolean(Values.HIDE_NOT_PASS_BUTTON));

			data.putString(Values.REP_ID, (String) jsonObject.get(Values.REP_ID));
			data.putString(Values.REP_USER_NAME, (String) jsonObject.get(Values.REP_USER_NAME));
			data.putString(Values.REP_ADDRESS, (String) jsonObject.get(Values.REP_ADDRESS));
			data.putString(Values.REP_ACCIDENT_DATE, (String) jsonObject.get(Values.REP_ACCIDENT_DATE));
			data.putString(Values.REP_REPAIR_DATE, (String) jsonObject.get(Values.REP_REPAIR_DATE));
			data.putString(Values.REP_DESCRIPE, (String) jsonObject.get(Values.REP_DESCRIPE));
			data.putString(Values.REP_IMAGE_ADDRESS, (String) jsonObject.get(Values.REP_IMAGE_ADDRESS));




			//			data.putString("repairId", jsonObject.getString("repairId"));
			//			data.putString("userName", jsonObject.getString("userName"));
			//			data.putString("address", jsonObject.getString("address"));
			//			data.putString("accidentDate", jsonObject.getString("accidentDate"));
			//			data.putString("repairDate", jsonObject.getString("repairDate"));
			//			data.putString("repDescripe", jsonObject.getString("repDescripe"));
			//			data.putString("imageAddress", jsonObject.getString("imageAddress"));
			intent.putExtra(Values.REP_DATA, data);
			intent.setClass(mactivity, RepairDetailActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			Log.i(tag, "intent.toUri(Intent.FLAG_ACTIVITY_CLEAR_TOP)====>"+intent.toUri(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			

			mactivity.startActivity(intent);

		} catch (Exception e) {

		}





	}



}
