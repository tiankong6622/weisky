package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;

/**
 * 家长与孩子的信息关联表
 * @author zfy
 *
 */
public class CustomerChild implements Serializable{

	private static final long serialVersionUID = -1219383741617897753L;
	private Long id; 
	private Long customerId; //家长id
	private Long childrenId;  //孩子id
	private String customerDeviceNumber; //家长设备编号
	private String childrenDeviceNumber; //孩子设备编号
	
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	private int isDeleted; //删除标记
	private int ifAdd; //是否是新增手环（1：新增 0：更换）
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(Long childrenId) {
		this.childrenId = childrenId;
	}
	public String getCustomerDeviceNumber() {
		return customerDeviceNumber;
	}
	public void setCustomerDeviceNumber(String customerDeviceNumber) {
		this.customerDeviceNumber = customerDeviceNumber;
	}
	public String getChildrenDeviceNumber() {
		return childrenDeviceNumber;
	}
	public void setChildrenDeviceNumber(String childrenDeviceNumber) {
		this.childrenDeviceNumber = childrenDeviceNumber;
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
	public int getIfAdd() {
		return ifAdd;
	}
	public void setIfAdd(int ifAdd) {
		this.ifAdd = ifAdd;
	}
}
