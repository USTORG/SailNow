package com.sailnow.oauth;

import com.sailnow.interfaces.OAuthService;

public abstract class Provider {

	public abstract String getAccessTokenEndpoint();

	public abstract String getAuthorizationEndpoint();

	public String getAccessTokenRequestType() {
		return OAuth.HttpMethod.POST;
	}
	
	public OAuthService createService(OAuthProperties prop) {
		
		return new OAuthServiceImpl(this,prop);
	}	
	
}
