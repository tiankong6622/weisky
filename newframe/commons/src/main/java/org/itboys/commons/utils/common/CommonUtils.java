package org.itboys.commons.utils.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.number.ToNumberUtils;
import org.itboys.commons.utils.reflection.Reflections;
import org.itboys.commons.utils.time.TimeUtils;

import com.google.common.collect.Lists;

/**
 * 通用utils
 * @author ChenJunhui
 */
public  class CommonUtils {
	
	/**
	 * 判断一个对象是否在一个对象集合中
	 * @param obj
	 * @param objects
	 * @return
	 */
	public static <T>  boolean isIn(T obj,List<T> objects){
		if(obj==null || objects==null || objects.isEmpty()){
			return false;
		}
		for(T o:objects){
			if(obj.equals(o)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断一个对象是否在一个对象集合中
	 * @param obj
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>  boolean isIn(T obj,T... objects){
		if(obj==null || objects==null || objects.length==0){
			return false;
		}
		for(T o:objects){
			if(obj.equals(o)){
				return true;
			}
		}
		return false;
	}

	public static boolean isAllNull(Object ...obj){
		if(obj==null || obj.length==0)
			return true;
		for(Object o:obj){
			if(o!=null){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 将集合中 某一个数字型的字段相加 并转成bigdecimal字段提交
	 * @param collection
	 * @param properties
	 * @return
	 */
	public static BigDecimal sum(Collection<?> collection,String properties){
		BigDecimal sum = CommonConstants.ZERO;
		if(collection!=null && !collection.isEmpty() && StringUtils.isNotBlank(properties)){
			for(Object obj:collection){
				if(obj != null){
					try {
						Object filedValue = PropertyUtils.getProperty(obj, properties);
						BigDecimal decimalValue = ToNumberUtils.getBigDecimal(filedValue);
						if(decimalValue != null){
							sum = sum.add(decimalValue);
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					} 
				}
			}
		}
		return sum;
	}
	
	public static Integer sumInt(Collection<?> collection,String properties){
		Integer sum = 0;
		if(collection!=null && !collection.isEmpty() && StringUtils.isNotBlank(properties)){
			for(Object obj:collection){
				if(obj != null){
					try {
						Object filedValue = PropertyUtils.getProperty(obj, properties);
						Integer intvalue = ToNumberUtils.getIntegerValue(filedValue);
						if(intvalue != null){
							sum = sum+intvalue;
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					} 
				}
			}
		}
		return sum;
	}
	
	public static boolean isGtThen(BigDecimal big,Object obj){
		if(big==null || obj==null)
			return false;
		return big.compareTo(ToNumberUtils.getBigDecimal(obj))>0;
	}
	
	/**
	 * 给对象复制
	 * @param obj
	 * @param value
	 * @param field
	 */
	public static <T> void setProperties(T obj,String value, String field){
		try{
			Field f = Reflections.getAccessibleField(obj, field);
			Class<?> fieldClass = f.getType();
			if(fieldClass.equals(String.class)){
				PropertyUtils.setProperty(obj, field, String.valueOf(value));
			}else if(fieldClass.equals(BigDecimal.class)){
				PropertyUtils.setProperty(obj, field, ToNumberUtils.getBigDecimal(value));
			}else if(fieldClass.equals(Long.class)){
				PropertyUtils.setProperty(obj, field, ToNumberUtils.getLongValue(value));
			}else if(fieldClass.equals(Integer.class)){
				PropertyUtils.setProperty(obj, field, ToNumberUtils.getIntegerValue(value));
			}else if(fieldClass.equals(Date.class)){
				PropertyUtils.setProperty(obj, field, TimeUtils.getDateValue(value));
			} else{
				throw new RuntimeException("暂时不支持"+fieldClass.getName()+"对象类型的转换 请自行扩展该函数!");
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<List<T>> genSubLists(List<T> list,int splitSize){
		if(list==null || list.isEmpty() || splitSize<=0)
			return Collections.EMPTY_LIST;
		int size = list.size()/splitSize;
		if(list.size()%splitSize!=0){
			size++;
		}
		List<List<T>> lists = Lists.newArrayListWithCapacity(size);
		List<T> subList=Lists.newArrayListWithCapacity(splitSize);
		for(int i=0,length=list.size();i<length;i++){
			if(i!=0 && i%splitSize==0){
				lists.add(subList);
				subList = Lists.newArrayListWithCapacity(splitSize);
			}
			subList.add(list.get(i));
		}
		lists.add(subList);
		return lists;
	}
	
}
