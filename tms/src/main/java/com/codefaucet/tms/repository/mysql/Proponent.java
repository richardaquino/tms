package com.codefaucet.tms.repository.mysql;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "proponents")
public class Proponent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Length(max = 32)
	private String title;

	@Column
	@Length(max = 64)
	@NotNull
	private String lastName;

	@Column
	@Length(max = 64)
	@NotNull
	private String firstName;

	@Column
	@Length(max = 64)
	private String middleName;

	@Column
	@Length(max = 32)
	private String suffix;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotNull
	private Thesis thesis;

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
