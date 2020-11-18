package com.gl.documentdata.controller;

import com.gl.documentdata.model.DocumentData;
import com.gl.documentdata.service.DocumentDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docinfo")
public class DocumentDataController {

	private static final Logger logger = LoggerFactory.getLogger(DocumentDataController.class);

	private DocumentDataService documentDataService;

	public  DocumentDataController(DocumentDataService documentDataService) {
		this.documentDataService = documentDataService;
	}


	@ApiOperation(
			value = "Get document data for a document",
			response = DocumentData.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/{docId}")
	public DocumentData getDocument(@PathVariable("docId") String docId){
		return
				documentDataService
						.findDocument(docId);
	}

	@GetMapping(value = "/all")
	public List<DocumentData> getAllDocuments(){
		return
				documentDataService
						.getAllDocuments();
	}

	@PutMapping
	public void updateDocument(@RequestBody DocumentData doc){
		documentDataService.updateDocument(doc);
	}

	@PostMapping
	public void addDocument(@RequestBody DocumentData documentData){
		documentDataService.addDocument(documentData);
	}

	@DeleteMapping(value = "/{docName}")
	public void deleteDocument(@PathVariable("docName") String docName){
		documentDataService.deleteDocument(docName);
	}





}
