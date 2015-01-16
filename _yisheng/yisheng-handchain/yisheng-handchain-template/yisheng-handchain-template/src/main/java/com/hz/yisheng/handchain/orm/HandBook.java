package com.hz.yisheng.handchain.orm;

import java.io.Serializable;
import java.util.Date;

import org.javafans.orm.entity.base.BaseAdminEntity;

public class HandBook extends BaseAdminEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 677427459455764876L;

	/* 标题  */
	private String title;
	
	/*副标题*/
	private String subtitle;
	
	/*摘要*/
	private String summary;
	
	/*内容*/
	private String content;
	
	//创建时间
	private Date createTime;
	
	//创建人
	private Long creator; 
	
	//修改人
	private Long updater; 
	
	//修改时间
	private Date updateTime; 
	
	//是否删除，1代表存在，0代表删除
	private Integer isDeleted;
	
	//用于查询的开始时间
	private String startTime;
	
	//用于查询的结束时间
	private String endTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
