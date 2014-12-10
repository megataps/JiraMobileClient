package com.pyco.android.jiramobileclient.jsonparsers;


import com.pyco.android.jiramobileclient.entities.Issue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by jackie on 12/9/14.
 */
public class IssueJsonParser {

    private static IssueJsonParser issueJsonParser;
    public static IssueJsonParser getInstance() {
        if(issueJsonParser == null) {
            issueJsonParser = new IssueJsonParser();
        }

        return issueJsonParser;
    }

    public ArrayList<Issue> getIssueList(JSONObject response) throws JSONException {
        ArrayList<Issue> issues = new ArrayList<Issue>();

        if (response.has("issues")) {
            JSONArray issueArr = response.getJSONArray("issues");

            int length = issueArr.length();

            for(int i = 0; i < length; i++) {
                JSONObject obj = issueArr.getJSONObject(i);

                String summary = "";
                URI self =  URI.create(obj.getString("self"));
                String key = obj.getString("key");
                long  id = obj.getLong("id");
                String description = "";

                JSONObject fieldJSON = obj.getJSONObject("fields");
                summary = fieldJSON.getString("summary");

                JSONObject statusJSON = fieldJSON.getJSONObject("status");
                description = statusJSON.getString("description");

                Issue issue = new Issue(summary,
                        self,
                        key,
                        id,
                        description,
                        null,null, null);

                issues.add(issue);
            }
        }

        return issues;
    }
}
