package com.sailnow.interfaces;


import java.util.List;
import java.util.Set;

import com.sailnow.models.PackageModel;
import com.sailnow.models.UserModel;

public interface PackageService {

	public void createPackage(PackageModel pack);

	public void createPackages(String userid,Set<PackageModel> packages);
	
	public void removePackage(String pkName);
	
	public PackageModel findPackage(String pkName);
	
	public List<PackageModel> listAllPackages();
	
	public List<PackageModel> getAllPackagesForUser(UserModel user);
	
}
