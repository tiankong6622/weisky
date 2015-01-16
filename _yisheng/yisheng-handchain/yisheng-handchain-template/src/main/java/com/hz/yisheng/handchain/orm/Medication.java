package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;

public class Medication implements Serializable{

	private static final long serialVersionUID = -5023721360273941614L;
	private Long id;       
	private String medicationName;  //药名
	private String medicationNum; //药量
	private Date medicationDate;  //用药时间
	private int customerId;  //用户Id
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
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public String getMedicationNum() {
		return medicationNum;
	}
	public void setMedicationNum(String medicationNum) {
		this.medicationNum = medicationNum;
	}
	public Date getMedicationDate() {
		return medicationDate;
	}
	public void setMedicationDate(Date medicationDate) {
		this.medicationDate = medicationDate;
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
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
