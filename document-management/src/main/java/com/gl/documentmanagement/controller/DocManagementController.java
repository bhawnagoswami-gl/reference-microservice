package com.gl.documentmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.documentmanagement.model.Document;
import com.gl.documentmanagement.model.DocumentData;
import com.gl.documentmanagement.model.MetadataInfo;
import com.gl.documentmanagement.service.DocManagementService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/docs")
public class DocManagementController {

	private static final Logger logger = LoggerFactory.getLogger(DocManagementController.class);
	@Autowired
	private DocManagementService docManagementService;

	@RequestMapping("/{docName}")
	@HystrixCommand(fallbackMethod = "fallbackCatalog", commandKey = "doSomethingKey")
	public Document getdocument(@PathVariable("docName") String docName) {
		logger.trace("docname for searchc" + docName);
		DocumentData documentData = null;
		MetadataInfo metadataInfo = null;
		Document document = null;
		try {
			documentData = docManagementService.getDocInfo(docName);
			metadataInfo = docManagementService.getMetaInfo(documentData.getDocId());
			document = new Document(documentData.getDocId(), documentData.getDocName(), documentData.getDocLocation(), metadataInfo.getDocType(), metadataInfo.getDocSize());
		} catch (Exception e) {
			logger.error("error while getting document " + e);
		}
		return document;
	}

	public Document fallbackCatalog(@PathVariable("docName") String docName) {

		logger.info("calling fallback for document " +docName);
		Document document = null;
		try {
			document = new Document(null, "", "", "No Document", "0");
		} catch (Exception e) {
			logger.error("error while fallback " + e);
		}
		return document;}
}
