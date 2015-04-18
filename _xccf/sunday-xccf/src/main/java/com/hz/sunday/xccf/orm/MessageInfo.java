package com.hz.sunday.xccf.orm;

import java.util.Date;
import java.util.List;

import org.javafans.orm.entity.base.BaseAdminEntity;

import com.hz.yisheng.commondata.orm.Attachement;

/**
 * 基本信息
 * 
 * @author WeiSky
 *
 */
public class MessageInfo extends BaseAdminEntity {

	private static final long serialVersionUID = -5429887823481501056L;
	private String title;// 标题
	private String subhead;// 副标题
	private String summary;// 摘要
	private String comment;// 内容
	private int mtype;// 信息类型  1：论坛介绍  2：新闻中心 3：顶尖领袖团 4：拟邀请嘉宾  5：组织机构  6:论坛日程  7:合作媒体  8:总策划人  9:联系方式  10：轮番图片
	private String filePath;// 文件路径
	private String reource;// 来源
	private Date actionTime;// 活动时间
	private String actionTime2;// 活动时间2
	private String actionAddr;// 活动地址

	private Date startTime;// 起始时间
	private String schedule;// 日程时间
	private Integer menuType;// 消息类型
	private List<Attachement> attList;// 附件表信息

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getMtype() {
		return mtype;
	}

	public void setMtype(int mtype) {
		this.mtype = mtype;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getReource() {
		return reource;
	}

	public void setReource(String reource) {
		this.reource = reource;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public String getActionTime2() {
		return actionTime2;
	}

	public void setActionTime2(String actionTime2) {
		this.actionTime2 = actionTime2;
	}

	public String getActionAddr() {
		return actionAddr;
	}

	public void setActionAddr(String actionAddr) {
		this.actionAddr = actionAddr;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public List<Attachement> getAttList() {
		return attList;
	}

	public void setAttList(List<Attachement> attList) {
		this.attList = attList;
	}

}
