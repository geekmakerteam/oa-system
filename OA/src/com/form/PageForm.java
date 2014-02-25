package com.form;

import javax.validation.constraints.NotNull;

/**
 * ��ҳ������
 * @author xue__lang
 */
public class PageForm {
	
	public static final int DEFAULT_PAGE_SIZE = 10;
	/**
	 * ҳ���С
	 */
	private Integer size;
	
	/**
	 * ҳ��
	 */
	private Integer currPage;
	
	/**
	 * �����д���
	 */
	private Integer sidx;
	
	/**
	 * ����ʽ
	 */
	private String sord;

	public PageForm() {
		this.size = DEFAULT_PAGE_SIZE;
		this.currPage = 1;
	}
	public PageForm(int size, int curr, int sidx, String sord) {
		super();
		this.size = size;
		this.currPage = curr;
		this.sidx = sidx;
		this.sord = sord;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int curr) {
		this.currPage = curr;
	}
	public int getSidx() {
		return sidx;
	}
	public void setSidx(int sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public int getRowStart() {
		return	this.getSize() * (this.getCurrPage() - 1);
	}

}
