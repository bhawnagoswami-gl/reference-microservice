package com.ac.documentdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ac.documentdata.dao.DocumentDataDAO;
import com.ac.documentdata.model.DocumentData;

@Service
public class DocumentDataService {
	private static final Logger logger = LoggerFactory.getLogger(DocumentDataService.class);


	private DocumentDataDAO documentDataDAO;

	@Autowired
	public DocumentDataService(DocumentDataDAO documentDataDAO)
	{
		this.documentDataDAO = documentDataDAO;
	}

	public DocumentData findDocument(String docId) {
		return
				documentDataDAO
				.findById(docId)
				.orElse(DocumentData.EMPTY_DOCUMENT);
	}

	public List<DocumentData> getAllDocuments() {
		return
				documentDataDAO
				.findAll();
	}

	public void addDocument(DocumentData documentData) {
		//Subsegment subsegment = AWSXRay.beginSubsegment("Get Doc Data");
		DocumentData data = documentDataDAO.save(documentData);
		logger.info("Document added/updated - {}", data);
		//AWSXRay.endSubsegment();
	}

	public void updateDocument(DocumentData documentData) {
		if (documentData.hasId()) {
			addDocument(documentData);
		} else {
			throwDocumentNotExistException(documentData);
		}
	}

	private void throwDocumentNotExistException(DocumentData documentData){
		logger.warn("Document {} doesn't exist and so can't be updated");
		throw new RuntimeException("Document " + documentData + " doesn't exist and so can't be updated");
	}

	public void deleteDocument(String docName) {
		documentDataDAO.deleteById(docName);
	}

}
