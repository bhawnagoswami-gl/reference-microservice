package com.gl.metadatainfo.controller;

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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.gl.metadatainfo.model.MetadataInfo;
import com.gl.metadatainfo.service.MetadataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/metainfo")
public class MetadataController {

	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);

	private MetadataService metadataService;

	@Autowired
	public MetadataController(MetadataService metadataService) {
		this.metadataService = metadataService;
	}

	@PostMapping(value = "/info")
	public void addMetadataInfo(@RequestBody MetadataInfo metadataInfo)
	{
		logger.info("add function called");
		metadataService.addMetadataInfo(metadataInfo);
	}

	@ApiOperation(value = "get metdata info about a document",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/info/{docId}")
	public MetadataInfo getMetadataInfo(@PathVariable("docId") String docId){
		MetadataInfo metaInfo = null;
		metaInfo = metadataService.getMetadataInfo(docId);
		logger.info("In MetadataController "+metaInfo.getDocId());
		return metaInfo;
	}

	@DeleteMapping(value = "/info/{docName}")
	public void deleteMetadatInfo(@PathVariable("docId") String docId){
		logger.info("delete function called");
		metadataService.deleteMetadatInfo(docId);
	}

	@PutMapping(value = "/info")
	public void updateMetadatInfo(@RequestBody MetadataInfo metadataInfo){
		logger.info("update function called");
		metadataService.updateMetadatInfo(metadataInfo);
	}

	@GetMapping(value = "/list-metainfo")
	public List<MetadataInfo> getAllMetadataInfos(){
		List<MetadataInfo> list = new ArrayList<>();
		logger.info("getAll function called");
		list = metadataService.getAllMetadataInfos();
		return list;
	}


}
