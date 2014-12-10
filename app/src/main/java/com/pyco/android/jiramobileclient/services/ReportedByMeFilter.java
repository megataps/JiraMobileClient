package com.pyco.android.jiramobileclient.services;


import com.pyco.android.jiramobileclient.utils.CLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackie on 12/8/14.
 */
public class ReportedByMeFilter extends BaseFilter implements IIssueFilter {

    private static String TAG = ReportedByMeFilter.class.getName();

    public JSONObject getJsonQuery() throws JSONException {

        Map<String, String> params = new HashMap<String, String>();
        params.put("jql", "reporter = currentUser() ORDER BY createdDate DESC");
        params.put("startAt", String.valueOf(mStartAt));
        params.put("maxResults", String.valueOf(mMaxResults));

        JSONObject jsonRequest = new JSONObject(params);

        JSONArray fieldsArr = new JSONArray();
        fieldsArr.put("summary");
        fieldsArr.put("status");
        fieldsArr.put("assignee");

        jsonRequest.put("fields", fieldsArr);
        CLog.e(TAG, "jsonRequest:" + jsonRequest.toString());

        return jsonRequest;
    }
}
