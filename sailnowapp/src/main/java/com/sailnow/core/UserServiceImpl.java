package com.sailnow.core;

import org.hibernate.Session;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.UserModel;
import com.sailnow.utils.HibernateUtil;

public class UserServiceImpl implements UserService {

	
	
	
	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.UserService#createUser(java.lang.String)
	 */
	public UserModel createUser(String userid) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		UserModel user = new UserModel();
		user.setEmail(userid);
		
		session.save(user);
		session.getTransaction().commit();
		
		return findUser(userid);
	}

	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.UserService#removeUser(java.lang.String)
	 */
	public void removeUser(String userid) {
		
		UserModel user = findUser(userid);
		
		if( user == null)
			System.out.println("Error: User does not exist");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		session.delete(user);
		session.getTransaction().commit();
		
	}

	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.UserService#updateUser(com.sailnow.models.UserModel)
	 */
	public UserModel updateUser(UserModel user) {
		
		if(findUser(user.getEmail()) == null)
			System.out.println("Error:User does not exist!");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		session.update(user);
		session.getTransaction().commit();
		
		return findUser(user.getEmail());
	}


	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.UserService#findUser(java.lang.String)
	 */
	public UserModel findUser(String userid) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		UserModel user = (UserModel) session.get(UserModel.class, userid);
		session.getTransaction().commit();	
		return user;
	}
	
}
