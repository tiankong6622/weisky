package com.hz.crf.model.orm;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 支持众筹项目的详情
 * @author weisky
 *
 * May 28, 2015
 */
@Entity(value = "ProductSupportDetail", noClassnameStored = true)
public class ProductSupportDetail extends BaseLongIdEntity{

	private static final long serialVersionUID = -2259772608719586323L;
	
	private Long productId;//项目ID
	private Long memeberId;//会员ID
	private Long productSupportID;//支持的档次ID
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getMemeberId() {
		return memeberId;
	}
	public void setMemeberId(Long memeberId) {
		this.memeberId = memeberId;
	}
	public Long getProductSupportID() {
		return productSupportID;
	}
	public void setProductSupportID(Long productSupportID) {
		this.productSupportID = productSupportID;
	}
	
}
