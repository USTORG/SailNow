package com.sailnow.oauth;

import com.sailnow.interfaces.OAuthService;

public class OAuthServiceBuilder {

	private String apiKey;
	private String apiSecret;
	private String callback;
	private Provider provider;
	private String scope;
	
	public OAuthServiceBuilder() {
		// TODO Auto-generated constructor stub
	}

	public OAuthServiceBuilder provider(Class<? extends Provider> providerClass) {
		this.provider = createProvider(providerClass);
		return this;
	}
	public OAuthServiceBuilder provider(Provider provider)
	{
		this.provider = provider;
		return this;
	}

	private Provider createProvider(Class<? extends Provider> providerClass) {
		Provider provider;
		try {
			provider = providerClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Error while creating the provider object", e);
		}
		return provider;
	}

	public OAuthServiceBuilder callback(String callback) {
		this.callback = callback;
		return this;
	}

	public OAuthServiceBuilder apiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}

	public OAuthServiceBuilder apiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
		return this;
	}

	public OAuthServiceBuilder scope(String scope) {
		this.scope = scope;
		return this;
	}
	
	public OAuthService build(OAuthProperties prop) {
		OAuthService oauthService = provider.createService(prop);
		
		return oauthService;
	}
}
