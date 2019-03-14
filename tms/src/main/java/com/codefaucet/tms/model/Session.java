package com.codefaucet.tms.model;

import java.time.LocalDateTime;

public class Session {

	private long id;
	private String token;
	private LocalDateTime expiration;
	private long userId;

	public Session(long id, String token, LocalDateTime expiration, long userId) {
		this.id = id;
		this.token = token;
		this.expiration = expiration;
		this.userId = userId;
	}

	public Session(String token, LocalDateTime expiration) {
		this(0L, token, expiration, 0L);
	}

	public Session() {
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
