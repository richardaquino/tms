package com.codefaucet.tms.repository.mysql;

import java.time.LocalDate;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "theses", uniqueConstraints = @UniqueConstraint(columnNames = { "book_number", "title" }))
public class Thesis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(columnDefinition = "tinyint(1) not null default 1")
	private boolean active;

	@Column(name = "book_number")
	@Length(max = 16)
	@NotNull
	private String bookNumber;

	@Column
	@Length(max = 256)
	@NotNull
	private String title;

	@Column(columnDefinition = "text not null")
	@NotNull
	private String thesisAbstract;

	@Column
	@NotNull
	private LocalDate submissionDate;

	@OneToMany(mappedBy = "thesis", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Proponent> proponents;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "thesis_panelists",
		joinColumns = { @JoinColumn(name = "thesis_id") },
		inverseJoinColumns = { @JoinColumn(name = "panelist_id") }
	)
	private List<Panelist> panelists;
	
	@OneToMany(mappedBy = "thesis", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FileAttachment> attachments;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "thesis_social_tags",
		joinColumns = { @JoinColumn(name = "thesis_id") },
		inverseJoinColumns = { @JoinColumn(name = "social_tag_id") }
	)
	private List<SocialTag> socialTags;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "thesis_topics",
		joinColumns = { @JoinColumn(name = "thesis_id") },
		inverseJoinColumns = { @JoinColumn(name = "topic_id") }
	)
	private List<Topic> topics;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "encoder_id")
	@NotNull
	private User encoder;
	
	public Thesis(long id, boolean active, String bookNumber, String title, String thesisAbstract,
			LocalDate submissionDate) {
		this.id = id;
		this.active = active;
		this.bookNumber = bookNumber;
		this.title = title;
		this.thesisAbstract = thesisAbstract;
		this.submissionDate = submissionDate;

		proponents = new ArrayList<Proponent>();
		panelists = new ArrayList<Panelist>();
		attachments = new ArrayList<FileAttachment>();

		socialTags = new ArrayList<SocialTag>();
		topics = new ArrayList<Topic>();
	}

	public Thesis(String bookNumber, String title, String thesisAbstract, LocalDate submissionDate) {
		this(0L, true, bookNumber, title, thesisAbstract, submissionDate);
	}

	public Thesis() {
		this("", "", "", LocalDate.now());
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

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThesisAbstract() {
		return thesisAbstract;
	}

	public void setThesisAbstract(String thesisAbstract) {
		this.thesisAbstract = thesisAbstract;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	public List<Proponent> getProponents() {
		return proponents;
	}

	public void setProponents(List<Proponent> proponents) {
		this.proponents = proponents;
	}

	public List<Panelist> getPanelists() {
		return panelists;
	}

	public void setPanelists(List<Panelist> panelists) {
		this.panelists = panelists;
	}

	public List<FileAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<FileAttachment> attachments) {
		this.attachments = attachments;
	}

	public List<SocialTag> getSocialTags() {
		return socialTags;
	}

	public void setSocialTags(List<SocialTag> socialTags) {
		this.socialTags = socialTags;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public User getEncoder() {
		return encoder;
	}

	public void setEncoder(User encoder) {
		this.encoder = encoder;
	}

}
