package com.hz.yisheng.dto;

import java.io.Serializable;

/**
 * 分页查询基类
 * @author ChenJunhui
 *
 */
public class PageQuery implements Serializable{

	private static final long serialVersionUID = -7387381732071141858L;

	public static final int PAGE_SIZE = 10;
	
	private int pageSize;
	private int pageNo;
	
	public int getPageSize() {
		return pageSize<=0?PAGE_SIZE:pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo<=0?1:pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	/**
	 * mysql 分页查询下标起始
	 * @return
	 */
	public long getRowStart(){
		if(pageNo<=1){
			pageNo=1;
		}
		if(pageSize<=0){
			pageSize=PAGE_SIZE;
		}
		return (pageNo - 1) * pageSize;
	}
}
