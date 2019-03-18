package com.codefaucet.tms.model.dto;

import com.codefaucet.tms.model.UserRole;

public class UserDTO {

	private long id;
	private boolean active;
	private UserRole role;
	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private String middleName;

	public UserDTO(long id, boolean active, UserRole role, String username, String password, String lastName,
			String firstName, String middleName) {
		this.id = id;
		this.active = active;
		this.role = role;
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
	}

	public UserDTO(UserRole role, String username, String password, String lastName, String firstName, String middleName) {
		this(0L, true, role, username, password, lastName, firstName, middleName);
	}

	public UserDTO() {
		this(UserRole.UNKNOWN, "", "", "", "", "");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

}
