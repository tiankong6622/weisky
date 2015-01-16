package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;

/**
 * 婴儿健康信息
 * @author zfy
 *
 */
public class ChildHealth implements Serializable{

	private static final long serialVersionUID = -7209126541510459875L;
	private Long id;
	private String name;
	private Long customerId; //婴儿的ID
	private double icterus;  //黄疸值
	private double bloodPre; //血压值
	private double heartRate; //心率
	private double temperature;  //体温
	
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public double getIcterus() {
		return icterus;
	}
	public void setIcterus(double icterus) {
		this.icterus = icterus;
	}
	public double getBloodPre() {
		return bloodPre;
	}
	public void setBloodPre(double bloodPre) {
		this.bloodPre = bloodPre;
	}
	public double getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(double heartRate) {
		this.heartRate = heartRate;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
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
	

}
