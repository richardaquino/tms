package com.codefaucet.tms.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThesisDTO {

	private long id;
	private boolean active;
	private String bookNumber;
	private String title;
	private String thesisAbstract;
	private LocalDate submissionDate;

	private List<ProponentDTO> proponents;
	private List<PanelistDTO> panelists;
	private List<FileAttachmentDTO> attachments;

	private List<SocialTagDTO> socialTags;
	private List<TopicDTO> topics;

	public ThesisDTO(long id, boolean active, String bookNumber, String title, String thesisAbstract,
			LocalDate submissionDate) {
		this.id = id;
		this.active = active;
		this.bookNumber = bookNumber;
		this.title = title;
		this.thesisAbstract = thesisAbstract;
		this.submissionDate = submissionDate;

		proponents = new ArrayList<ProponentDTO>();
		panelists = new ArrayList<PanelistDTO>();
		attachments = new ArrayList<FileAttachmentDTO>();

		socialTags = new ArrayList<SocialTagDTO>();
		topics = new ArrayList<TopicDTO>();
	}

	public ThesisDTO(String bookNumber, String title, String thesisAbstract, LocalDate submissionDate) {
		this(0L, true, bookNumber, title, thesisAbstract, submissionDate);
	}

	public ThesisDTO() {
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

	public List<ProponentDTO> getProponents() {
		return proponents;
	}

	public void setProponents(List<ProponentDTO> proponents) {
		this.proponents = proponents;
	}

	public List<PanelistDTO> getPanelists() {
		return panelists;
	}

	public void setPanelists(List<PanelistDTO> panelists) {
		this.panelists = panelists;
	}

	public List<FileAttachmentDTO> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<FileAttachmentDTO> attachments) {
		this.attachments = attachments;
	}

	public List<SocialTagDTO> getSocialTags() {
		return socialTags;
	}

	public void setSocialTags(List<SocialTagDTO> socialTags) {
		this.socialTags = socialTags;
	}

	public List<TopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<TopicDTO> topics) {
		this.topics = topics;
	}

}
