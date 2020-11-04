package com.gl.documentdata.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gl.documentdata.controller.DocumentDataController;
import com.gl.documentdata.model.DocumentData;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

@Repository
@EnableJpaRepositories
public class DocumentDataDAO {

	private static final Logger logger = LoggerFactory.getLogger(DocumentDataDAO.class);

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
			logger.error("error while getAllDocs call "+e);
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
				docData = (DocumentData) list.get(0);
			}
			logger.trace("got document with name "+docName);
		} catch (Exception e) {
			logger.error("error while getDoc call "+e);
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
			session.getTransaction().commit();
			logger.trace("document added successfully id is "+id);
		} catch (Exception e) {
			logger.error("error while addDocs call "+e);
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
			logger.trace("document updated successfully");
		} catch (Exception e) {
			logger.error("error while updateDocs call "+e);
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
			logger.trace("document deleted successfully name is "+docName);
		} catch (Exception e) {
			logger.error("error while deleteDocs call "+e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}	  
}
