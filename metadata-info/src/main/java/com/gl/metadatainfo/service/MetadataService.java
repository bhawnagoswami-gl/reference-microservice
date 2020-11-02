package com.gl.metadatainfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.metadatainfo.dao.MetadataDAO;
import com.gl.metadatainfo.model.MetadataInfo;

@Service
public class MetadataService {

	 @Autowired
	 MetadataDAO metadataDAO;

	  public MetadataInfo getMetadataInfo(long docId) {
		  MetadataInfo metadataInfo = metadataDAO.getMetadataInfo(docId);
	      System.out.println("get bhawna service " +metadataInfo.getDocId());
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

	  public void deleteMetadatInfo(long docId) {
		  metadataDAO.deleteMetadatInfo(docId);
	  }


}
