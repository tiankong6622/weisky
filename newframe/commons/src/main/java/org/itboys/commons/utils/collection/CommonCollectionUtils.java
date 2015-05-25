package org.itboys.commons.utils.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * 通用集合相关封装
 * @author ChenJunhui
 */
public class CommonCollectionUtils {

	public static boolean isEmpty(Collection<?> collection){
		return (collection==null || collection.isEmpty());
	}
	
	public static boolean isNotEmpty(Collection<?> collection){
		return !CommonCollectionUtils.isEmpty(collection);
	}
	
	public static List<Long> splictToLongList(String str,String split){
		if(StringUtils.isBlank(str)|| StringUtils.isBlank(split)){
			return new ArrayList<Long>(0);
		}
		Iterator<String> iter = Splitter.on(split).omitEmptyStrings().trimResults().split(str).iterator();
		List<Long> list = new ArrayList<Long>();
		while(iter.hasNext()){
			String next = iter.next();
			if(NumberUtils.isDigits(next)){
				list.add(Long.parseLong(next));
			}
		}
		return list;
	}
	
	/**
	 * 抽取集合中符合条件的
	 * @author ChenJunhui
	 * @param collection
	 * @param condition
	 * @return
	 */
	public static <T> List<T> filterCollection(Collection<T> collection,FetchCondition<T> condition){
		List<T> list = Lists.newArrayList();
		if(collection==null || collection.isEmpty()){
			return list;
		}
		for(T t:collection){
			if(condition.needFetch(t)){
				list.add(t);
			}
		}
		return list;
	}
	
	/**
	 * 把集合collection中的对象的属性为field并且值in (eqValue)的数据提取出来
	 * @param collection
	 * @param field
	 * @param eqValue
	 * @return
	 */
	public static Collection<?> fetchByFieldIn(Collection<?> collection,String field,Object... eqValue){
		Collection<Object> c = new ArrayList<Object>();
		if(collection!=null && StringUtils.isNotBlank(field)){
			for(Object obj:collection){
				if(obj!=null){
					try{
						Object fieldValue = PropertyUtils.getProperty(obj, field);
						for(Object eqv:eqValue){
							if(fieldValue==null){
								if(eqv==null){
									c.add(obj);
									break;
								}
							}else{
								if(fieldValue.equals(eqv)){
									c.add(obj);
									break;
								}
							}
						}
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}
			}
		}
		return c;
	}
	
	
	/**
	 * 把 str 按照换行回车符号转成list
	 * @param str
	 * @return
	 */
	public static List<String> spliteEnterToList(String str){
		List<String> list = new ArrayList<String>();
		if(StringUtils.isBlank(str))
			return list;
		String[] arr = StringUtils.split(str.trim(), "\r\n");
		for(String s:arr){
			if(StringUtils.isNotBlank(s)){
				String[] arr1 = StringUtils.split(s.trim(),"\n");
				for(String s1:arr1){
					if(StringUtils.isNotBlank(s1)){
						list.add(s1);
					}
				}
			}
		}
		return list;
	}
	
	public static List<String> getStringList(String ... str){
		List<String> list = new ArrayList<String>();
		if(str==null || str.length==0)
			return list;
		for(String s:str){
			list.add(s);
		}
		return list;
	}
}
