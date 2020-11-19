package com.gl.documentmanagement.service;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gl.documentmanagement.model.Document;
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

	public DocumentData getDocInfo(String docId)
	{
		logger.info("getting doc info for docId "+ docId + " url is " + docInfoUrl + "/docinfo/" + docId );
		DocumentData documentData = restTemplate.getForObject(docInfoUrl + "/docinfo/" + docId, DocumentData.class);
		logger.info("got documentDatausing document data service ");
		return documentData;
	}

	public MetadataInfo getMetaInfo(String docId)
	{
		logger.info("getting meta info for docId "+ docId + " url is " + metaInfoUrl + "/metainfo/" + docId );
		MetadataInfo metadataInfo = restTemplate.getForObject(metaInfoUrl + "/metainfo/"  + docId, MetadataInfo.class);
		logger.info("got metadata using metadata info service ");
		return metadataInfo;
	}
	
	public void addDocument(Document document) {
		DocumentData documentData = new DocumentData();
		MetadataInfo metadataInfo = new MetadataInfo();
		documentData.setDocId(document.getDocId());
		documentData.setDocName(document.getDocName());
		documentData.setDocLocation(document.getDocLoc());
		metadataInfo.setDocId(document.getDocId());
		metadataInfo.setDocType(document.getDocType());
		metadataInfo.setDocSize(document.getDocSize());
		try {
			HttpHeaders header = new HttpHeaders();
			ResponseEntity<Integer> entity1 = restTemplate.postForEntity(
					new URI(docInfoUrl + "/docinfo/"),
					new HttpEntity<DocumentData>(documentData,header),
					Integer.class);
			logger.info("document info adding "+entity1);
			ResponseEntity<Integer> entity3 = restTemplate.postForEntity(
					new URI(metaInfoUrl + "/metainfo/"),
					new HttpEntity<MetadataInfo>(metadataInfo,header),
					Integer.class);
			logger.info("metadata info adding "+entity3);
		}catch (Exception e){
			logger.error("Error in adding Doc",e);
		}
	}
}
