package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;
/**
 * 客户设备信息
 * @author zfy
 *
 */
public class CustomerDevice implements Serializable{

	private static final long serialVersionUID = 3181606602394559496L;
	private Long id;       
	private Long customerId;  //设备所属客户的ID
	private String number; //设备编号
	private String status;  //启动状态
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	
	private String name;
	
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
