package com.codefaucet.tms.repository.mysql;

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
@Table(name = "topics", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	@Length(max = 64)
	@NotNull
	private String name;

	@Column
	private double score;

	public Topic(long id, String name, double score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public Topic(String name, double score) {
		this(0L, name, score);
	}

	public Topic() {
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
