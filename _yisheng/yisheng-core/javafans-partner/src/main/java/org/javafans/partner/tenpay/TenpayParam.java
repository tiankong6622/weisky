package org.javafans.partner.tenpay;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 财付通支付请求参数
 * @author ChenJunhui
 */
public class TenpayParam {
	
	/**
	 * 提供给用户基本的静态创建财付通请求参数的方法 
	 * @param orderId
	 * @param subject
	 * @param totalFee
	 * @param bankType
	 *	 0	财付通
		1001	招商银行借记卡(不支持信用卡，信用卡请用1038)
		1002	中国工商银行
		1003	中国建设银行
		1004	上海浦东发展银行
		1005	中国农业银行
		1006	中国民生银行
		1008	深圳发展银行
		1009	兴业银行
		1010	平安银行
		1020	交通银行
		1021	中信银行
		1022	中国光大银行
		1024	上海银行
		1025	华夏银行
		1027	广东发展银行
		1028	中国邮政储蓄银行（仅支持广东地区）
		1038	招商银行信用卡，招行限额499元
		1032	北京银行
		1033	网汇通
		1052	中国银行
		8001	财付通余额支付

	 * @return
	 */
	public static TenpayParam builderTenpayParam(String orderId,String subject,BigDecimal totalFee,String bankType){
		TenpayParam tenpayParam = new TenpayParam();
		tenpayParam.setOrderId(orderId);
		tenpayParam.setSubject(subject);
		tenpayParam.setTotalFee(totalFee);
		tenpayParam.setBankType(bankType);
		return tenpayParam;
	} 

	/**
	 * 外部订单ID  对应支付宝财付通参数 out_trade_no  必填 
	 */
	private String orderId;
	/**
	 * 订单名称 或 订单商品名称 256 字节  客户端代码不用管长度 这边会处理的  最大 对应支付宝参数 subject  必填 
	 */
	private String subject;
	
	/**
	 * 订单总金额    不可空
	 */
	private BigDecimal totalFee;
	
	/**
	 * 银行类型  直接走财付通的话 传 default
	 */
	private String bankType;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
