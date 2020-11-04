package com.gl.documentdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.documentdata.dao.DocumentDataDAO;
import com.gl.documentdata.model.DocumentData;

@Service
public class DocumentDataService {

	 @Autowired
	 DocumentDataDAO documentDataDAO;

	  public DocumentData getDocument(String docName) {
		  DocumentData document = documentDataDAO.getDocument(docName);
	    return document;
	  }

	  public List<DocumentData> getAllDocuments() {
	    List<DocumentData> list = documentDataDAO.getAllDocs();
	    return list;
	  }

	  public void addDocument(DocumentData documentData) {
		  documentDataDAO.addDocument(documentData);
	  }

	  public void updateDocument(DocumentData documentData) {
		  documentDataDAO.updateDocument(documentData);
	  }

	  public void deleteDocument(String docName) {
		  documentDataDAO.deleteDocument(docName);
	  }

}
