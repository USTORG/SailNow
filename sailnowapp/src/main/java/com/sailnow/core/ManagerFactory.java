package com.sailnow.core;

import java.io.IOException;

import com.sailnow.interfaces.ItemService;
import com.sailnow.interfaces.UserService;
import com.sailnow.oauth.OAuthProperties;
import com.sailnow.oauth.OAuthRequest;

public class ManagerFactory {


	public static ItemService getItemService()
	{
		return new ItemServiceImpl();
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
}
