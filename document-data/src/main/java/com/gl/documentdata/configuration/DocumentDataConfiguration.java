package com.gl.documentdata.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.gl.documentdata.model.DocumentData;

@org.springframework.context.annotation.Configuration
public class DocumentDataConfiguration {

	@Bean
	public SessionFactory getSessionFactory() {
		SessionFactory factory = null;
		if (factory == null) {
			try {
				Properties settings = new Properties();
				settings.put("hibernate.connection.driver_class", "org.h2.Driver");
				settings.put("hibernate.connection.url", "jdbc:h2:mem:testdb");
				settings.put("hibernate.connection.username", "sa");
				settings.put("show_sql", true);
				settings.put("dialect", "org.hibernate.dialect.H2Dialect");
				Configuration cfg = new Configuration();
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

	@Bean
	public Properties hibernateProperties() { 
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("dialect","org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("show_sql", "true");
		hibernateProperties.setProperty("hbm2ddl.auto", "auto"); 
		return hibernateProperties; 
	}

}
