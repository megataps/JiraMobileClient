package com.pyco.android.jiramobileclient.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pyco.android.jiramobileclient.BaseApplication;
import com.pyco.android.jiramobileclient.R;
import com.pyco.android.jiramobileclient.entities.UserEntity;
import com.pyco.android.jiramobileclient.utils.AlertUtils;
import com.pyco.android.jiramobileclient.utils.StringUtils;


public class LoginActivity extends ActionBarActivity {

    private Button mBtnLogin;
    private EditText mTxtUsername;
    private EditText mTxtPassword;

    protected LoginAsyncTask mLoginAsyncTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mTxtUsername = (EditText) findViewById(R.id.txtUsername);
        mTxtPassword = (EditText) findViewById(R.id.txtPassword);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcess();
            }
        });


    }

    /**
     * Login process
     */
    private void loginProcess() {
        if(!isValidate())
            return;

        // Stop current process
        if (mLoginAsyncTask != null) {
            mLoginAsyncTask.mActivity = null;
            mLoginAsyncTask = null;
        }

        // Start new process
        mLoginAsyncTask = new LoginAsyncTask();
        mLoginAsyncTask.mActivity = this;

        mLoginAsyncTask.execute();
    }

    private boolean isValidate() {
        if(StringUtils.isEmpty(mTxtUsername.getText().toString())) {
            AlertUtils.showMessageAlert(this, getString(R.string.username_required));
            return false;
        }

        if(StringUtils.isEmpty(mTxtPassword.getText().toString())) {
            AlertUtils.showMessageAlert(this, getString(R.string.password_required));
            return false;
        }

        return true;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Stop login process in case the activity stopped
        if (mLoginAsyncTask != null) {

            if (isFinishing()) {
                mLoginAsyncTask.cancel(false);
            }

            mLoginAsyncTask.mActivity = null;
            mLoginAsyncTask = null;
        }
    }

    /**
     * LoginAsyncTask using to run background to get access token from Jira. When finished it the
     * the app will go to Home screen.
     */
    public static class LoginAsyncTask extends AsyncTask<Void, Void, UserEntity> {

        LoginActivity mActivity = null;

        @Override
        protected UserEntity doInBackground(Void... params) {
            try {
                if (mActivity != null && !mActivity.mLoginAsyncTask.isCancelled()) {
                    // TODO:
                    String username = mActivity.mTxtUsername.getText().toString();
                    String password = mActivity.mTxtPassword.getText().toString();
                    UserEntity userLoggedIn = new UserEntity(username, password);
                    return userLoggedIn;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(UserEntity result) {
            super.onPostExecute(result);

            if (mActivity != null && !mActivity.mLoginAsyncTask.isCancelled()) {

                if(result == null) {
                    AlertUtils.showMessageAlert(mActivity, mActivity.getString(R.string.login_failure));
                    return;
                }

                BaseApplication.getInstance().getSessionMaganger().saveUserLoggedInInformation(result);

                //Finished Login Activity and go to Main Activity
                Intent intent = new Intent(mActivity, MainActivity.class);
                mActivity.startActivity(intent);

                mActivity.finish();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (mActivity != null && !mActivity.mLoginAsyncTask.isCancelled()) {
                // Show waiting loading when login processing
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            mActivity.mLoginAsyncTask = null;
            mActivity = null;
        }
    }
}