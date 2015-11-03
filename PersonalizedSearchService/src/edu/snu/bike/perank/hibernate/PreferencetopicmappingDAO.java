package edu.snu.bike.perank.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * Data access object (DAO) for domain model class Preferencetopicmapping.
 * 
 * @see edu.snu.bike.perank.hibernate.Preferencetopicmapping
 * @author MyEclipse Persistence Tools
 */

public class PreferencetopicmappingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(PreferencetopicmappingDAO.class);

	// property constants

	public void save(Preferencetopicmapping transientInstance) {
		log.debug("saving Preferencetopicmapping instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
//			  getSession().beginTransaction().commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Preferencetopicmapping persistentInstance) {
		log.debug("deleting Preferencetopicmapping instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Preferencetopicmapping findById(edu.snu.bike.perank.hibernate.PreferencetopicmappingId id) {
		log.debug("getting Preferencetopicmapping instance with id: " + id);
		try {
			Preferencetopicmapping instance = (Preferencetopicmapping) getSession()
					.get("hibernate.Preferencetopicmapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Preferencetopicmapping instance) {
		log.debug("finding Preferencetopicmapping instance by example");
		try {
			List results = getSession().createCriteria(
					"hibernate.Preferencetopicmapping").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Preferencetopicmapping instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Preferencetopicmapping as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Preferencetopicmapping instances");
		try {
			String queryString = "from Preferencetopicmapping";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Preferencetopicmapping merge(Preferencetopicmapping detachedInstance) {
		log.debug("merging Preferencetopicmapping instance");
		try {
			Preferencetopicmapping result = (Preferencetopicmapping) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Preferencetopicmapping instance) {
		log.debug("attaching dirty Preferencetopicmapping instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Preferencetopicmapping instance) {
		log.debug("attaching clean Preferencetopicmapping instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}