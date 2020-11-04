package com.gl.documentdata.configuration;

import com.gl.documentdata.model.DocumentData;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;


import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class DocumentDataConfiguration {

  @Bean
  public SessionFactory getSessionFactory() {
    SessionFactory factory = null;
    if (factory == null) {
      try {
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12374393");
        settings.put(Environment.USER, "sql12374393");
        settings.put(Environment.PASS,"y3MVIRdmHb");
        settings.put("show_sql", true);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
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


}