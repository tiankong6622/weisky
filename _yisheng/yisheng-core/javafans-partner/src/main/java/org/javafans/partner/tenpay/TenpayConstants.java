package org.javafans.partner.tenpay;

import java.math.BigDecimal;

import org.javafans.common.constants.CommonConstants;

/**
 * 财付通常量
 * @author ChenJunhui
 *
 */
public interface TenpayConstants {

	/**
	 * 财付通请求网关
	 */
	public static String TENPAY_GATEWAY= "https://gw.tenpay.com/gateway/pay.htm";
	
	/**
	 * 财付通 校验URL
	 */
	public static String TENPAY_VERFIY_URL = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
	
	/**
	 * 财付通要穿分钱 所以乘以100
	 */
	public static BigDecimal YUAN_DIV_FEN = new BigDecimal(100);
	
	/**
	 * 银行类型 默认0 代表财付通
	 */
	public static String  DEFAULT_BANK_TYPE = "0";
	
	/**
	 * 币种 默认 人民币
	 */
	public static String DEFAULT_FEE_TYPE = "1";
	
	/**
	 * 默认签名方式
	 */
	public static String DEFAULT_SIGN_TYPE = "MD5";
	
	/**
	 * 默认编码
	 */
	public static String DEFAULT_ENCODE = CommonConstants.ENCODE.UTF_8;
}
