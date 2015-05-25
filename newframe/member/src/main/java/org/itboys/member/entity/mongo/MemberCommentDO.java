package org.itboys.member.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;

@Entity(value = "memberCommentDO", noClassnameStored = true)
public class MemberCommentDO extends MemberBaseEntity{

	private static final long serialVersionUID = -6006813331291319691L;
	private String title;//评论标题
	private String content;//评论内容
	private String image;//评论图片
	private Date comTime;//评论时间
	private String userName;//评论者用户名
	private String logo;//用户头像
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getComTime() {
		return comTime;
	}
	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
	
}
