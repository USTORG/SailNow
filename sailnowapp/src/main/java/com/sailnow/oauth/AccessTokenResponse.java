package com.sailnow.oauth;

import com.sailnow.models.UserModel;

public class AccessTokenResponse {

	private String email;
	private String accessToken;
	private Long expiresIn;
	private String refreshToken;
	private UserModel users;
	
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
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the expiresIn
	 */
	public Long getExpiresIn() {
		return expiresIn;
	}
	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	/**
	 * @return the users
	 */
	public UserModel getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(UserModel users) {
		this.users = users;
	}
	
}
