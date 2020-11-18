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
	@Id
	@Getter
	@Setter
	private String docId;
	
	@Column(name = "docname")
	@Getter
	@Setter
	private String docName;
	
	@Column(name = "doclocation")
	@Getter
	@Setter
	private String docLocation;


	public static final DocumentData EMPTY_DOCUMENT = new DocumentData();

	public boolean hasId() {
		return this.docId.isEmpty();
	}

}
