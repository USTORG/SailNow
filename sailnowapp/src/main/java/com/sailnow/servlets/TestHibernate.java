package com.sailnow.servlets;



import com.sailnow.models.PackageModel;
import com.sailnow.models.UserModel;
import com.sailnow.utils.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;


public class TestHibernate {

	public static void main(String[] args) {
		

		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		UserModel user = new UserModel();
		user.setEmail("Yasinj6@gmail.com");
		
		PackageModel pkg = new PackageModel();
		pkg.setName("Package 1");
		pkg.setDescription("Package 1 Description");
		pkg.setDuration("3 Weeks");
		pkg.setPrice(700);
		pkg.setUser(user);
		
		Set<PackageModel> pack = new HashSet<PackageModel>();
		pack.add(pkg);
		
		user.setPackages(pack);
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
	}
}
