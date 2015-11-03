package edu.snu.bike.perank.hibernate;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	@Override
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}