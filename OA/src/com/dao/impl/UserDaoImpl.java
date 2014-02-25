/**
 * 
 */
package com.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.entity.User;

/**
 * @author xue__lang
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao{

	@Override
	public boolean isExist(String username) {
		return null != this.getByName(username);
	}

	@Override
	public User getByName(String username) {
		Criteria ct = getSession().createCriteria(User.class);
		ct.add(Restrictions.eq("name", username));
		return (User) ct.uniqueResult();
	}


}
