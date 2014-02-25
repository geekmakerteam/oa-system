package com.dao;

import java.io.Serializable;
import java.util.List;

import com.form.PageForm;
import com.vo.Page;

public interface BaseDao<T, PK extends Serializable>{
	
	/**
	 * 根据id 获得一个对对象
	 * @param id 
	 */
	public T get(PK id);

	/**
	 * 根据id 获得多个对对象
	 * @param ids[] id数组 
	 */
	public List<T> get(PK ids[]);
	
	/**
	 *  通过属性名获取一个对象
	 *  @param property 列名
	 *  @param value 列的值 
	 */
	//public T getByProperty(String property,Object value);
	
	
	/**
	 * 根据pageFrom获取一个page
	 * @param PageForm  读取数据的表单
	 */
	public Page<T> findByPage(PageForm pageForm); 

	/**
	 * 根据id 获得一个对对象,使用时才发出sql语句
	 * @param id 
	 */
	public T load(PK id);
	/**
	 *  批量删除
	 *  @param ids id数组
	 */
	public int delete(PK[] ids);
	
	/**
	 *  持久化一个对象
	 *  @param 持久化对象
	 */
	public PK save(T entity);
	
	/**
	 *  跟新一个对象
	 *  @param 所跟新对象
	 */	
	public void update(T entity);

}