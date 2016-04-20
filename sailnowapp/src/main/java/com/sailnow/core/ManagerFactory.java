package com.sailnow.core;

import com.sailnow.interfaces.PackageService;
import com.sailnow.interfaces.UserService;

public class ManagerFactory {


	public PackageService getPackageService()
	{
		return new PackageServiceImpl();
	}
	
	public UserService getUserService()
	{
		return new UserServiceImpl();
	}
}
