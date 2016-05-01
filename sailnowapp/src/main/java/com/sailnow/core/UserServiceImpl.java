package com.sailnow.core;

import org.hibernate.Session;

import com.sailnow.interfaces.UserService;
import com.sailnow.models.User;
import com.sailnow.utils.HibernateUtil;

public class UserServiceImpl implements UserService {

	
	public void createUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(user);
		
		session.getTransaction().commit();
	}

	public void removeUser(String userid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class, userid);
		session.delete(user);

		session.getTransaction().commit();
	}

	public void updateUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.update(user);
		
		session.getTransaction().commit();

	}

	public User findUser(String userid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = (User) session.get(User.class, userid);
		
		session.getTransaction().commit();
		
		return user;
	}

}
