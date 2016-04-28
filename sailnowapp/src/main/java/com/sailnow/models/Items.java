package com.sailnow.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "items", catalog = "sailnow", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name"),})
public class Items implements java.io.Serializable {

	private String name;
	private String description;
	private String duration;
	private double price;
	private Set<SaleItem> saleItems = new HashSet<SaleItem>(0);
	
	public Items() {

	}
	
	public Items(String name, String description, String duration, double price, Set<SaleItem> saleItems) {
		super();
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.saleItems = saleItems;
	}

	@Id
	@Column(name = "name", unique = true, nullable = false)
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.items", cascade=CascadeType.ALL)
	public Set<SaleItem> getSaleItems() {
		return saleItems;
	}
	/**
	 * @param saleItems the saleItems to set
	 */
	public void setSaleItems(Set<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}

}
