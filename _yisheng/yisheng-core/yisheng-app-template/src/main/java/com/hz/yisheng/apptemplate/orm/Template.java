package com.hz.yisheng.apptemplate.orm;

import java.io.Serializable;
/**
 * 模块
 * @author loard
 *
 */
public class Template implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4061550033988153336L;
	/**模块编号*/
	private Long id;
	/**模块名称*/
	private String templateName;
	/**模块android图*/
	private String androidImage;
	/**模块android图路径*/
	private String apath;
	/**模块android大图*/
	private String androidImageB;
	/**模块android大图路径*/
	private String abpath;
	/**模块ios图*/
	private String iosImage;
	/**模块ios图路径*/
	private String ipath;
	/**模块ios大图*/
	private String iosImageB;
	/**模块ios大图路径*/
	private String ibpath;
	/**接口地址*/
	private String implAddress;
	/**备注*/
	private String remark;
	/**排序*/
	private Integer sort;
	/**对应的应用*/
	private Long appId;
	/**对应id*/
	private Long iid;
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getAndroidImage() {
		return androidImage;
	}
	public void setAndroidImage(String androidImage) {
		this.androidImage = androidImage;
	}
	public String getAndroidImageB() {
		return androidImageB;
	}
	public void setAndroidImageB(String androidImageB) {
		this.androidImageB = androidImageB;
	}
	public String getIosImage() {
		return iosImage;
	}
	public void setIosImage(String iosImage) {
		this.iosImage = iosImage;
	}
	public String getIosImageB() {
		return iosImageB;
	}
	public void setIosImageB(String iosImageB) {
		this.iosImageB = iosImageB;
	}
	public String getImplAddress() {
		return implAddress;
	}
	public void setImplAddress(String implAddress) {
		this.implAddress = implAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getApath() {
		return apath;
	}
	public void setApath(String apath) {
		this.apath = apath;
	}
	public String getAbpath() {
		return abpath;
	}
	public void setAbpath(String abpath) {
		this.abpath = abpath;
	}
	public String getIpath() {
		return ipath;
	}
	public void setIpath(String ipath) {
		this.ipath = ipath;
	}
	public String getIbpath() {
		return ibpath;
	}
	public void setIbpath(String ibpath) {
		this.ibpath = ibpath;
	}
	public Long getIid() {
		return iid;
	}
	public void setIid(Long iid) {
		this.iid = iid;
	}

}
