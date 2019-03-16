package com.codefaucet.tms.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Thesis {

	private long id;
	private boolean active;
	private String bookNumber;
	private String title;
	private String thesisAbstract;
	private LocalDate submissionDate;

	private List<Proponent> proponents;
	private List<Panelist> panelists;
	private List<FileAttachment> attachments;

	private List<SocialTag> socialTags;
	private List<Topic> topics;

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

}
