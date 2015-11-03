package edu.snu.bike.perank.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Data access object (DAO) for domain model class Preferencetable.
 * 
 * @see edu.snu.bike.perank.hibernate.PreferenceTable
 * @author MyEclipse Persistence Tools
 */

public class PreferenceTableDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(PreferenceTableDAO.class);

	// property constants
	public static final String ISWORK = "iswork";

	public static final String MONTH = "month";

	public static final String YEAR = "year";

	public void save(PreferenceTable transientInstance) {
		log.debug("saving Preferencetable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
//			  getSession().beginTransaction().commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PreferenceTable persistentInstance) {
		log.debug("deleting Preferencetable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PreferenceTable findById(java.lang.Integer id) {
		log.debug("getting Preferencetable instance with id: " + id);
		try {
			PreferenceTable instance = (PreferenceTable) getSession().get(
					"hibernate.Preferencetable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PreferenceTable instance) {
		log.debug("finding Preferencetable instance by example");
		try {
			List results = getSession().createCriteria(
					"hibernate.Preferencetable").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Preferencetable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Preferencetable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIswork(Object iswork) {
		return findByProperty(ISWORK, iswork);
	}

	public List findByMonth(Object month) {
		return findByProperty(MONTH, month);
	}

	public List findByYear(Object year) {
		return findByProperty(YEAR, year);
	}

	public List findAll() {
		log.debug("finding all Preferencetable instances");
		try {
			String queryString = "from Preferencetable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PreferenceTable merge(PreferenceTable detachedInstance) {
		log.debug("merging Preferencetable instance");
		try {
			PreferenceTable result = (PreferenceTable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PreferenceTable instance) {
		log.debug("attaching dirty Preferencetable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PreferenceTable instance) {
		log.debug("attaching clean Preferencetable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}