package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;

public class Lactation implements Serializable{

	private static final long serialVersionUID = 1767735480226614335L;
	private Long id;       
	private String lactationNum;  //哺乳量
	private Date lactationTime;  //哺乳时间
	private int childrenId;  //孩子Id
	private String customerName;  //用户名称
	
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	private int isDeleted; //删除标记
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLactationNum() {
		return lactationNum;
	}
	public void setLactationNum(String lactationNum) {
		this.lactationNum = lactationNum;
	}
	public Date getLactationTime() {
		return lactationTime;
	}
	public void setLactationTime(Date lactationTime) {
		this.lactationTime = lactationTime;
	}
	public int getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(int childrenId) {
		this.childrenId = childrenId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

}
