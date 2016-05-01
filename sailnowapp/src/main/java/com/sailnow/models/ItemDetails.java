package com.sailnow.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Item_details")
public class ItemDetails {

	private long id;
	private String description;
	private String duraion;
	private double price;
	private Set<SaleItem> saleItem = new HashSet<SaleItem>();
	
	
	public ItemDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ItemDetails(String description, String duraion, double price) {
		super();
		this.description = description;
		this.duraion = duraion;
		this.price = price;
	}


	@Id
	@GeneratedValue
	@Column(name="ITEMDETAILS_ID")
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the duraion
	 */
	public String getDuraion() {
		return duraion;
	}


	/**
	 * @param duraion the duraion to set
	 */
	public void setDuraion(String duraion) {
		this.duraion = duraion;
	}


	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}


	@OneToMany(mappedBy="item_details")
	public Set<SaleItem> getSaleItem() {
		return saleItem;
	}


	/**
	 * @param saleItem the saleItem to set
	 */
	public void setSaleItem(Set<SaleItem> saleItem) {
		this.saleItem = saleItem;
	}
	
}
