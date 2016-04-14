package com.sailnow.interfaces;

import com.sailnow.models.UserModel;

public interface UserService {

	public UserModel addUser(String email);

	public UserModel getUser(String email);

	public void deleteUser(String email);

	public void addPackage(PackageService pack);

	public PackageService getPackages();

	public void removePackage(String string);

}
