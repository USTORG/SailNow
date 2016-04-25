package com.sailnow.oauth;

import java.io.InputStream;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.sailnow.interfaces.OAuthService;
import com.sailnow.models.UserModel;

public class OAuthServiceImpl implements OAuthService {
	
	private final Provider provider;
	private final OAuthProperties prop;
	
	public OAuthServiceImpl(Provider provider, OAuthProperties prop) {
		this.provider = provider;
		this.prop = prop;
	}

	public String getAuthorizationRequestUrl(String redirectUri) {
		StringBuilder oauthUrl = new StringBuilder().append(provider.getAuthorizationEndpoint())
				   .append("?"+OAuth.OAUTH_CLIENT_ID+"=").append(prop.getClientId()) // the client id from the api console registration
				   .append("&"+OAuth.OAUTH_RESPONSE_TYPE+"=").append(OAuth.OAUTH_CODE)
				   .append("&"+OAuth.OAUTH_SCOPE+"=").append(prop.getScopesAsString()) // scope is the api permissions we are requesting
				   .append("&"+OAuth.OAUTH_REDIRECT_URI+"=").append(redirectUri);// the servlet that google redirects to after authorization
		return oauthUrl.toString();
	}

	public AccessTokenResponse getAccessToken(String code, String requestUrl) {
		OAuthRequest request = new OAuthRequest();
		request.setRequest(provider.getAccessTokenRequestType());
		request.body(prop);
		request.addBodyNameValuePair(OAuth.OAUTH_REDIRECT_URI, requestUrl);
		request.addBodyNameValuePair(OAuth.OAUTH_GRANT_TYPE,OAuth.OAUTH_AUTHORIZATION_CODE);
		request.addBodyNameValuePair(OAuth.OAUTH_CODE, code);
		String  body = request.executePostMethod(provider.getAccessTokenEndpoint());
		AccessTokenResponse token = new Gson().fromJson(body, AccessTokenResponse.class);
		return token;
	}

	public UserModel getUser(AccessTokenResponse accessToken) {
		OAuthRequest request = new OAuthRequest();
		request.setRequest(provider.getApiRequestType());	
		String url = provider.getUserInfoUrl()+"?"+OAuth.OAUTH_ACCESS_TOKEN+"="+accessToken.getAccess_token();
		String body = request.executeGetMethod(url);
		UserModel user = new Gson().fromJson(body, UserModel.class);
		return user;
	}


}
