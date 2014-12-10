package com.pyco.android.jiramobileclient.services;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.pyco.android.jiramobileclient.AppConfigs;
import com.pyco.android.jiramobileclient.BaseApplication;
import com.pyco.android.jiramobileclient.entities.Issue;
import com.pyco.android.jiramobileclient.jsonparsers.IssueJsonParser;
import com.pyco.android.jiramobileclient.utils.CLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackie on 12/2/14.
 */
public class JiraService extends BaseService {

    private static String TAG = JiraService.class.getName();

    private static JiraService instance;

    public static JiraService getInstance() {
        if (instance == null) {
            instance = new JiraService();
        }
        return instance;
    }

    public void loadIssue(final String username, final String password, BaseApplication baseApplication,IIssueFilter mIssueFilter) {
        CLog.d(TAG, "loadIssue");

        if (mBaseServiceListener != null) {
            mBaseServiceListener.onPreProcessListener();
        }

        JSONObject jsonRequest = null;
        try {
            jsonRequest = mIssueFilter.getJsonQuery();
        } catch (JSONException ex) {
            onError(ex);
        }

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.POST, AppConfigs.SEARCH_URL,
                jsonRequest, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                CLog.d(TAG, "onResponse:" + response.toString());

                try {
                    // TODO:
                    ArrayList<Issue> issues = IssueJsonParser.getInstance().getIssueList(response);


                    if (mBaseServiceListener != null) {
                        mBaseServiceListener.onResponseListener(issues);
                    }

                } catch(JSONException ex) {
                    onError(ex);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                CLog.d(TAG, "onErrorResponse");
                onError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                CLog.d(TAG, "getHeaders");
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", createBasicAuthHeader(username, password));

                return params;
            }
        };

        baseApplication.addToRequestQueue(jr);
    }
}
