package org.itboys.framework.resource;

import java.util.Map;

/**
 * 资源动态参数配置并提供方法读取 
 * 请在spring里做如下配置 
 * <bean id="resourceHolder" class="org.itboys.framework.resource.ResourceHolder" lazy-init="false">  
	  <property name="resourceMap">  
	      <map>  
	        <entry key="xxx" value="${xxx.xx.xx}"/> 
	        <entry key="xxx2" value="${xxx.xxx.xxx}"/>  
	      </map>  
	  </property>  
	</bean> 
 * @author ChenJunhui
 *
 */
public class ResourceHolder {

	private Map<String, Object> resourceMap;
	
	public Object getValue(String key){
		return resourceMap.get(key);
	}

	public String getStringValue(String key){
		return (String)resourceMap.get(key);
	}
	
	public int getIntValue(String key){
		return Integer.parseInt(resourceMap.get(key).toString());
	}
	
	public long getLongValue(String key){
		return  Long.parseLong(resourceMap.get(key).toString());
	}
	
	public boolean getBooleanValue(String key){
		return Boolean.valueOf(resourceMap.get(key).toString()) ;
	}
	
	public void setResourceMap(Map<String, Object> resourceMap) {
		this.resourceMap = resourceMap;
	}
}
