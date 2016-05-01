package com.sailnow.models;

import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	private String email;
	private String given_name;
	private String family_name;
	private Set<SaleItem> saleItem = new HashSet<SaleItem>();
	private Set<SaleHistory> saleHistory = new HashSet<SaleHistory>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userid, String given_name, String family_name) {
		super();
		this.email = userid;
		this.given_name = given_name;
		this.family_name = family_name;
	}

	@Id
	@Column(name="USER_ID")
	public String getEmail() {
		return email;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the given_name
	 */
	public String getGiven_name() {
		return given_name;
	}

	/**
	 * @param given_name the given_name to set
	 */
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	/**
	 * @return the family_name
	 */
	public String getFamily_name() {
		return family_name;
	}

	/**
	 * @param family_name the family_name to set
	 */
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	@OneToMany(mappedBy="user")
	public Set<SaleItem> getSaleItem() {
		return saleItem;
	}

	/**
	 * @param saleItem the saleItem to set
	 */
	public void setSaleItem(Set<SaleItem> saleItem) {
		this.saleItem = saleItem;
	}

	@OneToMany(mappedBy="user")
	public Set<SaleHistory> getSaleHistory() {
		return saleHistory;
	}

	/**
	 * @param saleHistory the saleHistory to set
	 */
	public void setSaleHistory(Set<SaleHistory> saleHistory) {
		this.saleHistory = saleHistory;
	}
	
	
}
