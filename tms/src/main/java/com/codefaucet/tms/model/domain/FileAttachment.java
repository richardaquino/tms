package com.codefaucet.tms.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "file_attachments", uniqueConstraints = @UniqueConstraint(columnNames = "server_filename"))
public class FileAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "original_filename")
	@Length(max = 256)
	@NotNull
	private String originalFilename;

	@Column(name = "server_filename")
	@Length(max = 256)
	@NotNull
	private String serverFilename;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "thesis_id")
	@NotNull
	private Thesis thesis;

	public FileAttachment(long id, String originalFilename, String serverFilename) {
		this.id = id;
		this.originalFilename = originalFilename;
		this.serverFilename = serverFilename;
	}

	public FileAttachment(String originalFilename, String serverFilename) {
		this(0L, originalFilename, serverFilename);
	}

	public FileAttachment() {
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

	public Thesis getThesis() {
		return thesis;
	}

	public void setThesis(Thesis thesis) {
		this.thesis = thesis;
	}

}
