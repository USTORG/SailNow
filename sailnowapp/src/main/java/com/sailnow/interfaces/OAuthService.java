package com.sailnow.interfaces;

import com.sailnow.oauth.AccessTokenResponse;

public interface OAuthService {

	public String getAuthorizationRequestUrl(String redirectUri);
	
	public AccessTokenResponse getAccessToken(String code, String requestUrl);
}
