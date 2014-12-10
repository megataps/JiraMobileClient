package com.pyco.android.jiramobileclient.services;


import com.pyco.android.jiramobileclient.entities.Issue;

import java.util.ArrayList;

/**
 * Created by jackie on 12/4/14.
 */
public interface IBaseService {

    public void onPreProcessListener();

    public void onErrorListener(Exception exception);

    public void onResponseListener(ArrayList<Issue> issues);
}
