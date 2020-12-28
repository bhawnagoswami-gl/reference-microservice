package com.ac.documentdata.configuration;

import org.apache.http.impl.client.HttpClientBuilder;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DocumentDataConfiguration {

	public SessionFactory sessionFactory;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}

