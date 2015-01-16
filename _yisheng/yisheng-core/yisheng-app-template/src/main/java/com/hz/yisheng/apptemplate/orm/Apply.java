package com.hz.yisheng.apptemplate.orm;

import java.io.Serializable;
import java.util.List;
/**
 * 应用
 * @author loard
 *
 */
public class Apply implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4625303957859187154L;
	/**应用编号*/
	private Long id;
	/**应用名称*/
	private String appName;
	/**应用图片*/
	private String appImage;
	/**联系人*/
	private String contacts;
	/**联系电话*/
	private String telephone;
	/**二维码地址*/
	private String tdcAddress;
	/**应用介绍*/
	private String appSummy;
	/**文件的路劲*/
	private String path;
	/**模块列表**/
	private List<Template> template;
	/**应用标识*/
	private String appcode;
	public List<Template> getTemplate() {
		return template;
	}
	public void setTemplate(List<Template> template) {
		this.template = template;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppImage() {
		return appImage;
	}
	public void setAppImage(String appImage) {
		this.appImage = appImage;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getTdcAddress() {
		return tdcAddress;
	}
	public void setTdcAddress(String tdcAddress) {
		this.tdcAddress = tdcAddress;
	}
	public String getAppSummy() {
		return appSummy;
	}
	public void setAppSummy(String appSummy) {
		this.appSummy = appSummy;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAppcode() {
		return appcode;
	}
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

}
