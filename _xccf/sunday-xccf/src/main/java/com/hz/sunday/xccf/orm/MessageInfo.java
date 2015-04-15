package com.hz.sunday.xccf.orm;

import java.util.Date;

import org.javafans.orm.entity.base.BaseAdminEntity;
/**
 * 基本信息
 * @author WeiSky
 *
 */
public class MessageInfo extends BaseAdminEntity{

	private static final long serialVersionUID = -5429887823481501056L;
	private String title;//标题
	private String subhead;//副标题
	private String summary;//摘要
	private String comment;//内容
	private int mtype;//信息类型  0:其他 1：新闻  2：通知公告 3：大赛介绍 4：参赛指南  5：联系我们  6:前端首页轮播的图片
	private String filePath;//文件路径
	private String reource;//来源
	private Date actionTime;//活动时间
	private String actionTime2;//活动时间2
	private String actionAddr;//活动地址
	
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
	
}
