package com.sailnow.core;

import org.hibernate.Session;


import com.sailnow.interfaces.PackageService;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.UserModel;
import com.sailnow.utils.HibernateUtil;

public class UserServiceImpl implements UserService {
	
	
	public UserModel addUser(String email) {
		Session session = null;
		UserModel user = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			user = new UserModel();
			user.setEmail(email);
			session.save(user);
			session.getTransaction().commit();

		}finally{
			session.close();	
		}
		return getUser(email);
	}
	
	public UserModel getUser(String email) {
		
		Session session = null;
		UserModel user = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			user = (UserModel) session.get(UserModel.class, email);
			session.getTransaction().commit();
		}finally{
			session.close();	
		}
		return user;
	}

	public void deleteUser(String email) {
		
		Session session = null;
		
		try{
			
			UserModel user = getUser(email);
			
			if(user != null)
			{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.delete(user);
				session.getTransaction().commit();
			}			
			
		}finally {
			session.close();
		}
	}

	
	public void addPackage(PackageService pack) {
		// TODO Auto-generated method stub
		
	}

	public PackageService getPackages() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removePackage(String string) {
		// TODO Auto-generated method stub
		
	}

}
