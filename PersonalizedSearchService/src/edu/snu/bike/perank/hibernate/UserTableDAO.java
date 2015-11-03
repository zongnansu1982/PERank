package edu.snu.bike.perank.hibernate;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Data access object (DAO) for domain model class Usertable.
 * 
 * @see edu.snu.bike.perank.hibernate.UserTable
 * @author MyEclipse Persistence Tools
 */

public class UserTableDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(UserTableDAO.class);

	// property constants
	public static final String NAME = "name";

	public static final String IP = "ip";

	public void save(UserTable transientInstance) {
		log.debug("saving Usertable instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
//			  getSession().beginTransaction().commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public boolean userExsit(String name) {
		log.debug("user exsit check");
		try {
			boolean re=true;
			List<UserTable> record=findByName(name);
			if(record.size()==0){
				re=false;
			}
			log.debug("user exsit : "+re);
			return re;
			
//			  getSession().beginTransaction().commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	
	public void delete(UserTable persistentInstance) {
		log.debug("deleting Usertable instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserTable findById(java.lang.Integer id) {
		log.debug("getting Usertable instance with id: " + id);
		try {
			UserTable instance = (UserTable) getSession().get(
					"hibernate.Usertable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserTable instance) {
		log.debug("finding Usertable instance by example");
		try {
			List results = getSession().createCriteria("hibernate.Usertable")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Usertable instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usertable as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findAll() {
		log.debug("finding all Usertable instances");
		try {
			String queryString = "from Usertable";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserTable merge(UserTable detachedInstance) {
		log.debug("merging Usertable instance");
		try {
			UserTable result = (UserTable) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserTable instance) {
		log.debug("attaching dirty Usertable instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserTable instance) {
		log.debug("attaching clean Usertable instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}