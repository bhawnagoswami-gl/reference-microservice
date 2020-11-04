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

@RestController
@RequestMapping("/docinfo")
public class DocumentDataController {

	private static final Logger logger = LoggerFactory.getLogger(DocumentDataController.class);

	@Autowired
	private DocumentDataService documentDataService;

	@PostMapping(value = "/info")
	public void addDocument(@RequestBody DocumentData documentData){
		try{
			logger.trace("add function called");
			documentDataService.addDocument(documentData);
		}catch (Exception e){
			logger.error("error while adddocument call "+e);
		}
		
	}

	@GetMapping(value = "/info/{docName}")
	public DocumentData getDocument(@PathVariable("docName") String docName){
		DocumentData doc = new DocumentData();
		try{
			logger.trace("get function called");
			doc = documentDataService.getDocument(docName);
		}catch (Exception e){
			logger.error("error while getdocument call "+e);
		}
		return doc;
	}

	@DeleteMapping(value = "/info/{docName}")
	public void deleteDocument(@PathVariable("docName") String docName){
		try{
			logger.trace("delete function called");
			documentDataService.deleteDocument(docName);
		}catch (Exception e){
			logger.error("error while deletedocument call "+e);
		}
	}

	@PutMapping(value = "/info")
	public void updateDocument(@RequestBody DocumentData doc){
		try{
			logger.trace("update function called");
			documentDataService.updateDocument(doc);
		}catch (Exception e){
			logger.error("error while updatedocument call "+e);
		}
	}

	@GetMapping(value = "/list-movies")
	public List<DocumentData> getAllDocuments(){
		List<DocumentData> list = new ArrayList<>();
		try{
			logger.trace("getAll function called");
			list = documentDataService.getAllDocuments();
		}catch (Exception e){
			logger.error("error while getAlldocument call "+e);
		}
		return list;
	}

}
