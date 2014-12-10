package com.pyco.android.jiramobileclient.entities;

/**
 * Created by jackie on 12/5/14.
 */
public class UserEntity {

    private String username;
    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
