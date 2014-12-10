package com.pyco.android.jiramobileclient;

import com.pyco.android.jiramobileclient.entities.UserEntity;
import com.pyco.android.jiramobileclient.services.AssignedToMeFilter;
import com.pyco.android.jiramobileclient.services.IIssueFilter;
import com.pyco.android.jiramobileclient.services.RecentlyViewedFilter;
import com.pyco.android.jiramobileclient.services.ReportedByMeFilter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by jackie on 12/10/14.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class IssueFilterTest {

    IIssueFilter filter;
    UserEntity userLoggedIn;

    @Before
    public void setup() {
        userLoggedIn = new UserEntity("username","password");
    }

    @Test
    public void testFilterIsReportedByMe() {
        filter = new ReportedByMeFilter();
        assertThat(filter.getClass().getName(), equalTo(ReportedByMeFilter.class.getName()));
    }

    @Test
    public void testFilterIsAssignToMe() {
        filter = new AssignedToMeFilter(userLoggedIn);
        assertThat(filter.getClass().getName(), equalTo(AssignedToMeFilter.class.getName()));
    }

    @Test
    public void testFilterIsRecentlyViewed() {
        filter = new RecentlyViewedFilter();
        assertThat(filter.getClass().getName(), equalTo(RecentlyViewedFilter.class.getName()));
    }
}
