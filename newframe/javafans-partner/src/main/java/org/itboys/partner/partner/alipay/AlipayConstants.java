package org.itboys.partner.partner.alipay;

/**
 * 支付宝相关常量
 * @author ChenJunhui
 */
public interface AlipayConstants {
	
	/**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do";
    
    /**
     * 支付宝通知验证地址
    */
    public static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";
    
	/**
	 * 系统默认编码 我们系统都有UTF-8的 如果将来真的有其他的 则做成动态配置编码
	 */
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 支付宝签名方式 虽然支付宝提供 RSA DSA MD5 我们统一用MD5好了
	 */
	public static final String SIGN_TYPE = "MD5";
    
    /**
     * 即时到账交易接口
     */
    public static final String SERVICE_DIRECT = "create_direct_pay_by_user";
    /**
     * 支付方式走网银
     */
    public static final String PAY_METHOD_BANKPAY="bankPay";
    
    /**
     * 支付类型-商品购买
     */
    public static final String ALIPAY_PAYMENT_TYPE = "1";
    
	public static final String ALIPAY_TRUE = "T";
	
	public static final String ALIPAY_FALSE= "F";
	
	public static final String CHARACTER_EQ = "=";
	
	public static final String CHARACTER_AND = "&";
}
