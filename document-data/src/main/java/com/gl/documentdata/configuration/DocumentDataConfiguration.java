package com.gl.documentdata.configuration;

import com.gl.documentdata.model.DocumentData;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class DocumentDataConfiguration {
	@Value("${spring.datasource.url}")
	public String url;
	@Value("${spring.datasource.driver}")
	public String driver;
	@Value("${spring.datasource.username}")
	public String username;
	@Value("${spring.datasource.password}")
	public String password;
	@Value("${spring.jpa.database-platform}")
	public String dialect;

	@Bean
	public SessionFactory getSessionFactory() {
		SessionFactory factory = null;
		if (factory == null) {
			try {
				Properties settings = new Properties();
				settings.put("hibernate.connection.driver_class", driver);
				settings.put("hibernate.connection.url", url);
				settings.put("hibernate.connection.username", username);
				settings.put("hibernate.connection.password",password);
				settings.put("show_sql", true);
				settings.put("dialect", dialect);
				org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
				cfg.setProperties(settings);
				cfg.addAnnotatedClass(DocumentData.class);
				factory = cfg.buildSessionFactory();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return factory;
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}


