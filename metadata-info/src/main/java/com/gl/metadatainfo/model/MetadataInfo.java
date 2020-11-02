package com.gl.metadatainfo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Metadatainfo")
public class MetadataInfo {

	@Column(name = "docid")
	@Getter
	@Id
	@Setter
	private long docId;

	@Column(name = "doctype")
	@Getter
	@Setter
	private String docType;

	@Column(name = "docsize")
	@Getter
	@Setter
	private String docSize;

	public MetadataInfo() {
	}

	public MetadataInfo(long docId, String docType, String docSize) {
		this.docId = docId;
		this.docType = docType;
		this.docSize = docSize;
	}

	public long getDocId() {
		return docId;
	}

	public void setDocId(long docId) {
		this.docId = docId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocSize() {
		return docSize;
	}

	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

}
