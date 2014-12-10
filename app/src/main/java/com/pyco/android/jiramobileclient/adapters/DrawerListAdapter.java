package com.pyco.android.jiramobileclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pyco.android.jiramobileclient.BaseApplication;
import com.pyco.android.jiramobileclient.R;
import com.pyco.android.jiramobileclient.entities.MenuItemEntity;

import java.util.ArrayList;

public class DrawerListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
    private ArrayList<MenuItemEntity> mMenuItems;

	public DrawerListAdapter(Context context, ArrayList<MenuItemEntity> menuItems) {
		mInflater = LayoutInflater.from(context);
        mMenuItems = menuItems;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (mMenuItems != null) {
			count = mMenuItems.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {

		return mMenuItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null || convertView != null
				&& !(convertView.getTag() instanceof ViewHolder)) {

			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.lv_navigation_drawer_item, null);

			holder.mLblName = (TextView) convertView.findViewById(R.id.lblName);
			holder.mImgThumbnail = (ImageView) convertView
					.findViewById(R.id.imgThumbnail);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (mMenuItems.size() > position) {

            MenuItemEntity menuItem = mMenuItems.get(position);
			holder.mLblName.setText(menuItem.getName());

            if(menuItem.getType() == MenuItemEntity.ITEM) {
                holder.mLblName.setTextColor(BaseApplication.getInstance().getApplicationContext().getResources().getColor(R.color.white));
            } else {
                holder.mLblName.setTextColor(BaseApplication.getInstance().getApplicationContext().getResources().getColor(R.color.gray));
            }
		}

		return convertView;
	}

	static class ViewHolder {
        ImageView mImgThumbnail;
        TextView mLblName;
	}
}
