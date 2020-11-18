package com.gl.documentmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.documentmanagement.model.Document;
import com.gl.documentmanagement.model.DocumentData;
import com.gl.documentmanagement.model.MetadataInfo;
import com.gl.documentmanagement.service.DocManagementService;

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
	
	@ApiOperation(value = "Get document data with a document name",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@RequestMapping("/{docName}")
	public Document getDocument(@PathVariable("docName") String docName) {
		logger.info("docname for searchc" + docName);
		DocumentData documentData = null;
		MetadataInfo metadataInfo = null;
		Document document = null;
		documentData = docManagementService.getDocInfo(docName);
		metadataInfo = docManagementService.getMetaInfo(documentData.getDocId());
		document = new Document(documentData.getDocId(), documentData.getDocName(), documentData.getDocLocation(), metadataInfo.getDocType(), metadataInfo.getDocSize());
		return document;
	}
}
