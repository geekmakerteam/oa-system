/**
 * 
 */
package com.dao;


import com.entity.User;

/**
 * UserDao Interface
 * @author xue__lang
 */
public interface UserDao extends BaseDao<User, Integer>{
	/**
	 * �жϴ��û������û�������
	 * @param  username ����
	 * @return boolean true/����  false/������
	 */
	public boolean isExist(String username);
	
	/**
	 * �����û�������û�
	 * @param username �û���
	 * @return �û�
	 */
	public User getByName(String username);

	
}
