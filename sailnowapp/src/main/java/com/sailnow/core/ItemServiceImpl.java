package com.sailnow.core;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.sailnow.interfaces.ItemService;
import com.sailnow.models.ItemDetails;
import com.sailnow.models.SaleHistory;
import com.sailnow.models.SaleItem;
import com.sailnow.models.User;
import com.sailnow.utils.HibernateUtil;

public class ItemServiceImpl implements ItemService {

	public void createSaleItem(User user, String itemid, ItemDetails details) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(details);
		
		SaleItem sale = new SaleItem();
		sale.setItemid(itemid);
		sale.setItem_details(details);
		sale.setUser(user);
		
		session.save(sale);
		
		session.getTransaction().commit();
	}

	public void removeSaleItem(String itemid) {
		// TODO Auto-generated method stub

	}

	public SaleItem updateSaleItem(SaleItem item) {
		// TODO Auto-generated method stub
		return null;
	}

	public SaleItem findSaleItem(String itemid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		SaleItem sale = (SaleItem) session.get(SaleItem.class, itemid);
		
		session.getTransaction().commit();
		
		return sale;
	}
	
	public void purchaseSaleItem(User user, String itemid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		SaleItem sale = (SaleItem) session.get(SaleItem.class, itemid);
		
		SaleHistory history = new SaleHistory();
		history.setItemid(sale.getItemid());
		history.setItem_details(new ItemDetails(sale.getItem_details().getDescription(),
				sale.getItem_details().getDuraion(),sale.getItem_details().getPrice()));
		history.setUser(user);
		
		session.delete(sale);
		session.save(history);
		
		session.getTransaction().commit();
	}

	public List<SaleItem> getUserSaleItemList(String email) {
		
		User user = ManagerFactory.getUserService().findUser(email);
		
		if(user == null)
		{
			
		}
		
		List<SaleItem> items = new ArrayList<SaleItem>();
		
		items.addAll(user.getSaleItem());
		
		return items;
	}

	public List<SaleHistory> getUserSaleHistory(String email) {
		
		User user = ManagerFactory.getUserService().findUser(email);
		
		if(user == null)
		{
			
		}
		
		List<SaleHistory> history = new ArrayList<SaleHistory>();
		history.addAll(user.getSaleHistory());
		
		return history;
	}

}
