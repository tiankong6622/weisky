package com.hz.sunday.cyds.orm;

import java.util.Date;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 新闻、通知公告、相关资料表
 * @author WeiSky
 *
 */
public class MessageNotice extends BaseAdminEntity{

	private static final long serialVersionUID = -3818276686987597959L;
	
	private String title;//标题
	private String subhead;//副标题
	private String summary;//摘要
	private String comment;//内容
	private int mtype;//信息类型  0:其他 1：新闻  2：通知公告 3：大赛介绍 4：参赛条件  5：联系我们  6:前端首页轮播的图片 7:报名渠道及方式  8：赛程安排  9：政策支持  10：参赛资料下载
	private String filePath;//文件路径
	private String reource;//来源
	private Date actionTime;//活动时间
	private String actionAddr;//活动地址
	
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	public String getActionAddr() {
		return actionAddr;
	}
	public void setActionAddr(String actionAddr) {
		this.actionAddr = actionAddr;
	}
	public String getReource() {
		return reource;
	}
	public void setReource(String reource) {
		this.reource = reource;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
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
	
}
