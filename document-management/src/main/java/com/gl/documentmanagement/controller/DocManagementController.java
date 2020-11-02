package com.gl.documentmanagement.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(DocManagementController.class);

	private DocManagementService docManagementService;

	@Autowired
	public DocManagementController(DocManagementService docManagementService) {
		this.docManagementService = docManagementService;	
	}

	@RequestMapping("/{docName}")
	@HystrixCommand(fallbackMethod = "fallbackCatalog", commandKey = "doSomethingKey")
	public Document getdocument(@PathVariable("docName") String docName) {
		System.out.println("docnmae for search "+ docName);
		DocumentData documentData = docManagementService.getDocInfo(docName);
		MetadataInfo metadataInfo = docManagementService.getMetaInfo(documentData.getDocId());
		return new Document(documentData.getDocId(), documentData.getDocName(), documentData.getDocLocation(), metadataInfo.getDocType(), metadataInfo.getDocSize()); 
	}

	public Document fallbackCatalog(@PathVariable("userId") String userId)
	{
		logger.info("calling fallback for document");
		return new Document(null, "", "", "No Document", "0"); 
	}
}
