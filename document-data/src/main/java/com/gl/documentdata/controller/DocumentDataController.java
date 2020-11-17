package com.gl.documentdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.documentdata.model.DocumentData;
import com.gl.documentdata.service.DocumentDataService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/docinfo")
public class DocumentDataController {

	private static final Logger logger = LoggerFactory.getLogger(DocumentDataController.class);


	private DocumentDataService documentDataService;

	public  DocumentDataController(DocumentDataService documentDataService) {
		this.documentDataService = documentDataService;
	}

	@PostMapping(value = "/info")
	public void addDocument(@RequestBody DocumentData documentData){
		logger.info("add function called");
		documentDataService.addDocument(documentData);
	}

	@ApiOperation(value = "get document data for a document",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/info/{docName}")
	public DocumentData getDocument(@PathVariable("docName") String docName){
		logger.info("get function called");
		DocumentData doc = new DocumentData();
		doc = documentDataService.getDocument(docName);
		return doc;
	}

	@DeleteMapping(value = "/info/{docName}")
	public void deleteDocument(@PathVariable("docName") String docName){
		logger.info("delete function called");
		documentDataService.deleteDocument(docName);
	}

	@PutMapping(value = "/info")
	public void updateDocument(@RequestBody DocumentData doc){
		logger.info("update function called");
		documentDataService.updateDocument(doc);
	}

	@GetMapping(value = "/list-docs")
	public List<DocumentData> getAllDocuments(){
		List<DocumentData> list = new ArrayList<>();
		logger.info("getAll function called");
		list = documentDataService.getAllDocuments();
		return list;
	}

}
