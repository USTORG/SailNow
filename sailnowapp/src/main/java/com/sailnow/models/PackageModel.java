package com.sailnow.models;

public class PackageModel implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3976736972133655544L;
	private String name;
	private String description;
	private String duration;
	private double price;
	
	private UserModel seller;
	private UserModel buyer;
	
	
	
	public PackageModel() {

	}
	public PackageModel(String name, String description, String duration, double price, UserModel seller) {

		this.name = name;
		this.description = description;
		this.duration = duration;
		this.price = price;
		this.seller = seller;
	}
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
	/**
	 * @return the sellusers
	 */
	public UserModel getSeller() {
		return seller;
	}
	/**
	 * @param sellusers the sellusers to set
	 */
	public void setSeller(UserModel seller) {
		this.seller = seller;
	}
	/**
	 * @return the buyusers
	 */
	public UserModel getBuyer() {
		return buyer;
	}
	/**
	 * @param buyusers the buyusers to set
	 */
	public void setBuyer(UserModel buyer) {
		this.buyer = buyer;
	}


}
