package com.sailnow.interfaces;


import java.util.List;
import java.util.Set;

import com.sailnow.models.PackageModel;

public interface PackageService {

	public void createPackage(PackageModel pack);

	public void createPackages(String userid,Set<PackageModel> packages);
	
	public void removePackage(String pkName);
	
	public PackageModel findPackage(String pkName);
	
	public List<PackageModel> listAllPackages();
	
}
