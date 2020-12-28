	package com.ac.metadatainfo.model;

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
	private String docId;

	@Column(name = "doctype")
	@Getter
	@Setter
	private String docType;

	@Column(name = "docsize")
	@Getter
	@Setter
	private String docSize;

	public static final MetadataInfo EMPTY_METADATA = new MetadataInfo();

	public MetadataInfo() {
	}
	
	public boolean hasId() {
		return this.docId.isEmpty();
	}

	
}
