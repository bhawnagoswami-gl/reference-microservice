package com.gl.documentdata.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.gl.documentdata.dao.DocumentDataDAO;
import com.gl.documentdata.model.DocumentData;

@Service
@XRayEnabled
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
		DocumentData data = documentDataDAO.save(documentData);
		logger.info("Document added/updated - {}", data);
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
