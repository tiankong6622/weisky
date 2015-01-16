package com.hz.yisheng.apptemplate.dto;

import java.io.Serializable;
/**
 * 关联表
 * @author loard
 *
 */
public class Relevance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4022424752434934263L;
	/**应用id*/
	private Long id;
	/**应用名称*/
	private String name;
	/**接口地址*/
	private String url;
	/**用于jQuery easyui中treegird的父子级别表示*/
	private Long _parentId = 0L;
	
	private Integer type;//1为应用，2为功能
	
	private Integer tempId;
	
	public Long get_parentId() {
		return _parentId;
	}
	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getTempId() {
		return tempId;
	}
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	

}
