package edu.snu.bike.perank.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Data access object (DAO) for domain model class Preferencelinkmapping.
 * 
 * @see edu.snu.bike.perank.hibernate.PreferenceLinkMapping
 * @author MyEclipse Persistence Tools
 */

public class PreferenceLinkMappingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(PreferenceLinkMappingDAO.class);

	// property constants

	public void save(PreferenceLinkMapping transientInstance) {
		log.debug("saving Preferencelinkmapping instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
//			  getSession().beginTransaction().commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PreferenceLinkMapping persistentInstance) {
		log.debug("deleting Preferencelinkmapping instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PreferenceLinkMapping findById(edu.snu.bike.perank.hibernate.PreferenceLinkMappingId id) {
		log.debug("getting Preferencelinkmapping instance with id: " + id);
		try {
			PreferenceLinkMapping instance = (PreferenceLinkMapping) getSession()
					.get("hibernate.Preferencelinkmapping", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PreferenceLinkMapping instance) {
		log.debug("finding Preferencelinkmapping instance by example");
		try {
			List results = getSession().createCriteria(
					"hibernate.Preferencelinkmapping").add(
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
		log.debug("finding Preferencelinkmapping instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Preferencelinkmapping as model where model."
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
		log.debug("finding all Preferencelinkmapping instances");
		try {
			String queryString = "from Preferencelinkmapping";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PreferenceLinkMapping merge(PreferenceLinkMapping detachedInstance) {
		log.debug("merging Preferencelinkmapping instance");
		try {
			PreferenceLinkMapping result = (PreferenceLinkMapping) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PreferenceLinkMapping instance) {
		log.debug("attaching dirty Preferencelinkmapping instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PreferenceLinkMapping instance) {
		log.debug("attaching clean Preferencelinkmapping instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}