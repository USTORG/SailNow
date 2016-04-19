package com.sailnow.models;

import java.util.Set;

public class UserModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5646758005694344294L;
	private String email;
	private Set<PackageModel>packages;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the packages
	 */
	public Set<PackageModel> getPackages() {
		return packages;
	}
	/**
	 * @param packages the packages to set
	 */
	public void setPackages(Set<PackageModel> packages) {
		this.packages = packages;
	}
	
	
}
