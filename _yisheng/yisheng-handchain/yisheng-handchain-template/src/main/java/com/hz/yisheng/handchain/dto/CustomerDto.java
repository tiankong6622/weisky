package com.hz.yisheng.handchain.dto;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

public class CustomerDto {
	private Long id;       
	private String name;  //姓名
	private int sex;  //性别 1:男， 2：女
	private String phone;  //电话
	private String address;  //地址
	private double weight;  //重量
	private Date bornTime;  //出生时间
	private Long parentId; //父类ID
	private Long _parentId; //父类ID
	private String deviceNumber;//设备编号
	private String deviceNumberChild;//设备编号
	private String bloodType; //血型
	private Long bloodPre; //血压
	private Long heartRate; //心率
	private Long temperature; //体温
	private Long icterus;//黄疸
	
	private double newWeight;  //重量
	private Long newBloodPre; //血压
	private Long newHeartRate; //心率
	private Long newTemperature; //体温
	private Long newIcterus;//黄疸
	
	private String password; //密码
	private Date inHospitalTime;  //住院时间
	private Date outHospitalTime; //出院时间
	private String number; //住院编号
	private String bedNumber; //床号
	private int age;  //年龄
	private String type; //类型
	private String leve; //等级
	private Date createTime;  //近期时间
	private Long customerId; //家长id
	
	private List<CustomerDto> children=Lists.newArrayList();

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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<CustomerDto> getChildren() {
		return children;
	}
	public void setChildren(List<CustomerDto> children) {
		this.children = children;
	}
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public Long getBloodPre() {
		return bloodPre;
	}
	public void setBloodPre(Long bloodPre) {
		this.bloodPre = bloodPre;
	}
	public Long getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(Long heartRate) {
		this.heartRate = heartRate;
	}
	public Long getTemperature() {
		return temperature;
	}
	public void setTemperature(Long temperature) {
		this.temperature = temperature;
	}
	public Long getIcterus() {
		return icterus;
	}
	public void setIcterus(Long icterus) {
		this.icterus = icterus;
	}
	public double getNewweight() {
		return newWeight;
	}
	public void setNewweight(double newWeight) {
		this.newWeight = newWeight;
	}
	public Long getNewBloodPre() {
		return newBloodPre;
	}
	public void setNewBloodPre(Long newBloodPre) {
		this.newBloodPre = newBloodPre;
	}
	public Long getNewHeartRate() {
		return newHeartRate;
	}
	public void setNewHeartRate(Long newHeartRate) {
		this.newHeartRate = newHeartRate;
	}
	public Long getNewTemperature() {
		return newTemperature;
	}
	public void setNewTemperature(Long newTemperature) {
		this.newTemperature = newTemperature;
	}
	public Long getNewIcterus() {
		return newIcterus;
	}
	public void setNewIcterus(Long newIcterus) {
		this.newIcterus = newIcterus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBornTime() {
		return bornTime;
	}
	public void setBornTime(Date bornTime) {
		this.bornTime = bornTime;
	}
	public String getDeviceNumberChild() {
		return deviceNumberChild;
	}
	public void setDeviceNumberChild(String deviceNumberChild) {
		this.deviceNumberChild = deviceNumberChild;
	}
	public double getNewWeight() {
		return newWeight;
	}
	public void setNewWeight(double newWeight) {
		this.newWeight = newWeight;
	}
	public Date getInHospitalTime() {
		return inHospitalTime;
	}
	public void setInHospitalTime(Date inHospitalTime) {
		this.inHospitalTime = inHospitalTime;
	}
	public Date getOutHospitalTime() {
		return outHospitalTime;
	}
	public void setOutHospitalTime(Date outHospitalTime) {
		this.outHospitalTime = outHospitalTime;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLeve() {
		return leve;
	}
	public void setLeve(String leve) {
		this.leve = leve;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
