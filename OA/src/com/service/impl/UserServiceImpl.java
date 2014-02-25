/**
 * 
 */
package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;
import com.form.PageForm;
import com.service.UserService;
import com.vo.Page;

/**
 * @author xue__lang
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;
	
	@Transactional(readOnly=true)
	public Page<User> findBypage(PageForm pageForm){
		return	dao.findByPage(pageForm);
	}
	
	@Transactional(readOnly=true)
	public User getByName(String username){
		return	dao.getByName(username);
	}


	public UserDao getDao() {
		return dao;
	}


	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	
}
