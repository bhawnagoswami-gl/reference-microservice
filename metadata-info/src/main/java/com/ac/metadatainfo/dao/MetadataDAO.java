package com.ac.metadatainfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.metadatainfo.model.MetadataInfo;

@Repository
public interface MetadataDAO extends JpaRepository <MetadataInfo, String> {

}
