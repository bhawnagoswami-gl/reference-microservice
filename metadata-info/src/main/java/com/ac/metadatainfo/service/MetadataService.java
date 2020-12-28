package com.ac.metadatainfo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ac.metadatainfo.controller.MetadataController;
import com.ac.metadatainfo.dao.MetadataDAO;
import com.ac.metadatainfo.model.MetadataInfo;

@Service
public class MetadataService {

	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	private MetadataDAO metadataDAO;

	@Autowired
	public MetadataService(MetadataDAO metadataDAO) {
		this.metadataDAO = metadataDAO;
	}

	public MetadataInfo findMetadataInfo(String docId) {
		return metadataDAO
				.findById(docId)
				.orElse(MetadataInfo.EMPTY_METADATA);
	}

	public List<MetadataInfo> getAllMetadataInfos() {
		return metadataDAO.findAll();
	}

	public void addMetadataInfo(MetadataInfo metadataInfo) {
		MetadataInfo metadata = metadataDAO.save(metadataInfo);
		logger.info("metadata added/updated - {}", metadata);
	}

	public void updateMetadatInfo(MetadataInfo metadataInfo) {
		if(metadataInfo.hasId())
		{
			addMetadataInfo(metadataInfo);
		} else {
			throwMetaInfoNotExistException(metadataInfo);
		}
	}

	public void deleteMetadatInfo(String docId) {
		metadataDAO.deleteById(docId);
	}

	private void throwMetaInfoNotExistException(MetadataInfo metadataInfo){
		logger.warn("metadataInfo {} doesn't exist and so can't be updated");
		throw new RuntimeException("Document " + metadataInfo + " doesn't exist and so can't be updated");
	}

}
