package org.itboys.member.entity.mongo;

import java.util.Date;

import org.itboys.mongodb.entity.BaseEntity;

import com.google.code.morphia.annotations.Entity;
/**
 *会员评论信息
 * @author huml
 *
 */
@Entity(value = "memberComment", noClassnameStored = true)
public class MemberComment extends MemberBaseEntity{
	private static final long serialVersionUID = 115646516486516L;
	
	private Long memberId;//会员ID主键
	private Long objectId;//评论对象ID
	private String type;//被评论对象类型 比如 商品 文章 等等(1.商品，2.文章   其他再定)
	private String title;//评论标题
	private String content;//评论内容
	private String image;//评论图片
	private Date comTime;//评论时间
	private Long projectId;//项目id
	private String score;//分数
	private String checker;//审核人
	private Date checkTime;//审核时间
	private Integer status;//状态（0.已删除 1.未审核 2.审核通过 ）
	//临时数据
	private String memberName;//会员名称
	private String nick;//会员昵称
	private String logo;//会员头像
	
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	  
	  
	  
	  
}
