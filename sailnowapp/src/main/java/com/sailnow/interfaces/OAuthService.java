package com.sailnow.interfaces;

import com.sailnow.models.User;
import com.sailnow.oauth.AccessTokenResponse;

public interface OAuthService {

	public String getAuthorizationRequestUrl(String redirectUri);
	
	public AccessTokenResponse getAccessToken(String code, String requestUrl);

	public User getUser(AccessTokenResponse accessToken);
}
