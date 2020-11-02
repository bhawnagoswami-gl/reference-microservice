package com.gl.metadatainfo.controller;

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

import com.gl.metadatainfo.model.MetadataInfo;
import com.gl.metadatainfo.service.MetadataService;

@RestController
@RequestMapping("/metainfo")
public class MetadataController {

	private static final Logger logger = LogManager.getLogger(MetadataController.class);

	@Autowired
	private MetadataService metadataService;

	@PostMapping(value = "/info")
	public void addMetadataInfo(@RequestBody MetadataInfo metadataInfo){
		metadataService.addMetadataInfo(metadataInfo);
	}

	@GetMapping(value = "/info/{docId}")
	public MetadataInfo getMetadataInfo(@PathVariable("docId") long docId){
		MetadataInfo metaInfo = metadataService.getMetadataInfo(docId);
		System.out.println("  in controller  "+metaInfo.getDocId());
		return metaInfo;
	}

	@DeleteMapping(value = "/info/{docName}")
	public void deleteMetadatInfo(@PathVariable("docId") long docId){
		metadataService.deleteMetadatInfo(docId);
	}

	@PutMapping(value = "/info")
	public void updateMetadatInfo(@RequestBody MetadataInfo metadataInfo){
		metadataService.updateMetadatInfo(metadataInfo);
	}

	@GetMapping(value = "/list-movies")
	public List<MetadataInfo> getAllMetadataInfos(){
		List<MetadataInfo> list = new ArrayList<>();
		list = metadataService.getAllMetadataInfos();
		return list;
	}


}
