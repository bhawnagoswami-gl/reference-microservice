package com.gl.documentdata.configuration;

import com.gl.documentdata.model.DocumentData;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class DocumentDataConfiguration {



	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}


