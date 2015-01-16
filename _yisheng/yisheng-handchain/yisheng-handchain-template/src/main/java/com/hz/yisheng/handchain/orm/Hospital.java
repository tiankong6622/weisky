package com.hz.yisheng.handchain.orm;

import java.util.Date;

import org.javafans.orm.entity.base.BaseAdminEntity;
/**
 * 医院表
 * @author loard
 *
 */
public class Hospital extends BaseAdminEntity{

	private static final long serialVersionUID = -4608443495206025392L;
	/**医院地址*/
	private String address;
	/**医院电话*/
	private String telephone;
	/**医院所在省份*/
	private String province;
	/**医院所在城市id*/
	private Long cityId;
	/**医院所在省份id*/
	private Long provinceId;
	/**医院所在地区id*/
	private Long districtId;
	/**医院名称*/
	private String hospitalName;
	/**对应hospitalId*/
	private Long value;
	/**对应hospitalName*/
	private String text;
	/**所在城市全称*/
	private String fullName;
	
	private String code; //医院编码
	private String hospitalUrl; //医院网址
	private String hospitalDes; //医院简介
	private Long creator; //创建人
	private Date createTime; //创建时间
	private Long updater; //修改人
	private Date updateTime; //修改时间
	private int isDeleted; //删除
	private String lat; //经度
	private String lng;  //纬度
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getHospitalUrl() {
		return hospitalUrl;
	}
	public void setHospitalUrl(String hospitalUrl) {
		this.hospitalUrl = hospitalUrl;
	}
	public String getHospitalDes() {
		return hospitalDes;
	}
	public void setHospitalDes(String hospitalDes) {
		this.hospitalDes = hospitalDes;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getUpdater() {
		return updater;
	}
	public void setUpdater(Long updater) {
		this.updater = updater;
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
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	

}
