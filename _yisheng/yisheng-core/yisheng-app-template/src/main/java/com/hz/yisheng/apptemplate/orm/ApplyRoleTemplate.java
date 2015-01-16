package com.hz.yisheng.apptemplate.orm;

import java.io.Serializable;

public class ApplyRoleTemplate implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6531810453977837644L;
	/**id*/
	private Long id;
	/**角色id*/
	private Long roleId;
	/**模块id*/
	private Long templateId;
	/**多个templateId拼装的字符串 以，号隔开*/
	private String templateIdStr;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public String getTemplateIdStr() {
		return templateIdStr;
	}
	public void setTemplateIdStr(String templateIdStr) {
		this.templateIdStr = templateIdStr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean equals(Object other){       
		  
		  if(this == other)                                  
		   return true;
		  if(other == null)         
		   return false;
		  if( !(other instanceof ApplyRoleTemplate))
		   return false;
		  
		  final ApplyRoleTemplate cat = (ApplyRoleTemplate)other;
		  
		  if( !getRoleId().equals(cat.getRoleId()))
		   return false;
		  if( !getTemplateId().equals(cat.getTemplateId()))
		   return false;
		  return true;
		 }
	 public int hashCode(){            
		  int result = getRoleId().hashCode();
		  result = 29 * result +getTemplateId().hashCode();
		  return result;
		 }
	

}
