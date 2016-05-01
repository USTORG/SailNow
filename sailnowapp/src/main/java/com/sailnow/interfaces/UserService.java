package com.sailnow.interfaces;

import com.sailnow.models.User;

public interface UserService {

	public void createUser(User user);
	
	public void removeUser(String userid);
	
	public void updateUser(User user);
	
	public User findUser(String userid);
}
