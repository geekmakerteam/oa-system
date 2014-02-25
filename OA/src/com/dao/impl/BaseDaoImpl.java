package com.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.dao.BaseDao;
import com.form.PageForm;
import com.vo.Page;

/**
 *  基础  dao 的实现类
 * @author xue_lang
 * 
 */
public abstract class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private static Logger log = Logger.getLogger(BaseDaoImpl.class);

	private Class<T> entityClass;

	@Autowired
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = null;
		/* Class<?> c = getClass() */;
		Type type = this.getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public T get(PK id) {
		log.debug(" get(PK id)  " + this.entityClass + "  " + id);
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> get(PK[] ids) {
		log.debug("get(PK[] ids) " + this.entityClass);
		Criteria ct = getSession().createCriteria(entityClass);
		ct.add(Restrictions.in("id", ids));
		return ct.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T load(PK id) {
		log.debug( "T load(PK id) " + this.entityClass);
		return (T) getSession().load(entityClass, id);
	}

	
	public Long getTotalCount() {

		log.debug(" getTotalCount()  " + this.entityClass + "  ");
			String hql = "select count(*) from " + entityClass.getName();
			return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK save(T entity) {
		log.debug("save(T entity) " + this.entityClass + " instance");
			return (PK) getSession().save(entity);
	}
	
	@Override
	public void update(T entity) {
		log.debug("update(T entity) " + this.entityClass + " instance");
			getSession().update(entity);
	}

	@Override
	public int delete(PK[] ids) {
		log.debug("delete(PK[] ids) " + this.entityClass + " instance");
		Assert.isNull(ids," ids 不能为空 ");
		String hql = "delete from "+ this.entityClass + " where id in ";
		String idStr = Arrays.toString(ids).replace("[", "(").replace("]",")");
		hql += idStr;
		getSession().createQuery(hql).executeUpdate();
		return ids.length;
	}

	@Override
	public Page<T> findByPage(PageForm pageForm) {
		
		if(pageForm == null) {
			pageForm = new PageForm();
		}
		
		Criteria criteria = getSession().createCriteria(this.entityClass);
		long  l = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		Integer total =	Integer.parseInt(String.valueOf(l));
		criteria.setProjection(null); //
		// criteria.setProjection(Projections.distinct(Property.forName("id")));
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		
		criteria.setFirstResult(pageForm.getRowStart()).setMaxResults(pageForm.getSize());
		
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list(); 
		
		return new Page<T>(pageForm.getCurrPage(),pageForm.getSize(),total,list);


	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	protected void rollback() {
		getSession().getTransaction().rollback();
	}
	
	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void evict(Object object) {
		getSession().evict(object);
	}

}