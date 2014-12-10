package com.pyco.android.jiramobileclient;

import android.content.Context;

import com.pyco.android.jiramobileclient.entities.UserEntity;


public class SessionManager {

	private UserEntity userLoggedIn;

	public boolean isLoggedIn() {
		if(userLoggedIn != null) {
			return true;
		}
		return false;
	}

	public void logout(final Context ctx) {
		userLoggedIn = null;

	}

	public void saveUserLoggedInInformation(UserEntity user) {
		userLoggedIn = user;
	}

    public UserEntity getLoggedInUser() {
        return userLoggedIn;
    }
}
