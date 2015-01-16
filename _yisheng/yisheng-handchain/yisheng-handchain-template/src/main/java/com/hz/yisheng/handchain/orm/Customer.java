package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;

/**
 * 母婴基本信息
 * @author zfy
 *
 */
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1797307886133108849L;
	
	private Long id;       
	private String name;  //姓名
	private String password; //密码
	private int sex;  //性别 1:男， 2：女
	private String phone;  //电话
	private String address;  //地址
	private Date inHospitalTime;  //住院时间
	private Date outHospitalTime; //出院时间
	private Date bornTime; //出生时间
	private double weight;  //重量
	private String number; //住院编号
	private String bedNumber; //床号
	private int age;  //年龄
	private String father; //父亲
	private Long parentId; //父类ID
	private String type; //类型
	private String leve; //等级
	
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	private int isDeleted; //删除标记
	private String bloodType;  //血型
	private Long hospitalId;  //所住医院

	
	private String motherName;  //母亲名称
	private String customDeviceNum;  //家长设备编号
	private String childDeviceNum;  //孩子设备编号
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Date getBornTime() {
		return bornTime;
	}
	public void setBornTime(Date bornTime) {
		this.bornTime = bornTime;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
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
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getCustomDeviceNum() {
		return customDeviceNum;
	}
	public void setCustomDeviceNum(String customDeviceNum) {
		this.customDeviceNum = customDeviceNum;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public Long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getChildDeviceNum() {
		return childDeviceNum;
	}
	public void setChildDeviceNum(String childDeviceNum) {
		this.childDeviceNum = childDeviceNum;
	}
	
}
