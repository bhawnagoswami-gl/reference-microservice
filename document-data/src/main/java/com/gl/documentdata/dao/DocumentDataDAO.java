package com.gl.documentdata.dao;

import com.gl.documentdata.model.DocumentData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDataDAO extends CrudRepository<DocumentData, String> {

    @Override
    List<DocumentData> findAll();


}
