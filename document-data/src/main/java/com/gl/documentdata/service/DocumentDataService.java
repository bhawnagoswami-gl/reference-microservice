package com.gl.documentdata.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.documentdata.dao.DocumentDataDAO;
import com.gl.documentdata.model.DocumentData;

@Service
public class DocumentDataService {

	 @Autowired
	 DocumentDataDAO documentDataDAO;

	  public DocumentData getDocument(String docName) {

	    return
				documentDataDAO.findById(docName)
						.orElse(new DocumentData());
	  }

	  public List<DocumentData> getAllDocuments() {
	    return documentDataDAO.findAll();
	  }

	  public void addOrUpdateDocument(DocumentData documentData) {
		  documentDataDAO.save(documentData);
	  }



	  public void deleteDocument(String docName) {
		  documentDataDAO.deleteById(docName);
	  }

}
