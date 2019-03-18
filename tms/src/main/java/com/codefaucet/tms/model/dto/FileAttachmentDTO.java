package com.codefaucet.tms.model.dto;

public class FileAttachmentDTO {

	private long id;
	private String originalFilename;
	private String serverFilename;

	public FileAttachmentDTO(long id, String originalFilename, String serverFilename) {
		this.id = id;
		this.originalFilename = originalFilename;
		this.serverFilename = serverFilename;
	}

	public FileAttachmentDTO(String originalFilename, String serverFilename) {
		this(0L, originalFilename, serverFilename);
	}

	public FileAttachmentDTO() {
		this("", "");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getServerFilename() {
		return serverFilename;
	}

	public void setServerFilename(String serverFilename) {
		this.serverFilename = serverFilename;
	}

}
