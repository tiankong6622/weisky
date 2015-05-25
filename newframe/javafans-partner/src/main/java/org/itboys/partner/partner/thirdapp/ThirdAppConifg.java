package org.itboys.partner.partner.thirdapp;

import java.util.Map;

import org.itboys.commons.utils.xml.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Maps;

/**
 *  三方应用配置
 * @author ChenJunhui
 *
 */
public class ThirdAppConifg {

	private static Map<String,String> configMap;
	

	public static String getValue(String key){
		return configMap.get(key);
	}
	

	/**
	 * 获取配置map 保护性copy
	 * @param key
	 * @return
	 */
	public static Map<String,String> getConfigMap(){
		Map<String,String> map = Maps.newHashMapWithExpectedSize(configMap.size());
		map.putAll(configMap);
		return map;
	}
	
	static{
		initConfigMap();
	}
	
	private static void initConfigMap(){
		Document doc = XmlUtils.getDocumentFromClassPath("third-config.xml");
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
