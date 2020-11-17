package com.gl.documentdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DOCUMENTDATA")
public class DocumentData {

	@Column(name = "docid")
	@Getter
	@Setter
	@Id
	private String docId;
	
	@Column(name = "docname")
	@Getter
	@Setter
	private String docName;
	
	@Column(name = "doclocation")
	@Getter
	@Setter
	private String docLocation;

	public DocumentData() {
	}

	public DocumentData(String docName, String docLocation) {
		this.docName = docName;
		this.docLocation = docLocation;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocLocation() {
		return docLocation;
	}

	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}

	public String getDocId() {
		return docId;
	}
	
	public void setDocId(String docId) {
		 this.docId =docId;
	}

}
