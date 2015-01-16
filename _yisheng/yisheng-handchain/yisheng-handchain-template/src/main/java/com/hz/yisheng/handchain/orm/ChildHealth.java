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
	private Long customerId; //用户ID
	private double icterus;  //黄疸值
	private double bloodPre; //血压值
	private double heartRate; //心率
	private double temperature;  //体温
	
	private double weight;  //体重
	private String bloodType;//血型 
	private double redBloodCell;//红细胞
	private double whiteBloodCell;//白细胞
	private double bloodPlat;//血小板
	private double glucose;//葡萄糖
	private double carba;//尿素
	private double trioxy;//尿酸
	private double creatinine;//肌酐
	private double pyruvicAcid;//丙酮酸
	private double bloodFat;//血脂
	private double cholest;//胆固醇
	private double phospho;//磷脂
	private double trigly;//甘油三酯
	private double vitalCapa;//肺活量
	private double cordBlood; //脐带血
	
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	private int isDeleted; //删除标记
	private Long gatherer; //采集人
	private Date gatherDate; // 采集时间
	
	private String picture; //健康指标图
	
	
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
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public double getRedBloodCell() {
		return redBloodCell;
	}
	public void setRedBloodCell(double redBloodCell) {
		this.redBloodCell = redBloodCell;
	}
	public double getWhiteBloodCell() {
		return whiteBloodCell;
	}
	public void setWhiteBloodCell(double whiteBloodCell) {
		this.whiteBloodCell = whiteBloodCell;
	}
	public double getBloodPlat() {
		return bloodPlat;
	}
	public void setBloodPlat(double bloodPlat) {
		this.bloodPlat = bloodPlat;
	}
	public double getGlucose() {
		return glucose;
	}
	public void setGlucose(double glucose) {
		this.glucose = glucose;
	}
	public double getCarba() {
		return carba;
	}
	public void setCarba(double carba) {
		this.carba = carba;
	}
	public double getTrioxy() {
		return trioxy;
	}
	public void setTrioxy(double trioxy) {
		this.trioxy = trioxy;
	}
	public double getCreatinine() {
		return creatinine;
	}
	public void setCreatinine(double creatinine) {
		this.creatinine = creatinine;
	}
	public double getPyruvicAcid() {
		return pyruvicAcid;
	}
	public void setPyruvicAcid(double pyruvicAcid) {
		this.pyruvicAcid = pyruvicAcid;
	}
	public double getBloodFat() {
		return bloodFat;
	}
	public void setBloodFat(double bloodFat) {
		this.bloodFat = bloodFat;
	}
	public double getCholest() {
		return cholest;
	}
	public void setCholest(double cholest) {
		this.cholest = cholest;
	}
	public double getPhospho() {
		return phospho;
	}
	public void setPhospho(double phospho) {
		this.phospho = phospho;
	}
	public double getTrigly() {
		return trigly;
	}
	public void setTrigly(double trigly) {
		this.trigly = trigly;
	}
	public double getVitalCapa() {
		return vitalCapa;
	}
	public void setVitalCapa(double vitalCapa) {
		this.vitalCapa = vitalCapa;
	}
	public Long getGatherer() {
		return gatherer;
	}
	public void setGatherer(Long gatherer) {
		this.gatherer = gatherer;
	}
	public Date getGatherDate() {
		return gatherDate;
	}
	public void setGatherDate(Date gatherDate) {
		this.gatherDate = gatherDate;
	}
	public double getCordBlood() {
		return cordBlood;
	}
	public void setCordBlood(double cordBlood) {
		this.cordBlood = cordBlood;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	
}
