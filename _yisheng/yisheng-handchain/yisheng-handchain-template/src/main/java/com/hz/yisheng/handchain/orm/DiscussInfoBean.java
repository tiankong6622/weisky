package com.hz.yisheng.handchain.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;
public class DiscussInfoBean extends BaseAdminEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 179775494704088291L;
	//朋友圈id
	private Long  friendId;
	//内容
	private String content;
	//标识
	private Integer mark;
	//评论人
	private String username;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getFriendId() {
		return friendId;
	}
	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
