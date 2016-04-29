package com.sailnow.models;

import java.util.Date;

import org.hibernate.Session;
import com.sailnow.utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		System.out.println("Hibernate Test...");
		
		User user = createUser("user1");
		
		for(int i = 0; i < 4;i++)
		{
			createItemForUser(user,i);
		}

		getListItemsForUser(user.getEmail());
		
		removeItemsForUser("Package2");
		
		getListItemsForUser(user.getEmail());
	}

	private static void removeItemsForUser(String string) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Items item = (Items) session.get(Items.class, string);
		
		session.delete(item);
		
		session.getTransaction().commit();
		
	}

	private static void getListItemsForUser(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = (User) session.get(User.class, email);
		
		session.getTransaction().commit();
		System.out.println("User "+user.getEmail()+" has "+user.getSaleItems().size()+" Items:");
		for(SaleItem items : user.getSaleItems())
		{
			System.out.println("Item name: "+items.getItems().getName());
		}

		
	}

	private static void createItemForUser(User user, int i) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Items item = new Items();
		item.setName("Package"+i);
		item.setDescription("description"+i);
		item.setDuration(i+" weeks");
		item.setPrice(i);
		
		SaleItem sale = new SaleItem();
		sale.setItems(item);
		sale.setUser(user);
		
		item.getSaleItems().add(sale);
		
		session.save(item);
		
		session.getTransaction().commit();
		
	}

	private static User createUser(String string) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		User user = new User();
		user.setEmail("yasinj6@gmail.com");
		user.setGiven_name("Yasin");
		user.setFamily_name("Mohamed");
		
		session.save(user);
		
		session.getTransaction().commit();
		
		System.out.println("User "+user.getEmail()+" Created...");
		return user;
	}

}
