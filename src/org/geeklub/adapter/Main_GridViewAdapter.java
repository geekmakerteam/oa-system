package org.geeklub.adapter;

import java.util.ArrayList;

import org.geeklub.beans.MetroButton;
import org.geeklub.vass.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main_GridViewAdapter extends BaseAdapter{
	private ArrayList<MetroButton> mdata;
	private LayoutInflater mlayoutInflater;
	private int index;


	public Main_GridViewAdapter(Context context,ArrayList<MetroButton> mdata,int position){
		this.mlayoutInflater = LayoutInflater.from(context);
		this.mdata = mdata;
		this.index = position;
	}

	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public Object getItem(int position) {
		return mdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;


		if(convertView==null){
			convertView = mlayoutInflater.inflate(R.layout.activity_main_gridview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.im_metro_picture = (ImageView) convertView.findViewById(R.id.im_src);
			viewHolder.tv_metro_name = (TextView) convertView.findViewById(R.id.title);
			viewHolder.rl_metro_background = (RelativeLayout) convertView.findViewById(R.id.rl_bg);


			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.im_metro_picture.setBackgroundResource((mdata.get(index * 8 + position)).getImageID());
		viewHolder.tv_metro_name.setText((mdata.get(index * 8 + position)).getTitle());
		viewHolder.rl_metro_background.setBackgroundResource((mdata.get(index * 8 + position)).getBgID());
		viewHolder.rl_metro_background.getBackground().setAlpha(225);



		return convertView;
	}


	static class ViewHolder{
		TextView tv_metro_name;
		ImageView im_metro_picture;
		RelativeLayout rl_metro_background;
	}

}
