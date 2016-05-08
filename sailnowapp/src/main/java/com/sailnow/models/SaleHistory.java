package com.sailnow.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SaleHistory")
public class SaleHistory {
	
	private String itemid;
	private String seller_fname;
	public String getSeller_fname() {
		return seller_fname;
	}
	public void setSeller_fname(String seller_fname) {
		this.seller_fname = seller_fname;
	}
	public String getSeller_lname() {
		return seller_lname;
	}
	public void setSeller_lname(String seller_lname) {
		this.seller_lname = seller_lname;
	}
	private String seller_lname;
	private User user;
	private ItemDetails item_details;
	
	@Id
	@Column(name="SALE_ITEM_ID")
	public String getItemid() {
		return itemid;
	}
	/**
	 * @param itemid the itemid to set
	 */
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	
    @ManyToOne
    @JoinColumn(name = "USER_ID") 
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEMDETAILS_ID")
	public ItemDetails getItem_details() {
		return item_details;
	}
	/**
	 * @param item_details the item_details to set
	 */
	public void setItem_details(ItemDetails item_details) {
		this.item_details = item_details;
	}
}
