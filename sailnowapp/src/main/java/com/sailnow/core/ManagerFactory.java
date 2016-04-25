package com.sailnow.core;

import java.io.IOException;

import com.sailnow.interfaces.OAuthTokenDao;
import com.sailnow.interfaces.PackageService;
import com.sailnow.interfaces.UserService;
import com.sailnow.oauth.OAuthProperties;
import com.sailnow.oauth.OAuthRequest;

public class ManagerFactory {


	public static PackageService getPackageService()
	{
		return new PackageServiceImpl();
	}
	
	public static UserService getUserService()
	{
		return new UserServiceImpl();
	}
	
	public static OAuthRequest getOuathRequest() throws IOException
	{
		OAuthProperties prop = new OAuthProperties();
		
		return new OAuthRequest();
	}

	public static OAuthTokenDao getOAuthTokenDao() {
		return new OAuthTokenDaoImpl();
	}
}
