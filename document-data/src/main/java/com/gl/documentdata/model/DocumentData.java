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
	private String docId;
	
	@Column(name = "docName")
	@Getter
	@Setter
	@Id
	private String docName;
	
	@Column(name = "docLocation")
	@Getter
	@Setter
	private String docLocation;

	public DocumentData() {
	}

	public DocumentData(String docId, String docName, String docLocation) {
		this.docId = docId;
		this.docName = docName;
		this.docLocation = docLocation;
	}

}
