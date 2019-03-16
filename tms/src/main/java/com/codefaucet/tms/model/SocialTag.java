package com.codefaucet.tms.model;

public class SocialTag {

	private String name;
	private double importance;

	public SocialTag(String name, double importance) {
		this.name = name;
		this.importance = importance;
	}

	public SocialTag() {
		this("", 1);
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
