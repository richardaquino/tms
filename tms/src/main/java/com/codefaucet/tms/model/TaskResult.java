package com.codefaucet.tms.model;

import java.util.Map;

public class TaskResult<T> {

	private T content;
	private TaskStatus status;
	private String message;
	private Map<String, String> errors;

	public TaskResult() {
		status = TaskStatus.UNKNOWN;
	}

	public TaskResult<T> setSuccessful(T content) {
		this.status = TaskStatus.SUCCESSFUL;
		this.content = content;
		return this;
	}
	
	public TaskResult<T> setFailed(String message) {
		this.status = TaskStatus.FAILED;
		this.message = message;
		return this;
	}
	
	public TaskResult<T> setFailed() {
		return this.setFailed("");
	}
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
