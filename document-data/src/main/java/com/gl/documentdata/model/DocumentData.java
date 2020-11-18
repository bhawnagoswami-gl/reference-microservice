package com.gl.documentdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DOCUMENTDATA")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DocumentData {

	@Column(name = "docid")
	@Id
	private String docId;
	
	@Column(name = "docname")
	private String docName;
	
	@Column(name = "doclocation")
	private String docLocation;


	public static final DocumentData EMPTY_DOCUMENT = new DocumentData();

	public boolean hasId() {
		return this.docId.isEmpty();
	}

}
