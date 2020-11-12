package com.gl.documentmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(DocManagementService.class);

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
		logger.info("getting doc info for docName "+ docName + " url is " + docInfoUrl + "/docinfo/info/" + docName );
		DocumentData documentData = restTemplate.getForObject(docInfoUrl + "/docinfo/info/" + docName, DocumentData.class);
		return documentData;
	}

	public MetadataInfo getMetaInfo(String docId)
	{
		logger.info("getting meta info for docId "+ docId + " url is " + metaInfoUrl + "/metainfo/info/" + docId );
		MetadataInfo metadataInfo = restTemplate.getForObject(metaInfoUrl  + docId, MetadataInfo.class);
		return metadataInfo;
	}
	
}
