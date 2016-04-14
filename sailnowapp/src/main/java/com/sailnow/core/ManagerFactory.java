package com.sailnow.core;

import com.sailnow.interfaces.Package;

public class ManagerFactory {


	public Package getPackage()
	{
		return new PackageImpl();
	}
}
