package com.gl.documentdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(DocumentDataController.class);

	@Autowired
	private DocumentDataService documentDataService;

	@PostMapping(value = "/info")
	public void addDocument(@RequestBody DocumentData documentData){
		documentDataService.addDocument(documentData);
	}

	@GetMapping(value = "/info/{docName}")
	public DocumentData getDocument(@PathVariable("docName") String docName){
		DocumentData doc = new DocumentData();
		try{
			System.out.println("going in controller of document-data service  "+doc.getDocName());
			doc = documentDataService.getDocument(docName);
			System.out.println("  in controller of document  "+doc.getDocName());
		}catch (Exception e){
			logger.error(e);
			System.out.println(e);
		}
		return doc;
	}

	@DeleteMapping(value = "/info/{docName}")
	public void deleteDocument(@PathVariable("docName") String docName){
		try{
			documentDataService.deleteDocument(docName);
		}catch (Exception e){
			logger.error(e);
		}
	}

	@PutMapping(value = "/info")
	public void updateDocument(@RequestBody DocumentData doc){
		try{
			documentDataService.updateDocument(doc);
		}catch (Exception e){
			logger.error(e);
		}
	}

	@GetMapping(value = "/list-movies")
	public List<DocumentData> getAllDocuments(){
		List<DocumentData> list = new ArrayList<>();
		try{
			list = documentDataService.getAllDocuments();
		}catch (Exception e){
			logger.error(e);
		}
		return list;
	}

}
