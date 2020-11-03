package com.gl.documentdata.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gl.documentdata.model.DocumentData;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@Repository
@EnableJpaRepositories
public class DocumentDataDAO {

	 private static final Logger logger = LogManager.getLogger(DocumentDataDAO.class);
	  @Autowired
	  protected SessionFactory sessionFactory;

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

	  public List<DocumentData> getAllDocs() {
	    Session session = sessionFactory.openSession();
	    System.out.println(sessionFactory + "  " + session);
	    List<DocumentData> list = new ArrayList<>();
	    try {
	      list = session.createQuery("from DocumentData").getResultList();
	    } catch (Exception e) {
	      logger.error(e);
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	    }
	    return list;
	  }

	  public DocumentData getDocument(String docName) {
	    DocumentData docData = new DocumentData();
	    Session session = sessionFactory.openSession();
	    try {
	      //docData = session.get(DocumentData.class, docName);
	      List list = session.createCriteria(DocumentData.class)
	              .add(Restrictions.like("docName", "%" + docName + "%")).list();
	      if (list != null) {
	        System.out.println("getting the doc data in DAO");
	        docData = (DocumentData) list.get(0);
	        System.out.println("getting the doc data in DAO " + docData.getDocId());
	      }
	      System.out.println("in dao   " + docData.getDocName());
	      logger.info(docData);
	    } catch (Exception e) {
	      logger.error(e);
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	    }
	    return docData;
	  }

	  public void addDocument(DocumentData docData) {
	    Session session = sessionFactory.openSession();
	    try {
	      session.beginTransaction();
	      String id = (String) session.save(docData);
	      System.out.print("id is " + id);
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      logger.error(e);
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	    }
	  }

	  public void updateDocument(DocumentData docData) {
	    Session session = sessionFactory.openSession();
	    try {
	      session.beginTransaction();
	      session.update(docData);
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      logger.error(e);
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	    }
	  }

	  public void deleteDocument(String docName) {
	    Session session = sessionFactory.openSession();
	    try {
	      session.beginTransaction();
	      session.delete(getDocument(docName));
	      session.getTransaction().commit();
	    } catch (Exception e) {
	      logger.error(e);
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	    }
	  }	  
}
