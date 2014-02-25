package org.geeklub.adapter;

import java.util.ArrayList;
import org.geeklub.beans.MetroButton;
import org.geeklub.vass.R;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Main_PagerAdapter extends PagerAdapter implements OnItemClickListener {
	private Context mcontext;
	private ArrayList<MetroButton> mdata;
	private LayoutInflater mlayoutInflater;
	private String tag = "Main_PagerAdapter";




	public Main_PagerAdapter(Context mcontext, ArrayList<MetroButton> mdata) {
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

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View rootView = mlayoutInflater.inflate(R.layout.activiy_main_pageradapter_gridview, null);

		GridView gridView = (GridView) rootView.findViewById(R.id.gv_metro);
		gridView.setNumColumns(2);
		gridView.setVerticalSpacing(5);
		gridView.setHorizontalSpacing(5);
		gridView.setAdapter(new Main_GridViewAdapter(mcontext,mdata,position));
		gridView.setOnItemClickListener(this);

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

	}

}
