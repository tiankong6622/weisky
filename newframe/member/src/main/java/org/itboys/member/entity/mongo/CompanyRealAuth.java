package org.itboys.member.entity.mongo;

import java.math.BigDecimal;
import java.util.Date;

import com.google.code.morphia.annotations.Entity;

/**
 * 企业会员的基本信息 默认不需要认证的 realAuthPass和authPassTime就忽略
 * @author huml
 *
 */
@Entity(value = "companyRealAuth", noClassnameStored = true)
public class CompanyRealAuth extends MemberBaseEntity {

	private static final long serialVersionUID = -7421055245376216424L;
	private Long memberId;
	private String businessLicence;//营业执照
	private String businessImg;
	private String companyName;
	private BigDecimal regFee;//注册资金
	private String scale;//公司规模 code表配置
	private Integer year;//成立年份
	private Date foundingTime;//公司成立时间
	private String site;//公司网址
	private Integer realAuthPass=0;//是否认证通过 1通过 0不通过
	private String alipayNo;//支付宝账号
	private String tencentNo;//财付通账号
	private Date authPassTime;
	
	private Integer provinceId;
	private Integer districtId;
	private Integer cityId;
	private String address;
	
	private String lng;//经度
	private String lat;//纬度
	
	private String linkman;
	private String businessMainScop;//主营业务 code表里配置
	private String businessScop;//经营范围 或公司类型 code表的code
	private String desc;//经营范围 或公司类型 code表的code
	
	private Integer moduletype;//信息模板类型 1,2.... 默认为1

	

	public Integer getModuletype() {
		return moduletype;
	}

	public void setModuletype(Integer moduletype) {
		this.moduletype = moduletype;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	

	public String getBusinessLicence() {
		return businessLicence;
	}

	public void setBusinessLicence(String businessLicence) {
		this.businessLicence = businessLicence;
	}

	public String getBusinessImg() {
		return businessImg;
	}

	public void setBusinessImg(String businessImg) {
		this.businessImg = businessImg;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public BigDecimal getRegFee() {
		return regFee;
	}

	public void setRegFee(BigDecimal regFee) {
		this.regFee = regFee;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Integer getRealAuthPass() {
		return realAuthPass;
	}

	public void setRealAuthPass(Integer realAuthPass) {
		this.realAuthPass = realAuthPass;
	}

	public String getAlipayNo() {
		return alipayNo;
	}

	public void setAlipayNo(String alipayNo) {
		this.alipayNo = alipayNo;
	}

	public String getTencentNo() {
		return tencentNo;
	}

	public void setTencentNo(String tencentNo) {
		this.tencentNo = tencentNo;
	}

	public Date getAuthPassTime() {
		return authPassTime;
	}

	public void setAuthPassTime(Date authPassTime) {
		this.authPassTime = authPassTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getBusinessScop() {
		return businessScop;
	}

	public void setBusinessScop(String businessScop) {
		this.businessScop = businessScop;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public Integer getYear() {
		return year;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getBusinessMainScop() {
		return businessMainScop;
	}

	public void setBusinessMainScop(String businessMainScop) {
		this.businessMainScop = businessMainScop;
	}

	public Date getFoundingTime() {
		return foundingTime;
	}

	public void setFoundingTime(Date foundingTime) {
		this.foundingTime = foundingTime;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
