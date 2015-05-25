package com.hz.crf.model.orm;

import java.math.BigDecimal;

import org.itboys.mongodb.entity.BaseLongIdEntity;

import com.google.code.morphia.annotations.Entity;
import com.hz.crf.model.CrowdConstants;

/**
 * 订单
 * @author weisky
 *
 * Jun 1, 2015
 */
@Entity(value = "Order", noClassnameStored = true)
public class Order extends BaseLongIdEntity{

	private static final long serialVersionUID = -4076415877567686430L;
	
	private Long memberId;//会员ID
	private Long productId;//项目ID
	private String productName;//项目名称
	private String byName;//购买人姓名
	private String supperName;//支持的名称  （比如演出门票）
	private int num;//购买的数量
	private BigDecimal price;//单价
	private BigDecimal totalPrice;//总价
	private String receName;//收货人姓名
	private String mobi;//收货人手机号
	private String address;//收货地址
	private int payType = CrowdConstants.Order.PAY_TYPE_ALIPAY;//支付方式  默认支付宝支付
	private int orderType = CrowdConstants.Order.BUILD_SUCCESS;//订单状态  默认创建订单成功，等待支付
	
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getByName() {
		return byName;
	}
	public void setByName(String byName) {
		this.byName = byName;
	}
	public String getSupperName() {
		return supperName;
	}
	public void setSupperName(String supperName) {
		this.supperName = supperName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getReceName() {
		return receName;
	}
	public void setReceName(String receName) {
		this.receName = receName;
	}
	public String getMobi() {
		return mobi;
	}
	public void setMobi(String mobi) {
		this.mobi = mobi;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	
}
