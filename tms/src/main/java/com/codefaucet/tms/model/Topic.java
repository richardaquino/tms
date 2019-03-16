package com.codefaucet.tms.model;

public class Topic {

	private String name;
	private double score;

	public Topic(String name, double score) {
		this.name = name;
		this.score = score;
	}

	public Topic() {
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
