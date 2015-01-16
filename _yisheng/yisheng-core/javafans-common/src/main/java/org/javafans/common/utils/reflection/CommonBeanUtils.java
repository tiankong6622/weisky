package org.javafans.common.utils.reflection;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * bean 熟悉 反射相关封装
 * @author ChenJunhui
 */
public abstract class CommonBeanUtils {

	/**
	 * 把 obj2选定的 properties 熟悉 copy到 obj1 里
	 * @author ChenJunhui
	 * @param obj1
	 * @param obj2
	 * @param properties
	 */
	public static void copyProperties(Object obj1,Object obj2,String... properties){
		if(properties!=null && properties.length>0 && obj1!=null && obj2!=null){
			for(String propertie:properties){
				try{
					Object filedValue = PropertyUtils.getProperty(obj2, propertie);
					PropertyUtils.setProperty(obj1, propertie, filedValue);
				}catch(Exception e){
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	
}
