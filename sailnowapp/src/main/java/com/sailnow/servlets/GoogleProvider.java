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

	@Override
	public String getUserInfoUrl() {
		return "https://www.googleapis.com/oauth2/v1/userinfo";
	}

}
