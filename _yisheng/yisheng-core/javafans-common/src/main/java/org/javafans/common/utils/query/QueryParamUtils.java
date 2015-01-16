package org.javafans.common.utils.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.javafans.common.utils.number.ToNumberUtils;
import org.javafans.common.utils.time.TimeUtils;

import com.google.common.collect.Maps;

/**
 * 查询条件转换
 * @author ChenJunhui
 */
public class QueryParamUtils {
	
	public static final String QUERY_KEY_PREFIX = "filter_";

	/**查询条件为String 的前缀*/
	public final static  String STRING_PREFIX = "S_";
	/**查询条件为Integer 的前缀*/
	public final static  String INTEGET_PREFIX = "I_";
	/**查询条件为Long 的前缀*/
	public final static  String LONG_PREFIX = "L_";
	/**查询条件为BigDecimal 的前缀*/
	public final static  String BIGDECIMAL_PREFIX = "B_";
	/**查询条件为 Date 的前缀*/
	public final static  String DATE_PREFIX = "D_";
	/**排序打头key  比如 按 那么  降序排序 则穿 order_by_name **/
	public final static String ORDER_BY_PREFIX = "order_by_";
	
	public final static String ORDER_DESC = "desc";//排序 降序
	public final static String ORDER_ASC = "asc"; //排序 升序
	public final static String ORDER_BY_KEY = "orderByKey";
	
	
	//还有其他类型的 可以扩展 并修改 getValue(String key,String value) 方法
	
	/**
	 * 将 requst 中 以filter_ 打头的参数 转到查询 map里
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,Object> builderQueryMap(HttpServletRequest request){
		Map requestMap = request.getParameterMap();
		Set<Object> set = requestMap.keySet();
		Map<String,Object> param = Maps.newHashMapWithExpectedSize(set.size());
		//排序字段拼接
		StringBuilder orderByStr = new StringBuilder();
		for (Iterator<Object> iter = set.iterator(); iter.hasNext();) {   
			String key = (String) iter.next();
			if(StringUtils.startsWith(key, QUERY_KEY_PREFIX)){
				String[] values = (String[]) requestMap.get(key);   
				if(values!=null){
					if(values.length==1){
						if(key.startsWith(ORDER_BY_PREFIX)){
							orderByStr.append(" ").append(getKey(key))
								.append(StringUtils.equalsIgnoreCase(values[0], ORDER_DESC)?ORDER_DESC:ORDER_ASC);
						}
						if(StringUtils.isNotBlank(values[0])){
							param.put(getKey(key), getValue(key, values[0]));
						}
					}else{//多个一般用于 in查询 这里放到list里
						List<Object> list = new ArrayList<Object>();
						for(String value:values){
							if(StringUtils.isNotBlank(value)){
								Object _value = getValue(key, value);
								if(_value!=null){
									list.add(_value);
								}
							}
						}
						param.put(getKey(key), list);
					}
				}
			}
		}
		//排序最终生成串
		String orderByValue = orderByStr.toString();
		if(StringUtils.isNotBlank(orderByValue)){
			param.put(ORDER_BY_KEY, orderByValue);
		}
		return param;
	}
	
	/**
	 * 获得转换的类型
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object getValue(String key,String value){
		String _key = key.substring(QUERY_KEY_PREFIX.length());
		if(StringUtils.startsWith(_key, STRING_PREFIX)){
			return value;
		}else if(StringUtils.startsWith(_key, INTEGET_PREFIX)){
			return ToNumberUtils.getIntegerValue(value);
		}else if(StringUtils.startsWith(_key, LONG_PREFIX)){
			return ToNumberUtils.getLongValue(value);
		}else if(StringUtils.startsWith(_key, BIGDECIMAL_PREFIX)){
			return ToNumberUtils.getBigDecimal(value);
		}else if(StringUtils.startsWith(_key, DATE_PREFIX)){
			return TimeUtils.getDateValue(value);
		}else{ //还有其他类型的自己加
			return null;
		}
	}
	
	/**
	 * 获得最终的查询key
	 * @param key
	 * @return
	 */
	public static String getKey(String key){
		String _key = key.substring(QUERY_KEY_PREFIX.length());
		if(StringUtils.startsWith(_key, STRING_PREFIX)){
			return _key.substring(STRING_PREFIX.length());
		}else if(StringUtils.startsWith(_key, INTEGET_PREFIX)){
			return _key.substring(INTEGET_PREFIX.length());
		}else if(StringUtils.startsWith(_key, LONG_PREFIX)){
			return _key.substring(LONG_PREFIX.length());
		}else if(StringUtils.startsWith(_key, BIGDECIMAL_PREFIX)){
			return _key.substring(BIGDECIMAL_PREFIX.length());
		}else if(StringUtils.startsWith(_key, DATE_PREFIX)){
			return _key.substring(DATE_PREFIX.length());
		}else if(StringUtils.startsWith(_key, ORDER_BY_PREFIX)){
			return _key.substring(ORDER_BY_PREFIX.length());
		}else{ //还有其他类型的自己加
			return null;
		}
	}
}
