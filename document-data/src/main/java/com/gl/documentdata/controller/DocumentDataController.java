package com.gl.documentdata.controller;

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

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.gl.documentdata.model.DocumentData;
import com.gl.documentdata.service.DocumentDataService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/docinfo")
@XRayEnabled
public class DocumentDataController {
	private static final Logger logger = LoggerFactory.getLogger(DocumentDataController.class);

	private DocumentDataService documentDataService;

	@Autowired
	public  DocumentDataController(DocumentDataService documentDataService) {
		this.documentDataService = documentDataService;
	}

	@ApiOperation(
			value = "Get document data for a document",
			response = DocumentData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/{docId}")
	public DocumentData getDocument(@PathVariable("docId") String docId){
		return
				documentDataService
						.findDocument(docId);
	}


	@ApiOperation(
			value = "Get document data for all documents",	
			response = DocumentData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/all")
	public List<DocumentData> getAllDocuments(){
		return
				documentDataService
						.getAllDocuments();
	}

	@ApiOperation(
			value = "Update document data for a document",
			response = DocumentData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully updated data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PutMapping
	public void updateDocument(@RequestBody DocumentData doc){
		documentDataService.updateDocument(doc);
	}

	@ApiOperation(
			value = "Add document data for a document",
			response = DocumentData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping
	public void addDocument(@RequestBody DocumentData documentData){
		documentDataService.addDocument(documentData);
	}

	@ApiOperation(
			value = "Delete document data for a document",
			response = DocumentData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@DeleteMapping(value = "/{docId}")
	public void deleteDocument(@PathVariable("docId") String docId){
		documentDataService.deleteDocument(docId);
	}
	
}
