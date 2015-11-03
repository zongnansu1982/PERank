package edu.snu.bike.perank.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Data access object (DAO) for domain model class Linkintresttable.
 * 
 * @see edu.snu.bike.perank.hibernate.LinkInterestTable
 * @author MyEclipse Persistence Tools
 */

public class LinkInterestTableDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(LinkInterestTableDAO.class);

	// property constants
	public static final String LINK = "link";

	public void save(LinkInterestTable transientInstance) {
		log.debug("saving Linkintresttable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
//			  getSession().beginTransaction().commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public boolean linkExsit(String link) {
		log.debug("link exsit check");
		try {
			boolean re=true;
			List record=findByLink(link);
			if(record.size()==0){
				re=false;
			}
			log.debug("link exsit: "+re);
			return re;
//			  getSession().beginTransaction().commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	

	public void delete(LinkInterestTable persistentInstance) {
		log.debug("deleting Linkintresttable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LinkInterestTable findById(java.lang.Integer id) {
		log.debug("getting Linkintresttable instance with id: " + id);
		try {
			LinkInterestTable instance = (LinkInterestTable) getSession().get(
					"hibernate.Linkintresttable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LinkInterestTable instance) {
		log.debug("finding Linkintresttable instance by example");
		try {
			List results = getSession().createCriteria(
					"hibernate.Linkintresttable").add(Example.create(instance))
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
		log.debug("finding Linkintresttable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Linkintresttable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLink(Object link) {
		return findByProperty(LINK, link);
	}

	public List findAll() {
		log.debug("finding all Linkintresttable instances");
		try {
			String queryString = "from Linkintresttable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LinkInterestTable merge(LinkInterestTable detachedInstance) {
		log.debug("merging Linkintresttable instance");
		try {
			LinkInterestTable result = (LinkInterestTable) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LinkInterestTable instance) {
		log.debug("attaching dirty Linkintresttable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LinkInterestTable instance) {
		log.debug("attaching clean Linkintresttable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}