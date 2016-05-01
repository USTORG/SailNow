package com.sailnow.models;

import java.util.Scanner;

import org.hibernate.Session;

import com.sailnow.utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		
		boolean exit = false;
		
		while(!exit)
		{
			System.out.println("User Item Service ....");
			System.out.println("1) Create user");
		}

	}

	private static void printUserHistory(String string) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User user  = (User) session.get(User.class, string);
		
		
		session.getTransaction().commit();
		
		System.out.println("User"+user.getEmail());
		System.out.println("User "+user.getSaleItem().size()+" Items");
		System.out.println("User "+user.getSaleHistory().size()+" History");

	}

	private static void createSaleHistory(String userid, ItemDetails itemDetails) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User user = (User) session.get(User.class, userid);
		
		SaleHistory sale = new SaleHistory();
		sale.setItemid("Package1");
		sale.setItem_details(itemDetails);
		sale.setUser(user);
		
		session.save(sale);
		
		session.getTransaction().commit();
	}

	private static void removeSaleItem(String string) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		SaleItem  sale = (SaleItem) session.get(SaleItem.class, string);
		session.delete(sale);		
		session.getTransaction().commit();
		
	}

	private static void printUserSaleItem(String string) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User user  = (User) session.get(User.class, string);
		
		
		session.getTransaction().commit();
		
		System.out.println("User "+user.getEmail());
		System.out.println("User has "+user.getSaleItem().size()+" Items");
		
		
	}

	private static void createSaleItem(String userid, ItemDetails itemDetails) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		User user = (User) session.get(User.class, userid);
		
		SaleItem sale = new SaleItem();
		sale.setItemid("Package1");
		sale.setItem_details(itemDetails);
		sale.setUser(user);
		
		session.save(sale);
		
		session.getTransaction().commit();
		
	}

	private static void creatUser(String string) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = new User("user1","Yasin","Jama");
		
		session.save(user);
		
		session.getTransaction().commit();
		
	}


}
