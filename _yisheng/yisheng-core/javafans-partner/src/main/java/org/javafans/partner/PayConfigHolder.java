package org.javafans.partner;

import java.util.Map;

import org.javafans.common.utils.xml.XmlUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Maps;

/**
 * 支付相关一些配置信息
 * @author ChenJunhui
 */
public class PayConfigHolder {

	private static final String ALIPAY_KEY = "alipayKey";
	private static final String ALIPAY_PARTNER = "alipayPartner";
	private static final String ALIPAY_NOTIFY_URL = "alipayReturnUrl";
	private static final String ALIPAY_RETURN_URL = "alipayReturnUrl";
	private static final String ALIPAY_SELLER_EMAIL = "alipaySellerEmail";
	private static final String TENPAY_PARTNER = "tenpayPartner";
	private static final String TENPAY_KEY = "tenpayKey";
	private static final String TENPAY_RETURN_URL = "tenpayReturnUrl";
	private static final String TENPAY_NOTIFY_URL = "tenpayNotifyUrl";
	
	private static Map<String,String> configMap;
	
	static{
		initConfigMap();
	}
	
	/**
	 * 获取支付宝的 key
	 * @return
	 */
	public static String getAlipayKey(){
		return configMap.get(ALIPAY_KEY);
	}
	
	/**
	 * 获取支付宝 partner id
	 * @return
	 */
	public static String getAlipayPartner(){
		return configMap.get(ALIPAY_PARTNER);
	}
	
	/**
	 * 获取支付宝请求异步URL
	 * @return
	 */
	public static String getAlipayNotifyUrl(){
		return configMap.get(ALIPAY_NOTIFY_URL);
	}
	
	/**
	 * 获取支付宝同步请求URL
	 * @return
	 */
	public static String getAlipayReturnUrl(){
		return configMap.get(ALIPAY_RETURN_URL);
	}
	
	/**
	 * 获取支付宝卖家的账号
	 * @return
	 */
	public static String getAlipaySellerEmail(){
		return configMap.get(ALIPAY_SELLER_EMAIL);
	}
	
	/**
	 * 手机H5支付同步回调URL
	 * @return
	 */
	public static String getAlipayWapCallbackUrl(){
		return configMap.get("wapCallbackUrl");
	}
	
	/**
	 * 手机H5支付异步回调URL  
	 * @return
	 */
	public static String getAlipayWapNotifyUrl(){
		return configMap.get("wapNotifyUrl");
	}
	
	/**
	 * 手机H5中断回调URL
	 * @return
	 */
	public static String getAlipayWapMerchantUrl(){
		return configMap.get("wapMerchantUrl");
	}
	
	/**
	 * 手机版本
	 * @return
	 */
	public static String getAlipayWapVersion(){
		return configMap.get("wapVersion");
	}
	
	/**
	 * 获取财付通 partner id
	 * @return
	 */
	public static String getTenpayPartner(){
		return configMap.get(TENPAY_PARTNER);
	}

	/**
	 * 获取财付通 密钥
	 * @return
	 */
	public static String getTenpayKey(){
		return configMap.get(TENPAY_KEY);
	}
	
	/**
	 * 财付通同步请求URL
	 * @return
	 */
	public static String getTenpayReturnUrl(){
		return configMap.get(TENPAY_RETURN_URL);
	}
	
	/**
	 * 财付通异步请求URL
	 * @return
	 */
	public static String getTenpayNotifyUrl(){
		return configMap.get(TENPAY_NOTIFY_URL);
	}
	
	private static void initConfigMap(){
		Document doc = XmlUtils.getDocumentFromClassPath("pay-config.xml");
		NodeList configNodeList=doc.getElementsByTagName("config");
		configMap = Maps.newHashMapWithExpectedSize(configNodeList.getLength());
		for(int i=0,size=configNodeList.getLength();i<size;i++){
			Node node = configNodeList.item(i);
			String key = node.getAttributes().getNamedItem("key").getNodeValue();
			String value = node.getTextContent();
			configMap.put(key, value);
		}
	}
}
