package com.codefaucet.tms.model.dto;

public class TopicDTO {

	private String name;
	private double score;

	public TopicDTO(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public TopicDTO() {
		this("", 1);
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
