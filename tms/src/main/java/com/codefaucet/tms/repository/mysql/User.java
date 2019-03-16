package com.codefaucet.tms.repository.mysql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.codefaucet.tms.model.UserRole;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(columnDefinition = "tinyint(1) default 1")
	@NotNull
	private boolean active;

	@Column
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Column(name = "username")
	@NotNull
	@Length(max = 32)
	private String username;

	@Column
	@NotNull
	@Length(max = 64)
	private String password;

	@Column(name = "last_name")
	@NotNull
	@Length(max = 32)
	private String lastName;

	@Column(name = "first_name")
	@NotNull
	@Length(max = 32)
	private String firstName;

	@Column(name = "middle_name")
	@Length(max = 32)
	private String middleName;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Session> sessions;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "encoder")
	private List<Panelist> encodedPanelists;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "encoder")
	private List<Thesis> theses;

	public User(long id, boolean active, UserRole role, String username, String password, String lastName,
			String firstName, String middleName) {
		this.id = id;
		this.active = active;
		this.role = role;
		this.username = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		sessions = new ArrayList<Session>();
		encodedPanelists = new ArrayList<Panelist>();
		theses = new ArrayList<Thesis>();
	}

	public User(UserRole role, String username, String password, String lastName, String firstName, String middleName) {
		this(0L, true, role, username, password, lastName, firstName, middleName);
	}

	public User() {
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

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Panelist> getEncodedPanelists() {
		return encodedPanelists;
	}

	public void setEncodedPanelists(List<Panelist> encodedPanelists) {
		this.encodedPanelists = encodedPanelists;
	}

	public List<Thesis> getTheses() {
		return theses;
	}

	public void setTheses(List<Thesis> theses) {
		this.theses = theses;
	}

}
