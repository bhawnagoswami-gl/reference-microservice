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

import com.gl.metadatainfo.model.MetadataInfo;
import com.gl.metadatainfo.service.MetadataService;

@RestController
@RequestMapping("/metainfo")
public class MetadataController {

	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);

	@Autowired
	private MetadataService metadataService;

	@PostMapping(value = "/info")
	public void addMetadataInfo(@RequestBody MetadataInfo metadataInfo)
	{try{
		logger.trace("add function called");
		metadataService.addMetadataInfo(metadataInfo);
	}catch (Exception e){
		logger.error("error while adddocument call "+e);
	}
	}

	@GetMapping(value = "/info/{docId}")
	public MetadataInfo getMetadataInfo(@PathVariable("docId") String docId){
		MetadataInfo metaInfo = null;
		try{
		metaInfo = metadataService.getMetadataInfo(docId);
		System.out.println("  in controller  "+metaInfo.getDocId());
	}catch (Exception e){
		logger.error("error while adddocument call "+e);
	}
		return metaInfo;
	}

	@DeleteMapping(value = "/info/{docName}")
	public void deleteMetadatInfo(@PathVariable("docId") String docId){
		try{
			logger.trace("delete function called");
		metadataService.deleteMetadatInfo(docId);
		}catch (Exception e){
			logger.error("error while deletedocument call "+e);
		}
	}

	@PutMapping(value = "/info")
	public void updateMetadatInfo(@RequestBody MetadataInfo metadataInfo){
		try{
			logger.trace("update function called");
		metadataService.updateMetadatInfo(metadataInfo);
		}catch (Exception e){
			logger.error("error while updatedocument call "+e);
		}
	}

	@GetMapping(value = "/list-movies")
	public List<MetadataInfo> getAllMetadataInfos(){
		List<MetadataInfo> list = new ArrayList<>();
		try{
			logger.trace("getAll function called");
		list = metadataService.getAllMetadataInfos();
		}catch (Exception e){
			logger.error("error while getAlldocument call "+e);
		}
		return list;
	}


}
