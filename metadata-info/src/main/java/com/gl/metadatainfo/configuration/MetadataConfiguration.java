package com.gl.metadatainfo.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.gl.metadatainfo.model.MetadataInfo;

@Configuration
public class MetadataConfiguration {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	@Bean
	public SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Map<String, String> settings = new HashMap<>();
				settings.put(Environment.DRIVER, "org.h2.Driver");
				settings.put(Environment.URL, "jdbc:h2:mem:testdb");
				settings.put(Environment.USER, "sa");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
				settings.put(Environment.HBM2DDL_AUTO, "update");

				registry = new StandardServiceRegistryBuilder().applySettings(settings).build();
				MetadataSources sources = new MetadataSources(registry)
						.addAnnotatedClass(MetadataInfo.class )
						.addAnnotatedClassName( "com.gl.metadatainfo.model.MetadataInfo" );
				Metadata metadata = sources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
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
