package com.gl.documentdata.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class DocumentDataConfiguration {
	/*
	 * @Value("${spring.datasource.url}") public String url;
	 * 
	 * @Value("${spring.datasource.driver}") public String driver;
	 * 
	 * @Value("${spring.datasource.username}") public String username;
	 * 
	 * @Value("${spring.datasource.password}") public String password;
	 * 
	 * @Value("${spring.jpa.database-platform}") public String dialect;
	 */
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}


