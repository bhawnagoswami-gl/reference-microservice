package com.gl.documentmanagement.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gl.documentmanagement.model.DocumentData;
import com.gl.documentmanagement.model.MetadataInfo;

@Service
@ConfigurationProperties(prefix="endpoint")
public class DocManagementService {

	private static final Logger logger = LogManager.getLogger(DocManagementService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${endpoint.metainfoUrl}")
	private String metaInfoUrl;

	@Value("${endpoint.docinfoUrl}")
	private String docInfoUrl;

	@Autowired
	public DocManagementService() {
	}

	public DocumentData getDocInfo(String docName)
	{
		logger.info("getting doc info for docName "+ docName + " url is "+docInfoUrl+docName );
		DocumentData documentData = restTemplate.getForObject("http://localhost:8081/docinfo/info/" + docName, DocumentData.class);
		return documentData;
	}

	public MetadataInfo getMetaInfo(String docId)
	{
		logger.info("getting description for doc " + docId);
		MetadataInfo metadataInfo = restTemplate.getForObject("http://localhost:8082/metainfo/info/" + docId, MetadataInfo.class);
		return metadataInfo;
	}
	
}
