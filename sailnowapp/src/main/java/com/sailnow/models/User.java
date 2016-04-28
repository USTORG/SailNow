package com.sailnow.models;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user", catalog = "sailnow")
public class User implements java.io.Serializable{
	
	private String email;
	private String given_name;
	private String family_name;
	private Set<SaleItem> saleItems = new HashSet<SaleItem>(0);

	public User() {
		
	}
	
	public User(String email, String given_name, String family_name, Set<SaleItem> saleItems) {
		this.email = email;
		this.given_name = given_name;
		this.family_name = family_name;
		this.saleItems = saleItems;
	}

	@Id
	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "given_name", nullable = true, length = 10)
	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	
	@Column(name = "family_name", nullable = true, length = 10)
	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user")
	public Set<SaleItem> getSaleItems() {
		return saleItems;
	}

	public void setSaleItems(Set<SaleItem> saleItems) {
		this.saleItems = saleItems;
	}

}
