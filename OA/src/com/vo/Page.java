package com.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 对于需要分页的model数据进行分装
 * 
 * @author xue_lang
 */

public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1156391794571918030L;
	/** 定义排序方式  */
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	/* 当前需要查询的页面编号*/
	private int currPage;
	/* 每页记录数*/
	private int size;
	/* 总记录数*/	
	private int totalRecord;
	/* 总页数*/
	private int totalPages;
	/* 页面数据*/
	private List<T> list;

	/**
	 * 默认构造函数，初始化一部分参数
	 * 当前页为 第一页
	 * 页面大小为10
	 * 默认排序为 DESC
	 */
	public Page() {
		this.currPage = 1;
		this.size = 12;
	}
	
	/**
	 * 默认构造函数，初始化一部分参数
	 * 当前页为 第一页
	 * 页面大小为10
	 * 默认排序为 DESC
	 */
	public Page(int currPage,int size,int totalRecord,List<T> list) {
		this();
		this.currPage = currPage;
		this.size = size;
		this.totalRecord = totalRecord;
		this.list = list;
	}
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		
		this.size = size > 0 ? size : this.size;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public boolean isPreviousPage() {
		return currPage != 1;
	}
	public boolean isNextPage() {
		return currPage != totalPages;
	}

	public int getTotalPages() {
		this.totalPages = totalRecord%size == 0 ? totalRecord/size : totalRecord/size + 1;
		if(this.totalRecord == 0) {
			this.totalPages = 1;
		}
		return totalPages;
	}

	public boolean isFirstPage() {
		return currPage == 1;
	}
	public boolean isLastPage() {
		return currPage == totalPages;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}


}
