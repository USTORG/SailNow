package com.sailnow.core;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sailnow.interfaces.OAuthTokenDao;
import com.sailnow.models.UserModel;
import com.sailnow.oauth.AccessTokenResponse;
import com.sailnow.utils.HibernateUtil;

public class OAuthTokenDaoImpl implements OAuthTokenDao {

	public void saveKeys(AccessTokenResponse accessToken, String email) {
		
		UserModel user = ManagerFactory.getUserService().findUser(email);
//		if(user == null)
//		{
//			user = ManagerFactory.getUserService().createUser(email);
//		}
		accessToken.setUsers(user);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		session.save(accessToken);
		session.getTransaction().commit();
	}

}
