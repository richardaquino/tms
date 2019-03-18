package com.codefaucet.tms.model.dto;

public class SocialTagDTO {

	private String name;
	private double importance;

	public SocialTagDTO(String name, double importance) {
		this.name = name;
		this.importance = importance;
	}

	public SocialTagDTO() {
		this("", 0);
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
