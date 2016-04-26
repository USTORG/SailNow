package com.sailnow.models;

import java.util.Set;

import com.sailnow.oauth.AccessTokenResponse;

public class UserModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5646758005694344294L;
	private String email;
	private String given_name;
	private String family_name;
	private Set<PackageModel>sellpackages;
	private Set<PackageModel>buypackages;
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
//	/**
//	 * @return the packages
//	 */
//	public Set<PackageModel> getPackages() {
//		return sellpackages;
//	}
//	/**
//	 * @param packages the packages to set
//	 */
//	public void setPackages(Set<PackageModel> packages) {
//		this.sellpackages = packages;
//	}
	/**
	 * @return the sellpackages
	 */
	public Set<PackageModel> getSellpackages() {
		return sellpackages;
	}
	/**
	 * @param sellpackages the sellpackages to set
	 */
	public void setSellpackages(Set<PackageModel> sellpackages) {
		this.sellpackages = sellpackages;
	}
	/**
	 * @return the buypackages
	 */
	public Set<PackageModel> getBuypackages() {
		return buypackages;
	}
	/**
	 * @param buypackages the buypackages to set
	 */
	public void setBuypackages(Set<PackageModel> buypackages) {
		this.buypackages = buypackages;
	}
	
	
}
