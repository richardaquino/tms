package com.codefaucet.tms.model;

public class Panelist {

	private long id;
	private boolean active;
	private String title;
	private String lastName;
	private String firstName;
	private String middleName;
	private String suffix;

	public Panelist(long id, boolean active, String title, String lastName, String firstName, String middleName,
			String suffix) {
		this.id = id;
		this.active = active;
		this.title = title;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffix = suffix;
	}

	public Panelist(String title, String lastName, String firstName, String middleName, String suffix) {
		this(0L, true, "", "", "", "", "");
	}

	public Panelist() {
		this("", "", "", "", "");
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
