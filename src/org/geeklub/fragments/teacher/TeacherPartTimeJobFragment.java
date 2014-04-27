package org.geeklub.fragments.teacher;

import org.geeklub.http.OaAsyncHttpClient;
import org.geeklub.vass.R;
import org.geeklub.vass.function.teacher.SeePartTimeJobDetailInfoActivity;
import org.json.JSONArray;
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

public class TeacherPartTimeJobFragment extends Fragment  implements CardClickListener{

	//	UI控件
	private CardListView cardsListView = null;
	private ProgressBar progressBar = null;

	private CardAdapter<Card> cardAdapter;
	//当前Activity的引用
	private Activity mactivity;
	private JSONArray jsonArray = null;


	private String tag = "TeacherPartTimeJobListFragment";



	/**
	 * 
	 * @param url
	 * @param sub_url
	 * @param title
	 * @param hide_tv_choice  隐藏“该同学的去向”
	 * @param hide_rg_all_wish 隐藏“RadioGroup”
	 * @param hide_btn_submit  隐藏button
	 * @return
	 */


	public static Fragment newInstance(String url,
			String sub_url,
			String title,
			boolean hide_tv_choice,
			boolean hide_rg_all_wish,
			boolean hide_btn_submit
			) {
		Fragment fragment = new TeacherPartTimeJobFragment();
		Bundle args = new Bundle();
		args.putString("url", url);
		args.putString("sub_url", sub_url);
		args.putString("title", title);


		args.putBoolean("hide_tv_choice", hide_tv_choice);
		args.putBoolean("hide_rg_all_wish", hide_rg_all_wish);
		args.putBoolean("hide_btn_submit",hide_btn_submit );



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

		cardAdapter = new CardAdapter<Card>(mactivity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.common_repair_fragment, null);

		progressBar = (ProgressBar) rootView.findViewById(R.id.common_repair_fragment_progressbar);
		cardsListView = (CardListView) rootView.findViewById(R.id.common_repair_fragment_cardsList);

		cardsListView.setOnCardClickListener(this);



		return rootView;
	}



	@Override
	public void onStart() {
		super.onStart();

		cardAdapter.clear();
		showCardList();
	}

	private void showCardList() {
		progressBar.setVisibility(View.VISIBLE);

		cardsListView.setAdapter(cardAdapter);	



		OaAsyncHttpClient.get(getArguments().getString("url"), null, new JsonHttpResponseHandler(){


			@Override
			public void onSuccess(int statusCode, JSONObject response) {
				super.onSuccess(statusCode, response);
				progressBar.setVisibility(View.GONE);

				try {
					jsonArray = response.getJSONArray("datas");

					for(int i = 0 ; i < jsonArray.length();i++){
						cardAdapter.add(new Card(jsonArray.getJSONObject(i).getString("userName"),
								"志愿1："+jsonArray.getJSONObject(i).getString("firstWish")+" | 志愿2："
										+ jsonArray.getJSONObject(i).getString("secondWish")
								));
					}


				} catch (Exception e) {

					e.printStackTrace();
				}

			}


			@Override
			public void onFailure(Throwable e, JSONObject errorResponse) {
				super.onFailure(e, errorResponse);
				progressBar.setVisibility(View.GONE);
			}
		}, mactivity);
	}




	@Override
	public void onCardClick(int index, CardBase card, View view) {
		Log.i(tag, "点击了第"+index+"个card");
		Intent intent = new Intent();
		Bundle data = new Bundle();

		try {
			
			JSONObject jsonObject = jsonArray.getJSONObject(index);

			String firstWish = jsonObject.getString("firstWish");
			Log.i(tag, "firstWish===>"+firstWish);
			String secondWish = jsonObject.getString("secondWish");
			Log.i(tag, "secondWish===>"+secondWish);
			String status = jsonObject.getString("status");
			Log.i(tag, "status===>"+status);
			String userName = jsonObject.getString("userName");
			Log.i(tag, "userName===>"+userName);
			String imageAddress = jsonObject.getString("imageAddress");
			Log.i(tag, "imageAddress===>"+imageAddress);
			String hwstudyId = jsonObject.getString("hwstudyId");
			Log.i(tag, "hwstudyId===>"+hwstudyId);

			data.putString("firstWish", firstWish);
			data.putString("secondWish", secondWish);
			data.putString("status", status);
			data.putString("userName", userName);
			data.putString("imageAddress", imageAddress);
			data.putString("hwstudyId", hwstudyId);

			data.putString("sub_url", getArguments().getString("sub_url"));
			Log.i(tag, "sub_url===>"+getArguments().getString("sub_url"));
			data.putString("title",getArguments().getString("title") );
			Log.i(tag, "title===>"+getArguments().getString("title"));
			data.putBoolean("hide_tv_choice", getArguments().getBoolean("hide_tv_choice"));
			Log.i(tag, "hide_tv_choice===>"+getArguments().getString("hide_tv_choice"));
			data.putBoolean("hide_rg_all_wish", getArguments().getBoolean("hide_rg_all_wish"));
			Log.i(tag, "hide_rg_all_wish===>"+getArguments().getString("hide_rg_all_wish"));
			data.putBoolean("hide_btn_submit", getArguments().getBoolean("hide_btn_submit"));
			Log.i(tag, "hide_btn_submit===>"+getArguments().getString("hide_btn_submit"));
			//			data.putBundle("args", getArguments());


			//			intent.putExtra("args", getArguments());
			intent.putExtra("data", data);
			intent.setClass(mactivity, SeePartTimeJobDetailInfoActivity.class);
			mactivity.startActivity(intent);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);




		} catch (Exception e) {
			Log.i(tag, "fgdasfad");
		}
		

	}



}
