package com.gl.documentdata.dao;

import com.gl.documentdata.model.DocumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentDataDAO extends JpaRepository <DocumentData, String> {

}
