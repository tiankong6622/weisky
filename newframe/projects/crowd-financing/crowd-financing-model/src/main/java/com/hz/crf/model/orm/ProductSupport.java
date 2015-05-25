package com.hz.crf.model.orm;

import java.math.BigDecimal;
import java.util.Date;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 众筹项目的支持/回报档次相关
 * 
 * @author weisky
 *
 * May 28, 2015
 */
@Entity(value = "ProductSupport", noClassnameStored = true)
public class ProductSupport extends BaseLongIdEntity{

	private static final long serialVersionUID = -6586947443773533805L;
	
	private Long productId;//众筹项目ID
	private BigDecimal support;//支持的金额
	private Date reportDate;//预计的回报时间
	private String message;//回报信息
	private int supportNum;//已支持的数量
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getSupport() {
		return support;
	}
	public void setSupport(BigDecimal support) {
		this.support = support;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getSupportNum() {
		return supportNum;
	}
	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}
	
}
