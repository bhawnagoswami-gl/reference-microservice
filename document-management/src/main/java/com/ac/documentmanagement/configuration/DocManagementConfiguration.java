package com.ac.documentmanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DocManagementConfiguration {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}



}
