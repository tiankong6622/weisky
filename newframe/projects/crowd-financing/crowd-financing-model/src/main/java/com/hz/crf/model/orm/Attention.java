package com.hz.crf.model.orm;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 会员关注的项目
 * 
 * @author weisky
 *
 * Jun 1, 2015
 */
@Entity(value = "Attention", noClassnameStored = true)
public class Attention extends BaseLongIdEntity{

	private static final long serialVersionUID = -6528200288502829373L;
	
	private Long memberId;//会员ID
	private Long productId;//项目ID
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
