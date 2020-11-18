package com.gl.metadatainfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.metadatainfo.model.MetadataInfo;

@Repository
public interface MetadataDAO extends JpaRepository <MetadataInfo, String> {

}
