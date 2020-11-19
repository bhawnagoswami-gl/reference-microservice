package com.gl.documentdata.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DocumentDataConfiguration {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.addDataSourceProperty("connectionTimeout", "40000");
		config.addDataSourceProperty("idleTimeout", "300000");
		config.addDataSourceProperty("maximumPoolSize", "50");
		config.addDataSourceProperty("maxLifetime", "1200000");
		config.addDataSourceProperty("autoCommit", "false");
	    return new HikariDataSource(config);
	}
}


