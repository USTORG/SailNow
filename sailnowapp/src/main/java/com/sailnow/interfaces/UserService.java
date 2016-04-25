package com.sailnow.interfaces;

import com.sailnow.models.UserModel;

public interface UserService {
	
	public UserModel createUser(String userid);
	
	public void removeUser(String userid);
	
	public UserModel updateUser(UserModel user);
	
	public UserModel findUser(String userid);

	public void createUser(UserModel user);
	
}
