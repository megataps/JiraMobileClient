package com.pyco.android.jiramobileclient.services;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jackie on 12/8/14.
 */
public interface IIssueFilter {

    public JSONObject getJsonQuery() throws JSONException;

}
