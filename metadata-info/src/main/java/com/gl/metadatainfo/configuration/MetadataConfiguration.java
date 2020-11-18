package com.gl.metadatainfo.configuration;

import com.gl.metadatainfo.model.MetadataInfo;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Configuration
public class MetadataConfiguration {
 
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

}
