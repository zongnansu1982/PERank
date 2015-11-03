package edu.snu.bike.perank.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Data access object (DAO) for domain model class Preferencetopicmapping.
 * 
 * @see edu.snu.bike.perank.hibernate.PreferenceTopicMapping
 * @author MyEclipse Persistence Tools
 */

public class PreferenceTopicMappingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(PreferenceTopicMappingDAO.class);

	// property constants

	public void save(PreferenceTopicMapping transientInstance) {
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

	public void delete(PreferenceTopicMapping persistentInstance) {
		log.debug("deleting Preferencetopicmapping instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PreferenceTopicMapping findById(edu.snu.bike.perank.hibernate.PreferenceTopicMappingId id) {
		log.debug("getting Preferencetopicmapping instance with id: " + id);
		try {
			PreferenceTopicMapping instance = (PreferenceTopicMapping) getSession()
					.get("hibernate.Preferencetopicmapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PreferenceTopicMapping instance) {
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

	public PreferenceTopicMapping merge(PreferenceTopicMapping detachedInstance) {
		log.debug("merging Preferencetopicmapping instance");
		try {
			PreferenceTopicMapping result = (PreferenceTopicMapping) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PreferenceTopicMapping instance) {
		log.debug("attaching dirty Preferencetopicmapping instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PreferenceTopicMapping instance) {
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