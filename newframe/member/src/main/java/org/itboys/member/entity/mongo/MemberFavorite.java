package org.itboys.member.entity.mongo;

import java.util.Date;

import com.google.code.morphia.annotations.Entity;

/**
 * 会员通用对象收藏夹
 * 
 * @author huml
 * 
 */
@Entity(value = "memberFavorite", noClassnameStored = true)
public class MemberFavorite extends MemberBaseEntity {

	private static final long serialVersionUID = 837605996041900878L;
	private Long memberId;
	private Long objectId;
	private Integer type;
	private Date collTime;
	
	public static Integer TYPE_PRODUCT = 1;  
	public static Integer TYPE_INFO = 2;
	public static Integer TYPE_SUPPINFO = 3;
	public static Integer TYPE_BLOG = 4;
	public static Integer TYPE_PURCHASER=5;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCollTime() {
		return collTime;
	}

	public void setCollTime(Date collTime) {
		this.collTime = collTime;
	}

}
