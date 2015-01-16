package org.javafans.common.dto;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Option与其他对象属性置值操作
 * @author ChenJunhui
 *
 */
public class OptionUtils {

	private static Logger logger = LoggerFactory.getLogger(OptionUtils.class);
	
	/**
	 * 将 keyField等于 Option的key 的对象的 valueField置上 option的value值
	 */
	public  static void mergeProperties(Object obj,String keyField,String valueField,List<Option> options){
		try{
			if(options==null || options.isEmpty() || obj==null || StringUtils.isBlank(keyField) || StringUtils.isBlank(valueField)){
				return;
			}
			String keyValue = (String) PropertyUtils.getProperty(obj, keyField);
			if(StringUtils.isNotBlank(keyValue)){
				for(Option o:options){
					if(o!=null && keyValue.equals(o.getKey())){
						String value = o.getValue();
						if(StringUtils.isNotBlank(value)){
							PropertyUtils.setProperty(obj, valueField, value);
							return;
						}else{
							PropertyUtils.setProperty(obj, valueField, keyValue);
						}
					}
				}
			}
			PropertyUtils.setProperty(obj, valueField, keyValue);
		}catch(Exception e){
			logger.error("mergeProperties fail",e);
		}
	}
}
