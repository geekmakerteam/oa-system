package org.geeklub.adapter;

import java.util.ArrayList;
import org.geeklub.beans.MetroButton;
import org.geeklub.constant.Values;
import org.geeklub.vass.R;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityPagerAdapter extends PagerAdapter implements OnItemClickListener {
	private Context mcontext;
	private ArrayList<MetroButton> mdata;
	private LayoutInflater mlayoutInflater;
	private GridView gridview;
	private TextView tv_pageNum;
	private int CURRENT_PAGE;
	private String tag = "MainActivityPagerAdapter";









	public void setCURRENT_PAGE(int cURRENT_PAGE) {
		CURRENT_PAGE = cURRENT_PAGE;
	}

	public MainActivityPagerAdapter(Context mcontext, ArrayList<MetroButton> mdata) {
		this.mcontext = mcontext;
		this.mlayoutInflater = LayoutInflater.from(mcontext);
		this.mdata = mdata;

	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}

	//	@InjectView(R.layout.main_activity_pageradapter) View rootView;
	//	@InjectView(R.id.main_activity_pageradapter_gv) GridView gridview;
	//	@InjectView(R.id.main_activity_pageradapter_settting)  Button Button;
	//	@InjectView(R.id.main_activity_pageradapter_pageNum) TextView tv_pageNum;

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View rootView = mlayoutInflater.inflate(R.layout.main_activity_pageradapter, null);


		gridview = (GridView) rootView.findViewById(R.id.main_activity_pageradapter_gv);
		gridview.setNumColumns(2);
		gridview.setVerticalSpacing(5);
		gridview.setHorizontalSpacing(5);
		gridview.setAdapter(new MainActivityGridViewAdapter(mcontext,mdata,position));
		gridview.setOnItemClickListener(this);

		tv_pageNum = (TextView) rootView.findViewById(R.id.main_activity_pageradapter_pageNum);
		tv_pageNum.setText((int)(position+1) + "");
		Log.i(tag, "instantiateItem");



		((ViewPager)container).addView(rootView);

		return rootView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager)container).removeViewAt(position);
		super.destroyItem(container, position, object);
	}








	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Log.i(tag, "当前的页面是=====>"+ CURRENT_PAGE);
		onSelectFuction(CURRENT_PAGE * Values.PAGE_SIZE + position);
	}




	private void onSelectFuction(int i) {
		Toast.makeText(mcontext, "点击了第"+ i + "个功能", Toast.LENGTH_SHORT).show();

		switch (i) {
		case Values.REPAIR_ACTIVITY:

			break;

		case Values.SIGN_IN_ACTIVITY:

			break;

		default:
			break;
		}


	}

}
