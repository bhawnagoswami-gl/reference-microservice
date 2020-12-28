package com.ac.documentmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ac.documentmanagement.model.Document;
import com.ac.documentmanagement.model.DocumentData;
import com.ac.documentmanagement.model.MetadataInfo;
import com.ac.documentmanagement.service.DocManagementService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/docs")
public class DocManagementController {

	private static final Logger logger = LoggerFactory.getLogger(DocManagementController.class);
	private DocManagementService docManagementService;
	
	@Autowired
	public DocManagementController(DocManagementService docManagementService)
	{
		this.docManagementService = docManagementService;
	}
	
	@ApiOperation(value = "Get document data with a document id",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@RequestMapping("/{docId}")
	public Document getDocument(@PathVariable("docId") String docId) {
		logger.info("docname for searchc" + docId);
		DocumentData documentData = null;
		MetadataInfo metadataInfo = null;
		Document document = null;
		documentData = docManagementService.getDocInfo(docId);
		metadataInfo = docManagementService.getMetaInfo(docId);
		document = new Document(documentData.getDocId(), documentData.getDocName(), documentData.getDocLocation(), metadataInfo.getDocType(), metadataInfo.getDocSize());
		return document;
	}
	
	@ApiOperation(value = "Add document data with a document id",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping()
	public void addDocument(@RequestBody Document document){
	   logger.info("Adding document");
	   docManagementService.addDocument(document);
	}
	
}
