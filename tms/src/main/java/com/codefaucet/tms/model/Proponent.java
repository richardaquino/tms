package com.codefaucet.tms.model;

public class Proponent {

	private long id;
	private String title;
	private String lastName;
	private String firstName;
	private String middleName;
	private String suffix;

	public Proponent(long id, String title, String lastName, String firstName, String middleName, String suffix) {
		this.id = id;
		this.title = title;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffix = suffix;
	}

	public Proponent(String title, String lastName, String firstName, String middleName, String suffix) {
		this(0L, title, lastName, firstName, middleName, suffix);
	}

	public Proponent() {
		this("", "", "", "", "");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
