package com.pyco.android.jiramobileclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pyco.android.jiramobileclient.R;
import com.pyco.android.jiramobileclient.entities.Issue;

import java.util.ArrayList;


public class IssueListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
    private ArrayList<Issue> mIssues;

	public IssueListAdapter(Context context, ArrayList<Issue> issues) {
		mInflater = LayoutInflater.from(context);
        mIssues = issues;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (mIssues != null) {
			count = mIssues.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {

		return mIssues.get(position);
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
			convertView = mInflater.inflate(R.layout.lv_issue_item, null);

			holder.mTxtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.mTxtIssueId = (TextView) convertView.findViewById(R.id.txtIssueId);
			holder.mImgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (mIssues.size() > position) {
            Issue issue = mIssues.get(position);
            holder.mTxtTitle.setText(issue.getSummary());
            holder.mTxtIssueId.setText(issue.getKey());
		}

		return convertView;
	}

	static class ViewHolder {
        ImageView mImgIcon;
        TextView mTxtTitle;
        TextView mTxtIssueId;

	}
}
