package com.codefaucet.tms.model.dto;

import java.time.LocalDateTime;

public class SessionDTO {

	private long id;
	private String token;
	private LocalDateTime expiration;
	private long userId;

	public SessionDTO(long id, String token, LocalDateTime expiration, long userId) {
		this.id = id;
		this.token = token;
		this.expiration = expiration;
		this.userId = userId;
	}

	public SessionDTO(String token, LocalDateTime expiration) {
		this(0L, token, expiration, 0L);
	}

	public SessionDTO() {
		this("", LocalDateTime.now());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
