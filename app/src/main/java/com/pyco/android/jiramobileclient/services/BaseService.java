package com.pyco.android.jiramobileclient.services;

import com.pyco.android.jiramobileclient.utils.Base64;

/**
 * Created by jackie on 12/9/14.
 */
public abstract class BaseService {

    protected IBaseService mBaseServiceListener;
    protected IIssueFilter mIssueFilter;

    public void setListener(IBaseService listener) {
        mBaseServiceListener = listener;
    }

    protected String createBasicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        String base64EncodedCredentials = Base64.encode(credentials.getBytes());
        return "Basic " + base64EncodedCredentials;
    }

    public void onError(Exception exception) {
        if (mBaseServiceListener != null) {
            mBaseServiceListener.onErrorListener(exception);
        }
    }
}
