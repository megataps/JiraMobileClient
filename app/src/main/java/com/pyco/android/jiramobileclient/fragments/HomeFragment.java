package com.pyco.android.jiramobileclient.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.pyco.android.jiramobileclient.BaseApplication;
import com.pyco.android.jiramobileclient.R;
import com.pyco.android.jiramobileclient.activities.MainActivity;
import com.pyco.android.jiramobileclient.adapters.IssueListAdapter;
import com.pyco.android.jiramobileclient.entities.Issue;
import com.pyco.android.jiramobileclient.entities.UserEntity;
import com.pyco.android.jiramobileclient.services.IBaseService;
import com.pyco.android.jiramobileclient.services.IIssueFilter;
import com.pyco.android.jiramobileclient.services.JiraService;
import com.pyco.android.jiramobileclient.utils.CLog;

import java.util.ArrayList;

/**
 * Created by jackie on 12/4/14.
 */
public class HomeFragment extends Fragment implements IBaseService, SwipeRefreshLayout.OnRefreshListener{

    private static String TAG = HomeFragment.class.getName();

    private ListView mLvIssues;
    private IssueListAdapter mIssueListAdapter;
    private ArrayList<Issue> mIssues;
    private JiraService mService;

    protected LinearLayout mLlMessageContainer;
    protected ProgressBar mPgbLoading;
    private Button mBtnReload;

    SwipeRefreshLayout swipeLayout;

    private IIssueFilter mIssueFilter;
    private UserEntity mUserEntity;
    private int mSectionIndex;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionIndex, IIssueFilter issueFilter, UserEntity user) {
        HomeFragment fragment = new HomeFragment();
        fragment.mIssueFilter = issueFilter;
        fragment.mUserEntity = user;
        fragment.mSectionIndex = sectionIndex;
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // TODO: Change screen title
//        ((MainActivity) activity).onSectionAttached(
//                getArguments().getInt(mSectionIndex));
        ((MainActivity) activity).onSectionAttached(mSectionIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mLvIssues = (ListView) rootView.findViewById(R.id.lvIssues);

        swipeLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mLlMessageContainer = (LinearLayout) rootView.findViewById(R.id.llMessageContainer);
        mPgbLoading = (ProgressBar) rootView.findViewById(R.id.pgbLoading);
        mBtnReload = (Button) rootView.findViewById(R.id.btnReload);

        mBtnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIssues();
            }
        });

        mIssues = new ArrayList<Issue>();
        mIssueListAdapter = new IssueListAdapter(getActivity(), mIssues);
        mLvIssues.setAdapter(mIssueListAdapter);

        mService = JiraService.getInstance();
        mService.setListener(this);

        /*
         * http://nlopez.io/swiperefreshlayout-with-listview-done-right/
         */
        mLvIssues.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (mLvIssues == null || mLvIssues.getChildCount() == 0) ?
                                0 : mLvIssues.getChildAt(0).getTop();
                swipeLayout.setEnabled(topRowVerticalPosition >= 0);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadIssues();
    }

    private void loadIssues() {
        mService.loadIssue(mUserEntity.getUsername(), mUserEntity.getPassword(),
                BaseApplication.getInstance(),mIssueFilter);
    }

    public void onErrorListener(Exception ex) {
        mLlMessageContainer.setVisibility(View.VISIBLE);
        mPgbLoading.setVisibility(View.GONE);
        mLvIssues.setVisibility(View.GONE);
    }

    public void onResponseListener(ArrayList<Issue> issues) {
        mPgbLoading.setVisibility(View.GONE);
        mLvIssues.setVisibility(View.VISIBLE);
        mIssues.addAll(issues);

        CLog.e(TAG, "mIssues.size():" + mIssues.size());

        mIssueListAdapter.notifyDataSetChanged();
    }

    public void onPreProcessListener() {
        mLlMessageContainer.setVisibility(View.GONE);
        mPgbLoading.setVisibility(View.VISIBLE);
        mLvIssues.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 1000);

        loadIssues();
    }
}