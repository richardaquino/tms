package com.codefaucet.tms.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "social_tags", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class SocialTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Length(max = 64)
	@NotNull
	private String name;

	@Column
	private double importance;

	public SocialTag(long id, String name, double importance) {
		this.id = id;
		this.name = name;
		this.importance = importance;
	}

	public SocialTag(String name, double importance) {
		this(0L, name, importance);
	}

	public SocialTag() {
		this("", 0);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getImportance() {
		return importance;
	}

	public void setImportance(double importance) {
		this.importance = importance;
	}

}
