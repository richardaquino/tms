package com.codefaucet.tms.repository.mysql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "panelists")
public class Panelist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition = "tinyint(1) not null default 1")
	private boolean active;

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "panelists")
	private List<Thesis> theses;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "encoder_id")
	@NotNull
	private User encoder;

	public Panelist(long id, boolean active, String title, String lastName, String firstName, String middleName,
			String suffix) {
		this.id = id;
		this.active = active;
		this.title = title;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffix = suffix;

		theses = new ArrayList<Thesis>();
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

	public List<Thesis> getTheses() {
		return theses;
	}

	public void setTheses(List<Thesis> theses) {
		this.theses = theses;
	}

	public User getEncoder() {
		return encoder;
	}

	public void setEncoder(User encoder) {
		this.encoder = encoder;
	}

}
