package com.hz.crf.model.orm;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.hz.crf.model.CrowdConstants;
/**
 * 收货地址
 * @author weisky
 *
 * May 28, 2015
 */
public class Address extends BaseLongIdEntity{

	private static final long serialVersionUID = -919404825853199908L;
	
	private Long memberId;//会员的ID
	private String name;//收货人
	private String mobi;//手机号
	private Long provinceId;//省份ID
	private Long cityId;//市ID
	private Long districtId;//区ID
	private String titleAddre;//收货地址的省市区
	private String detailAddre;//详细地址
	private int defaultAddre = CrowdConstants.Address.DEFAULT_YES;// 默认地址    1：是   0：否
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobi() {
		return mobi;
	}
	public void setMobi(String mobi) {
		this.mobi = mobi;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getTitleAddre() {
		return titleAddre;
	}
	public void setTitleAddre(String titleAddre) {
		this.titleAddre = titleAddre;
	}
	public String getDetailAddre() {
		return detailAddre;
	}
	public void setDetailAddre(String detailAddre) {
		this.detailAddre = detailAddre;
	}
	public int getDefaultAddre() {
		return defaultAddre;
	}
	public void setDefaultAddre(int defaultAddre) {
		this.defaultAddre = defaultAddre;
	}
	
}
