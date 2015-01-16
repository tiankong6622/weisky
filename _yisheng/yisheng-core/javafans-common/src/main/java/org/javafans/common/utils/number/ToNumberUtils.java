package org.javafans.common.utils.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 数字类型相关操作封装
 * @author ChenJunhui
 */
public abstract class ToNumberUtils {

	/**
	 * 对象转integer类型  (确定能转的调用)
	 * @param obj
	 * @return
	 */
	public static Integer getIntegerValue(Object obj){
		if(obj == null)
			return null;
		String str = StringUtils.trim(obj.toString());
		if(NumberUtils.isDigits(str)){
			return Integer.parseInt(str);
		}
		return null;
	}
	
	/**
	 * 对象转 bigdecimal类型 (确定能转的调用)
	 * @param obj
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object obj){
		if(obj!=null){
			String str = StringUtils.trim(obj.toString());
			if(NumberUtils.isNumber(str)){
				return new BigDecimal(str);
			}
		}
		return null;
	}
	
	/**
	 * 格式化数字对象 并转成 BigDecimal 类型 (确定能转的调用)
	 * @param obj
	 * @param format
	 * @return
	 */
	public static BigDecimal formatNumber(Object obj,String format){
		String str = (obj==null?StringUtils.EMPTY:obj.toString());
		if(!NumberUtils.isNumber(str))
			return null;
		DecimalFormat df = new DecimalFormat(format);
		return new BigDecimal(df.format(new BigDecimal(str)));
	}
	
	/**
	 * 将数字对象 转成 long型 (确定能转的调用)
	 * @param obj
	 * @return
	 */
	public static Long getLongValue(Object obj){
		if(obj==null)
			return null;
		String str = StringUtils.trim(obj.toString());
		if(NumberUtils.isDigits(str)){
			return Long.parseLong(str);
		}
		return null;
	}
}
