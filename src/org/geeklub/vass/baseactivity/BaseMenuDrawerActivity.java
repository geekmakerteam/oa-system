package org.geeklub.vass.baseactivity;

import java.util.ArrayList;
import java.util.List;
import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import org.geeklub.adapter.MenuAdapter;
import org.geeklub.adapter.MenuAdapter.MenuListener;
import org.geeklub.beans.Item;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public abstract class BaseMenuDrawerActivity extends SherlockFragmentActivity  implements MenuListener{

	private static final String STATE_ACTIVE_POSITION =
			"net.simonvt.menudrawer.samples.LeftDrawerSample.activePosition";

	protected MenuDrawer mMenuDrawer;

	protected MenuAdapter mAdapter;
	protected ListView mList;
	protected List<Object> items = new ArrayList<Object>();


	private int mActivePosition = 0;

	@Override
	protected void onCreate(Bundle inState) {
		super.onCreate(inState);

		if (inState != null) {
			mActivePosition = inState.getInt(STATE_ACTIVE_POSITION);
		}

		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, getDrawerPosition(), getDragMode());


		initView(items);

		mList = new ListView(this);

		mAdapter = new MenuAdapter(this, items);
		mAdapter.setListener(this);
		mAdapter.setActivePosition(mActivePosition);

		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(mItemClickListener);

		mMenuDrawer.setMenuView(mList);
	}

	protected abstract void onMenuItemClicked(int position, Item item);

	protected abstract int getDragMode();

	protected abstract Position getDrawerPosition();

	protected abstract void initView(List<Object> items);

	private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			mActivePosition = position;
			mMenuDrawer.setActiveView(view, position);
			mAdapter.setActivePosition(position);
			onMenuItemClicked(position, (Item) mAdapter.getItem(position));
		}
	};

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_ACTIVE_POSITION, mActivePosition);
	}

	@Override
	public void onActiveViewChanged(View v) {
		mMenuDrawer.setActiveView(v, mActivePosition);
	}

}
