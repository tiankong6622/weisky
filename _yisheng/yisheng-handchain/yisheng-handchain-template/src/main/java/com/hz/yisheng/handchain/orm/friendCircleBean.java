package com.hz.yisheng.handchain.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

public class friendCircleBean extends BaseAdminEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5339219326353959424L;
	// 图片
	private String pic;
	// 标题
	private String title;
	// 链接
	private String link;
	// 内容
	private String content;
	// 删除
	private Integer isDeleted;
	// 评论总数
	private Long discussCount = 0l;
	// 点赞总数
	private Long praiseCount = 0l;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public Long getDiscussCount() {
		return discussCount;
	}

	public void setDiscussCount(Long discussCount) {
		this.discussCount = discussCount;
	}

	public Long getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Long praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
