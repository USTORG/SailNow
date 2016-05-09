package com.sailnow.oauth;



public class ServiceBuilder {

//	public OauthService provider(OauthProviders provider)
//	{
//		OauthService service = null;
//		if(provider.getClass().equals(GoogleApi.class))
//		{
////			service = new OauthService(provider.oauthEndpoint());;
//		}
//		return service;
//	}

	public OauthService provider(Class cl) {
		
		OauthService service = null;
		
		if(cl == GoogleApi.class)
		{
			service = new OauthService(GoogleApi.oauthEndpoint());;
		}
		return service;
	}
}
