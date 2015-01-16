package com.hz.yisheng.handchain.orm;


import java.util.Date;

import org.javafans.orm.entity.base.BaseAdminEntity;

public class KnowledgeStockBean extends BaseAdminEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1151179894169459970L;
	/**标题**/
	private String title;
	/**副标题**/
	private String subtitle;
	/**发病时间**/
	private String sicktime;
	/**摘要**/
	private String summary;
	/**疾病类型**/
	private String sicktype;
	/**内容**/
	private String content;
	private Long plateId; //板块id
	private Long creator; //创建人
	private Long updater; //修改人
	private Date createTime; // 创建时间
	private Date updateTime; //修改时间
	private int isDeleted; //删除标记
	
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
	public String getSicktime() {
		return sicktime;
	}
	public void setSicktime(String sicktime) {
		this.sicktime = sicktime;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSicktype() {
		return sicktype;
	}
	public void setSicktype(String sicktype) {
		this.sicktype = sicktype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getPlateId() {
		return plateId;
	}
	public void setPlateId(Long plateId) {
		this.plateId = plateId;
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
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
