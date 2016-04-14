package com.sailnow.interfaces;


import com.sailnow.models.PackageModel;

public interface PackageService {

	public PackageService createPackage(PackageModel pack);

	public PackageService getPackage(String name);

	
}
