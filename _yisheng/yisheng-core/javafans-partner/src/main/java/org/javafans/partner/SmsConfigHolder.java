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
public class SmsConfigHolder {

	private static final String SMS_URL = "smsurl";
	private static final String SMS_UID = "smsuid";
	private static final String SMS_PWD = "smspwd";
	private static final String SMS_SND = "smssnd";
	private static final String SMS_DOMAIN = "smsdomain";
	
	private static Map<String,String> configMap;
	
	static{
		initConfigMap();
	}
	
	
	public static String getSmsUrl() {
		return configMap.get(SMS_URL);
	}


	public static String getSmsUid() {
		return configMap.get(SMS_UID);
	}


	public static String getSmsPwd() {
		return configMap.get(SMS_PWD);
	}


	public static String getSmsSnd() {
		return configMap.get(SMS_SND);
	}


	public static String getSmsDomain() {
		
		return configMap.get(SMS_DOMAIN);
	}


	private static void initConfigMap(){
		Document doc = XmlUtils.getDocumentFromClassPath("sms-config.xml");
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
