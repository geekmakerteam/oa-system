/**
 * 
 */
package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;
import com.form.PageForm;
import com.vo.Page;

/**
 * @author xue__lang
 *
 */
public interface UserService {
	
	/**
	 * 获得用户列表
	 * @param pageForm 查询表单
	 * @return page
	 */
	public Page<User> findBypage(PageForm pageForm);
	

	/**
	 * 根据用户民查找用户
	 * @param username 用户名
	 * @return 用户
	 */
	public User getByName(String username);

	
}
