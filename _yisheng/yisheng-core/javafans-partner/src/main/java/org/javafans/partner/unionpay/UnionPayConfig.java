package org.javafans.partner.unionpay;

import java.util.Map;

import org.javafans.common.utils.xml.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Maps;

/**
 * 银联动态参数配置
 * 确定有这个配置文件 union-bank.xml  在classes路径下 配置内容大概如下 变量你们自己去改
 * <?xml version="1.0" encoding="UTF-8"?>
	<!--  银联相关参数配置表  -->
	<configs>
		<!-- 前台交易生产环境 -->
		<config key="UPOP_BASE_URL">https://unionpaysecure.com/api/</config>
		<!-- 后台交易生产环境  -->
		<config key="UPOP_BSPAY_BASE_URL">https://besvr.unionpaysecure.com/api/</config>
		<!-- 后台交易生产环境  -->
		<config key="UPOP_QUERY_BASE_URL">https://query.unionpaysecure.com/api/</config>
		<!-- 商户代码  -->
		<config key="merCode">103330155330002</config>
		<!-- 银联支付成功同步回调地址  -->
		<config key="merFrontEndUrl">http://www.brand4x4.com/order/unionPayReturn</config>
		<!-- 银联支付成功异步通知地址  -->
		<config key="merFrontEndUrl">http://www.brand4x4.com/order/unionPayNotify</config>
		<!-- 商户密匙，需要和银联商户网站上配置的一样  -->
		<config key="securityKey">88888888</config>
		<!-- 商户名称  -->
	<config key="merName">富阳铁甲汽车用品有限公司</config>
</configs>

 * @author ChenJunhui
 *
 */
public class UnionPayConfig {

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
		Document doc = XmlUtils.getDocumentFromClassPath("union-bank.xml");
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
