package com.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ������Ҫ��ҳ��model���ݽ��з�װ
 * 
 * @author xue_lang
 */

public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1156391794571918030L;
	/** ��������ʽ  */
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	/* ��ǰ��Ҫ��ѯ��ҳ����*/
	private int currPage;
	/* ÿҳ��¼��*/
	private int size;
	/* �ܼ�¼��*/	
	private int totalRecord;
	/* ��ҳ��*/
	private int totalPages;
	/* ҳ������*/
	private List<T> list;

	/**
	 * Ĭ�Ϲ��캯������ʼ��һ���ֲ���
	 * ��ǰҳΪ ��һҳ
	 * ҳ���СΪ10
	 * Ĭ������Ϊ DESC
	 */
	public Page() {
		this.currPage = 1;
		this.size = 12;
	}
	
	/**
	 * Ĭ�Ϲ��캯������ʼ��һ���ֲ���
	 * ��ǰҳΪ ��һҳ
	 * ҳ���СΪ10
	 * Ĭ������Ϊ DESC
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
