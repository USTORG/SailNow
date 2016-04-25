package com.sailnow.oauth;

import com.sailnow.interfaces.OAuthService;

public abstract class Provider {

	public abstract String getAccessTokenEndpoint();

	public abstract String getAuthorizationEndpoint();
	
	public abstract String getUserInfoUrl();
	
	public String getAccessTokenRequestType() {
		return OAuth.HttpMethod.POST;
	}
	
	public String getApiRequestType() {
		return OAuth.HttpMethod.GET;
	}
	public OAuthService createService(OAuthProperties prop) {
		
		return new OAuthServiceImpl(this,prop);
	}

	 

	
}
