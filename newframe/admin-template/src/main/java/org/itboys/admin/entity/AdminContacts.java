package org.itboys.admin.entity;

import java.io.UnsupportedEncodingException;

import com.google.code.morphia.annotations.Entity;


/**
 *通讯录实体类
 *@author huml 
 */
@Entity(value = "AdminContacts", noClassnameStored = true)
public class AdminContacts extends BaseAdminEntity{
	private static final long serialVersionUID = -5442847087468257466L;
	private Long orgId;//部门id
	private String name;//姓名
	private Integer sex;//性别
	private Long postId;//职务id
	private String tel;
	private String mobile;
	private String qq;
	private String weixin;
	private String remark;
	private Integer isDeleted = 0;
	private Long projectId;
	private String fied1;
	
	
	private String org;
	private String post;
	
	
	public String getFied1() {
		return fied1;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getName() {
		return name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public void setFied1(String fied1) {
		this.fied1 = fied1;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
