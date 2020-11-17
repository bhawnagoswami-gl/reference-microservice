package com.gl.metadatainfo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.metadatainfo.controller.MetadataController;
import com.gl.metadatainfo.dao.MetadataDAO;
import com.gl.metadatainfo.model.MetadataInfo;

@Service
public class MetadataService {

	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	private MetadataDAO metadataDAO;

	@Autowired
	public MetadataService(MetadataDAO metadataDAO) {
		this.metadataDAO = metadataDAO;
	}

	public MetadataInfo getMetadataInfo(String docId) {
		MetadataInfo metadataInfo = metadataDAO.getMetadataInfo(docId);
		logger.info("In MetadataService "+ docId);
		return metadataInfo;
	}

	public List<MetadataInfo> getAllMetadataInfos() {
		List<MetadataInfo> list = metadataDAO.getAllMetadataInfos();
		return list;
	}

	public void addMetadataInfo(MetadataInfo metadataInfo) {
		metadataDAO.addMetadataInfo(metadataInfo);
	}

	public void updateMetadatInfo(MetadataInfo metadataInfo) {
		metadataDAO.updateMetadatInfo(metadataInfo);
	}

	public void deleteMetadatInfo(String docId) {
		metadataDAO.deleteMetadatInfo(docId);
	}


}
