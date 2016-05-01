package com.sailnow.oauth;

public class OauthService {

	private String apiKey;
	private String apiSecret;
	private String callback;
	private String scope;
	private String oauthEndpoint;
	private String authorizationUrl;
	
	public OauthService(String oauthEndpoint) {
		this.oauthEndpoint = oauthEndpoint;
	}
	/**
	 * @param apiKey the apiKey to set
	 */
	public void apiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	/**
	 * @param apiSecret the apiSecret to set
	 */
	public void apiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
	/**
	 * @param callback the callback to set
	 */
	public void callback(String callback) {
		this.callback = callback;
	}
	/**
	 * @param scope the scope to set
	 */
	public void scope(String scope) {
		this.scope = scope;
	}
	
	public String getAuthorizationUrl()
	{
		return authorizationUrl;
	}
	
	public OauthService build()
	{
		 StringBuilder oauthUrl = new StringBuilder().append(oauthEndpoint)
				   .append("?client_id=").append(apiKey) // the client id from the api console registration
				   .append("&response_type=code")
				   .append("&scope=").append(scope) // scope is the api permissions we are requesting
				   .append("&redirect_uri=").append(callback);// the servlet that google redirects to after authorization
//				   .append("&state=this_can_be_anything_to_help_correlate_the_response%3Dlike_session_id")
//				   .append("&access_type=offline") // here we are asking to access to user's data while they are not signed in
//				   .append("&approval_prompt=force");
		this.authorizationUrl = oauthUrl.toString();
		 return this;
	}
	
}
