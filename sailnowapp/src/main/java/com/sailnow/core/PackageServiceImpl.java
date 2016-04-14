package com.sailnow.core;
import org.hibernate.Session;

import com.sailnow.interfaces.PackageService;
import com.sailnow.models.PackageModel;
import com.sailnow.utils.HibernateUtil;

public class PackageServiceImpl implements PackageService {

	public PackageService createPackage(PackageModel pack) {
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			
			if(pack == null)
			{
				throw new NullPointerException();
			}
			session.beginTransaction();
			session.save(pack);
			
		}finally {
			session.close();
		}
		return getPackage(pack.getName());
	}

	public PackageService getPackage(String name) {
		
		Session session = null;
		PackageService pack = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			pack = (PackageService) session.get(PackageModel.class, name);
		}finally {
			session.close();
		}
		
		return pack;
	}

}
