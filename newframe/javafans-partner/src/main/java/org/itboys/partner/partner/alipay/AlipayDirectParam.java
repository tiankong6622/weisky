package org.itboys.partner.partner.alipay;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.itboys.commons.utils.string.CommonStringUtils;

/**
 * 支付宝即时到账请求参数
 * @author ChenJunhui
 *
 */
public class AlipayDirectParam {
	
	/**
	 * 外部订单ID  对应支付宝参数 out_trade_no  必填 
	 */
	private String orderId;
	/**
	 * 订单名称 或 订单商品名称 256 字节  客户端代码不用管长度 这边会处理的  最大 对应支付宝参数 subject  必填 
	 */
	private String subject;
	
	/**
	 * 支付类型 默认为1 商品购买 对应支付宝参数 payment_type
	 */
	private String paymentType = AlipayConstants.ALIPAY_PAYMENT_TYPE;
	
	/**
	 * 订单总金额    不可空
	 */
	private BigDecimal totalFee;
	
	/**
	 * 买家支付宝账号 对应支付宝  参数 buyer_email 邮箱或手机格式
	 */
	private String buyerEmail;
	
	/**
	 * 支付宝快捷登入30分钟内的传token 支付宝账号快捷登入后方可使用  可以不填
	 */
	private String token;
	
	/**
	 * 支付宝走网银支付的时候填这个字段
	 */
	private String defaultbank;
	/**
	 * 支付方式
	 */
	private String paymethod;
	
	
	/*********其他的先不管 接入网银以后再扩展**********/
	
	/**
	 * 提供带参数的 静态创建AlipayDirectParam对象函数 减少客户端代码 很多set方法
	 * @param orderId
	 * @param subject
	 * @param totalFee
	 * @return
	 */
	public static AlipayDirectParam getAlipayDirectParam(String orderId,String subject,BigDecimal totalFee){
		AlipayDirectParam alipayDirectParam = new AlipayDirectParam();
		alipayDirectParam.setOrderId(orderId);
		alipayDirectParam.setSubject(CommonStringUtils.catAndLeftByBytes(subject, 256));
		alipayDirectParam.setTotalFee(totalFee);
		return alipayDirectParam;
	}
	

	/**
	 * 提供带参数的 静态创建AlipayDirectParam对象函数 减少客户端代码 很多set方法
	 * @param orderId
	 * @param subject
	 * @param totalFee
	 * @return
	 */
	public static AlipayDirectParam getAlipayDirectParam(String orderId,String subject,String buyerEmail,BigDecimal totalFee){
		AlipayDirectParam alipayDirectParam = AlipayDirectParam.getAlipayDirectParam(orderId, subject, totalFee);
		alipayDirectParam.setBuyerEmail(buyerEmail);
		return alipayDirectParam;
	}
	

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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getDefaultbank() {
		return defaultbank;
	}


	public void setDefaultbank(String defaultbank) {
		this.defaultbank = defaultbank;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}


	public String getPaymethod() {
		return paymethod;
	}


	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

}
