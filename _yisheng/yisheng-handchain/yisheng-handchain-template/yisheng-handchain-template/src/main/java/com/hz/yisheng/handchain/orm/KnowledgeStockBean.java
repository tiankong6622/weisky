package com.hz.yisheng.handchain.orm;


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
	
}
