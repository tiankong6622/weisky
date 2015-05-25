package org.itboys.commons.utils.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * java bean 转map 
 * @author ChenJunhui
 *
 */
public class BeanToMap {
	
	/**
	 * 组装反射字段信息 过多不同的bean调用 可能导致内存溢出 调用前评估下 或者调大perm区内存
	 */
	private static Map<String, List<Field>> cacheMap = new HashMap<String, List<Field>>();

	/**
	 * java bean 转map 
	 * @param t
	 * @return
	 */
	public static <T> Map<String,Object> convert(T t){
		if(t==null)
			return null;
		List<Field> fields = cacheMap.get(t.getClass().getName());
		if(fields==null){
			fields = prepareFields(t);
		}
		Map<String,Object> map = Maps.newHashMap();
		for(Field f:fields){
			try {
				map.put(f.getName(), f.get(t));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return map;
	}
	
	public synchronized static <T> List<Field> prepareFields(T t){
		if(cacheMap.containsKey(t.getClass().getName())){
			return cacheMap.get(t.getClass().getName());
		}
		List<Field> fields = Reflections.getAllFields(t);
		cacheMap.put(t.getClass().getName(), fields);
		return fields;
	}
	
}
