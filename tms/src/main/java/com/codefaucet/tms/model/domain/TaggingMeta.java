package com.codefaucet.tms.model.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "tagging_meta")
public class TaggingMeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "submission_time")
	@NonNull
	private LocalDateTime submissionTime;

	@Column(name = "submitter_code")
	@Length(max = 128)
	@NotNull
	private String submitterCode;

	@Column
	@Length(max = 256)
	@NotNull
	private String signature;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taggingMeta")
	private Thesis thesis;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "encoder_id", nullable = false)
	private User encoder;

	public TaggingMeta(long id, LocalDateTime submissionTime, String submitterCode, String signature) {
		this.id = id;
		this.submissionTime = submissionTime;
		this.submitterCode = submitterCode;
		this.signature = signature;
	}

	public TaggingMeta(String submitterCode, String signature) {
		this(0L, LocalDateTime.now(), submitterCode, signature);
	}

	public TaggingMeta() {
		this("", "");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(LocalDateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getSubmitterCode() {
		return submitterCode;
	}

	public void setSubmitterCode(String submitterCode) {
		this.submitterCode = submitterCode;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Thesis getThesis() {
		return thesis;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}

	public User getEncoder() {
		return encoder;
	}

	public void setEncoder(User encoder) {
		this.encoder = encoder;
	}

}
