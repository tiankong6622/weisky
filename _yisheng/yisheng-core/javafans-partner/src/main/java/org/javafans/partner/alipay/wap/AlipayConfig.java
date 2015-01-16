package org.javafans.partner.alipay.wap;

import org.javafans.partner.PayConfigHolder;

public class AlipayConfig {

	public static String key=PayConfigHolder.getAlipayKey();//密钥
	 // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String private_key = "";
	public static String partner = PayConfigHolder.getAlipayPartner();
    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String ali_public_key = "";
	public static String sign_type_md5="MD5";
	public static String sign_type = "MD5";// 签名方式，选择项：0001(RSA)、MD5
	public static String format = "xml";//返回格式
	public static String input_charset = "utf-8";	// 字符编码格式 目前支持  utf-8
	//支付宝网关地址
	public static String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
	
	public static String sellerEmail=PayConfigHolder.getAlipaySellerEmail();//卖家邮箱或手机号 就是卖家的支付宝登入名
	public static String notifyUrl=PayConfigHolder.getAlipayWapNotifyUrl();//异步通知地址
	public static String callbackUrl=PayConfigHolder.getAlipayWapCallbackUrl();//同步回调地址
	public static String merchantUrl=PayConfigHolder.getAlipayWapMerchantUrl();//中断回调地址
	public static String v=PayConfigHolder.getAlipayWapVersion();//版本
	
	
}
