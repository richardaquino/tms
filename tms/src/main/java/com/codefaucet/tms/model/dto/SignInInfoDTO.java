package com.codefaucet.tms.model.dto;

import com.codefaucet.tms.model.UserRole;

public class SignInInfoDTO {

	private String sessionToken;
	private long userId;
	private String username;
	private UserRole role;

	public SignInInfoDTO(String sessionToken, long userId, String username, UserRole role) {
		this.sessionToken = sessionToken;
		this.userId = userId;
		this.username = username;
		this.role = role;
	}

	public SignInInfoDTO() {
		this("", 0L, "", UserRole.UNKNOWN);
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
