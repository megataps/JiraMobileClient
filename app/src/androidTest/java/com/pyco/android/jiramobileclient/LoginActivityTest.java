package com.pyco.android.jiramobileclient;

import android.widget.Button;
import android.widget.EditText;

import com.pyco.android.jiramobileclient.activities.LoginActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by jackie on 12/10/14.
 */
// Fix issue when run test
// Link: https://github.com/robolectric/robolectric/issues/1332
// emulateSdk = 18: Robolectric don't allow run test on android api > 18
// reportSdk = 18: Because Activity using some style on Api > 18. This setting trick about that.
@Config(emulateSdk = 18, reportSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    private LoginActivity activity;
    private Button btnLogin;
    private EditText txtUsernname;
    private EditText txtPassword;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(LoginActivity.class).create().get();
        btnLogin = (Button)activity.findViewById(R.id.btnLogin);
        txtUsernname = (EditText)activity.findViewById(R.id.txtUsername);
        txtPassword = (EditText)activity.findViewById(R.id.txtPassword);
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        Assert.assertNotNull(activity);
    }

    @Test
    public void checkWidgetInActivityNotNull() throws Exception {
        Assert.assertNotNull(btnLogin);
        Assert.assertNotNull(txtUsernname);
        Assert.assertNotNull(txtPassword);
    }

    @Test
    public void testLoginButtonClick() throws Exception {
        
    }
}
