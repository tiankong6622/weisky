package org.javafans.resources;

import java.util.Map;

import org.javafans.resources.ResourceUtils;

import com.google.common.collect.ImmutableMap;

/**
 * 获取kv资源配置信息
 * @author ChenJunHui
 */
public class ResourceConfig {

	private static Map<String,String> sysResourceMap;
	
	static{
		//不变map 其他使用者不可修改
		sysResourceMap = ImmutableMap.copyOf(ResourceUtils.getKVResource("resource-config.xml")) ;
	}
	
	/**
	 * 获取全局资源map
	 * @return
	 */
	public static Map<String,String> getSysResourceMap(){
		return sysResourceMap;
	}
	
	/**
	 * 获得系统资源配置值
	 * @param key
	 * @return
	 */
	public static String getSysConfig(String key){
		return sysResourceMap.get(key);
	}
	
}
