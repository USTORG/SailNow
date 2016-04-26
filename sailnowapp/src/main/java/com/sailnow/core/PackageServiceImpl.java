package com.sailnow.core;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.sailnow.interfaces.PackageService;
import com.sailnow.interfaces.UserService;
import com.sailnow.models.PackageModel;
import com.sailnow.models.UserModel;
import com.sailnow.utils.HibernateUtil;

public class PackageServiceImpl implements PackageService {

	
	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.PackageService#createPackage(com.sailnow.models.PackageModel)
	 */
	public void createPackage(PackageModel pack) {
		
		ManagerFactory mgrFactory = new ManagerFactory();
		UserService userservice = mgrFactory.getUserService();
		UserModel user = userservice.findUser(pack.getSeller().getEmail());
		
		if(user == null)
			System.out.println("Error: User doesn't exist");
		
		Set<PackageModel> packages = new HashSet<PackageModel>();
		packages.add(pack);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		session.save(pack);
		session.getTransaction().commit();
		
	}

	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.PackageService#createPackages(java.lang.String, java.util.Set)
	 */
	public void createPackages(String userid, Set<PackageModel> packages) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.PackageService#removePackage(java.lang.String)
	 */
	public void removePackage(String pkName) {
		
		PackageModel pkmodel = findPackage(pkName);
		
		if(pkmodel == null)
			System.out.println("Error: Package doesn't exist");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		session.delete(pkmodel);
		session.getTransaction().commit();
	}

	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.PackageService#findPackage(java.lang.String)
	 */
	public PackageModel findPackage(String pkName) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		PackageModel pkmodel = (PackageModel) session.get(PackageModel.class, pkName);
		session.getTransaction().commit();
		return pkmodel;
	}

	/* (non-Javadoc)
	 * @see com.sailnow.interfaces.PackageService#listAllPackages()
	 */
	public List<PackageModel> listAllPackages() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		List<PackageModel> pklist = session.createQuery("from PackageModel").list();
		session.getTransaction().commit();
		return pklist;
	}
	
	public List<PackageModel> getAllPackagesForUser(UserModel user)
	{
		List<PackageModel> list = new ArrayList<PackageModel>();
		
		for(PackageModel pack : user.getSellpackages())
		{
			PackageModel foundPack = findPackage(pack.getName());
			list.add(foundPack);
		}
		
		return list;
	}


}
