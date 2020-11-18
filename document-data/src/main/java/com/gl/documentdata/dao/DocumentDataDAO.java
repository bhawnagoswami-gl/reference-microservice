package com.gl.documentdata.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.documentdata.model.DocumentData;

@Repository
public interface DocumentDataDAO extends JpaRepository <DocumentData, String> {

}
