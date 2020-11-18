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

	@ApiOperation(
			value = "Post metadata info for a document",
			response = MetadataInfo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully posted data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PostMapping()
	public void addMetadataInfo(@RequestBody MetadataInfo metadataInfo)
	{
		metadataService.addMetadataInfo(metadataInfo);
	}


	@ApiOperation(value = "Get metdata info about a document",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/{docId}")
	public MetadataInfo getMetadataInfo(@PathVariable("docId") String docId){
		return metadataService.findMetadataInfo(docId);
	}
	
	
	@ApiOperation(value = "Delete metdata info about a document",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@DeleteMapping(value = "/{docId}")
	public void deleteMetadatInfo(@PathVariable("docId") String docId){
		metadataService.deleteMetadatInfo(docId);
	}
	

	@ApiOperation(value = "Update metdata info about a document",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully updated data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@PutMapping
	public void updateMetadatInfo(@RequestBody MetadataInfo metadataInfo){
		logger.info("update function called");
		metadataService.updateMetadatInfo(metadataInfo);
	}
	
	
	@ApiOperation(
			value = "Get Metainfo data for all documents",
			response = MetadataInfo.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved data"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value = "/all")
	public List<MetadataInfo> getAllMetadataInfos(){
		 return metadataService.getAllMetadataInfos();
	}


}
