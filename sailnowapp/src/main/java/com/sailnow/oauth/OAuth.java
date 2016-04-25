package com.sailnow.oauth;

public final class OAuth {

	public static final class HttpMethod {
		public static final String POST = "POST";
		public static final String GET = "GET";
		public static final String DELETE = "DELETE";
		public static final String PUT = "PUT";
	}
	
	public static final class HeaderType {
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
		public static final String AUTHORIZATION = "Authorization";
	}
	
	public static final class ContentType {
		public static final String URL_ENCODED = "application/x-www-form-urlencoded";
		public static final String JSON = "application/json";
	}
	
	public static final String OAUTH_RESPONSE_TYPE = "response_type";
	public static final String OAUTH_CLIENT_ID = "client_id";
	public static final String OAUTH_CLIENT_SECRET = "client_secret";
	public static final String OAUTH_REDIRECT_URI = "redirect_uri";
	public static final String OAUTH_SCOPE = "scope";
	public static final String OAUTH_STATE = "state";
	public static final String OAUTH_GRANT_TYPE = "grant_type";
	public static final String OAUTH_AUTHORIZATION_CODE = "authorization_code";
	
	// Authorization response params
	public static final String OAUTH_CODE = "code";
	public static final String OAUTH_ACCESS_TOKEN = "access_token";
	public static final String OAUTH_EXPIRES_IN = "expires_in";
	public static final String OAUTH_REFRESH_TOKEN = "refresh_token";
}
