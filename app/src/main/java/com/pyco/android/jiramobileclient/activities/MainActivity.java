package com.pyco.android.jiramobileclient.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.pyco.android.jiramobileclient.BaseApplication;
import com.pyco.android.jiramobileclient.R;
import com.pyco.android.jiramobileclient.entities.UserEntity;
import com.pyco.android.jiramobileclient.fragments.HomeFragment;
import com.pyco.android.jiramobileclient.services.AssignedToMeFilter;
import com.pyco.android.jiramobileclient.services.IIssueFilter;
import com.pyco.android.jiramobileclient.services.RecentlyViewedFilter;
import com.pyco.android.jiramobileclient.services.ReportedByMeFilter;
import com.pyco.android.jiramobileclient.utils.CLog;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        CLog.e(MainActivity.class.getName(), "onNavigationDrawerItemSelected-Position:" + position);

        UserEntity user = BaseApplication.getInstance().getSessionMaganger().getLoggedInUser();
        IIssueFilter issueFilter = null;
        if(position == 1) {
            issueFilter = new AssignedToMeFilter(user);
        } else if(position == 2) {
            issueFilter = new ReportedByMeFilter();
        } else if(position == 3){
            issueFilter = new RecentlyViewedFilter();
        } else {
            // TODO:
            issueFilter = new AssignedToMeFilter(user);
        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance(position, issueFilter, user))
                .commit();
    }

    public void onSectionAttached(int number) {

        mTitle = mNavigationDrawerFragment.getSelectedItemName(number);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
