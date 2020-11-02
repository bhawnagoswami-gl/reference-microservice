package com.gl.metadatainfo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gl.metadatainfo.model.MetadataInfo;

@Repository
@EnableJpaRepositories
public class MetadataDAO {

	private static final Logger logger = LogManager.getLogger(MetadataDAO.class);
	  
	  protected SessionFactory sessionFactory;
	  
	  @Autowired
	  public MetadataDAO(SessionFactory sessionFactory)
	  {
		  this.sessionFactory = sessionFactory;
	  }
	  
	  public void exit() {
	    sessionFactory.close();
	  }
	  
	  public void closeFactory() {
		    if (sessionFactory != null) {
		        try {
		            sessionFactory.close();
		        } catch (HibernateException ignored) {
		        	logger.error("Couldn't close SessionFactory", ignored);
		        }
		    }
	  }

	  public List<MetadataInfo> getAllMetadataInfos() {
	    Session session = sessionFactory.openSession();
	    List<MetadataInfo> list = new ArrayList<>();
	    try {
	    	list = session.createQuery("from MetadataInfo").getResultList();
	    }
	    catch (Exception e) {
	        logger.error(e);
	      }
	      finally {
	          if (session != null) {
	              session.close();
	          }
	      }
	    return list;
	  }

	  public MetadataInfo getMetadataInfo(long docId) {
		  MetadataInfo metadata = new MetadataInfo() {
		};
	    Session session = sessionFactory.openSession();
	    try {
	    	metadata = session.get(MetadataInfo.class, docId);
		    System.out.println("in metainfo dao   "+metadata.getDocId());
	      logger.info(metadata);
	    } catch (Exception e) {
	      logger.error(e);
	    }
	    finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return metadata;
	  }

	  public void addMetadataInfo(MetadataInfo metadataInfo) {
	    Session session = sessionFactory.openSession();
	    try {
	      session.beginTransaction();
	      String id = (String) session.save(metadataInfo);
	      System.out.print("id is "+id);
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      logger.error(e);
	    }
	    finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	  }

	  public void updateMetadatInfo(MetadataInfo metadataInfo) {
	    Session session = sessionFactory.openSession();
	    try {
	      session.beginTransaction();
	      session.update(metadataInfo);
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      logger.error(e);
	    }
	    finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	  }

	  public void deleteMetadatInfo(long docId) {
	    Session session = sessionFactory.openSession();
	    try {
	      session.beginTransaction();
	      session.delete(getMetadataInfo(docId));
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      logger.error(e);
	    }
	    finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	  }


	  

}
