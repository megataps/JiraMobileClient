package com.pyco.android.jiramobileclient.entities;

import java.net.URI;

/**
 * Created by jackie on 12/2/14.
 */
public class BasicIssue {

    private final URI self;
    private final String key;
    private final Long id;

    public BasicIssue(URI self, String key, Long id) {
        this.self = self;
        this.key = key;
        this.id = id;
    }

    /**
     * @return URI of this issue
     */
    public URI getSelf() {
        return self;
    }

    /**
     * @return issue key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return issue id
     */
    public Long getId() {
        return id;
    }

}
