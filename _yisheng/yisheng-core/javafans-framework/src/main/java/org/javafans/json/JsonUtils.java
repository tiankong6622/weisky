package org.javafans.json;

import org.javafans.common.utils.exception.Exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json相关
 * @author ChenJunhui
 *
 */
public class JsonUtils {

	public static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 对象转json
	 * @param obj
	 * @return
	 */
	public static String objectToString(Object obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}
	
	/**
	 * json转对象
	 * @param clazz
	 * @param json
	 * @return
	 */
	public static <T> T  jsonToObject(Class<T> clazz,String json){
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		} 
	}
}
