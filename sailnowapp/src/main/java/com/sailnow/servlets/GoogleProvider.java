package com.sailnow.servlets;

import com.sailnow.oauth.Provider;

public class GoogleProvider extends Provider {

	@Override
	public String getAccessTokenEndpoint() {
		return "https://accounts.google.com/o/oauth2/token";
	}

	@Override
	public String getAuthorizationEndpoint() {
		return "https://accounts.google.com/o/oauth2/auth";
	}

}
