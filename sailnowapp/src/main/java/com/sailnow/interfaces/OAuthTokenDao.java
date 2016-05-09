package com.sailnow.interfaces;

import com.sailnow.oauth.AccessTokenResponse;

public interface OAuthTokenDao {

	public void saveKeys(AccessTokenResponse accessToken,String email);
}
