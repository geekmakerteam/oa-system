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
	 * ����û��б�
	 * @param pageForm ��ѯ��
	 * @return page
	 */
	public Page<User> findBypage(PageForm pageForm);
	

	/**
	 * �����û�������û�
	 * @param username �û���
	 * @return �û�
	 */
	public User getByName(String username);

	
}
