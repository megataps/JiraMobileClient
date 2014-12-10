package com.pyco.android.jiramobileclient.entities;

import org.joda.time.DateTime;

import java.net.URI;

/**
 * Created by jackie on 12/2/14.
 */
public class Issue extends BasicIssue {

    private final String summary;
    private final String description;
    private final DateTime creationDate;
    private final DateTime updateDate;
    private final DateTime dueDate;

    public Issue(String summary, URI self, String key, Long id, String description,
                 DateTime creationDate, DateTime updateDate, DateTime dueDate) {

        super(self, key, id);
        this.summary = summary;
        this.description = description;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.dueDate = dueDate;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public DateTime getDueDate() {
        return dueDate;
    }
}
