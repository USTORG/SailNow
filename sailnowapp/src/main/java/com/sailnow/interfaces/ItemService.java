package com.sailnow.interfaces;

import java.util.List;

import com.sailnow.models.ItemDetails;
import com.sailnow.models.SaleHistory;
import com.sailnow.models.SaleItem;
import com.sailnow.models.User;

public interface ItemService {

	public void createSaleItem(User user,String itemid,ItemDetails details);
	
	public void removeSaleItem(String itemid);
	
	public SaleItem updateSaleItem(SaleItem item);
	
	public SaleItem findSaleItem(String itemid);
	
	public void purchaseSaleItem(User user,String itemid);
	
	public List<SaleItem> getUserSaleItemList(String email);
	
	public List<SaleHistory> getUserSaleHistory(String email);
	
	public List<SaleItem> getAllItems();
}
