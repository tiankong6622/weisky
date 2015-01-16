package org.javafans.common.utils.string;

import java.math.BigDecimal;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.javafans.common.constants.CommonConstants;

/**
 * string操作相关
 * @author ChenJunhui
 */
public  class CommonStringUtils {
	
	/**
	 * 判断str 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str !=null && str!="" && !str.equals("null")){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getStringValue(Object obj){
		return obj==null?null:obj.toString();
	}
	
	/**
	 * 判断str 是否在strs里
	 * @param str
	 * @param strs
	 * @return
	 */
	public static boolean in(String str,String ...strs){
		if(strs ==null || strs.length==0 || str==null)
			return false;
		for(String s:strs){
			if(StringUtils.equals(str, s)){
				return true;
			}
		}
		return false;
	}
	
	public static String nullToEmpty(String obj){
		return obj==null?StringUtils.EMPTY:obj;
	}
	
	/**
	 * 按字节截取字符
	 * @param str
	 * @param byte_len
	 * @return
	 */
	public static String catAndLeftByBytes(String str, int byte_len){
		if (str == null || str.getBytes().length < byte_len) {
			return str;
		}
		String substr = str;
		while (substr.getBytes().length > byte_len) {
			substr = substr.substring(0, substr.length() - 1);
		}
		return substr;
	}

	/**
	 * 获得折扣率 obj1/obj2 保留 scale 位小数
	 * @param obj1
	 * @param obj2
	 * @param scale
	 * @return
	 */
	public static String getZk(BigDecimal obj1,BigDecimal obj2,int scale){
		if(obj2==null || obj2.compareTo(CommonConstants.ZERO) <=0 || obj1==null || obj1.compareTo(CommonConstants.ZERO)<=0){
			return String.valueOf(0);
		}
		String result =  String.valueOf(obj1.multiply(new BigDecimal(10)).divide(obj2, scale, BigDecimal.ROUND_HALF_UP));
		//如果最后一个不是0的数字的后面都是0 把后面的0全部截取掉不要
		int index = result.indexOf(".");
		if(index!=-1){
			String remain = result.substring(index+1);
			char[] arr = remain.toCharArray();
			int idx = -1;
			for(int i = arr.length-1;i>=0;i--){
				if(arr[i]!=48){
					idx = i;
					break;
				}
			}
			if(idx == -1){
				return result.substring(0,index);
			}else{
				return result.substring(0,index+idx+2);
			}
		}
		return result; 
	}
	
	public static String formatNumber(String number,int bit){
		if(NumberUtils.isNumber(number)){
			int index = number.indexOf(".");
			if(index!=-1){
				String pro = number.substring(0, index);
				String end = number.substring(index+1,number.length());
				if(end.length()>bit){
					end = end.substring(0,bit);
				}
				return pro+"."+end;
			}
		}
		return number;
	}
	
	public static String printStr(int length,int count,String str){
		if(length<=0 || count<=0)
			return StringUtils.EMPTY;
		String string = StringUtils.EMPTY;
		for(int i=0;i<length*count;i++){
			string+=str;
		}
		return string;
	}
	
	/**
	 * 当str的长度大于subLength时候 截取字符 并用 append 追加到后面
	 * @param str
	 * @param subLength
	 * @return
	 */
	public static String subStringAndAppendValue(String str,int subLength,String append){
		if(str==null || subLength<=1)
			return str;
		str = str.trim();
		if(str.length()<=subLength)
			return str;
		str =  str.substring(0, subLength-1);
		return StringUtils.isBlank(append)?str:(str+append);
	}
	
	/**
	 * 判断str 是否在strs里
	 * @param str
	 * @param strs
	 * @return
	 */
	public static boolean contain(String strs,String str){
		if(!isNotEmpty(str) || !isNotEmpty(strs))
			return false;
		if(strs.contains(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 将一个数字 不满length位的话 前面补0
	 * @param num java.util.Number 的子类 Integer Long 等都是 Number的子类
	 * @param length
	 * @return
	 */
	public static String getNumberStr(Number num,int length){
		String numStr = num.toString();
		int len = numStr.length();
		if (len < length) {
			for (int i = 0, size = length - len; i < size; i++) {
				numStr = "0" + numStr;
			}
		}
		return numStr;
	}
	
	/**
	 * 判断 n个字符串是不是都为空或者null
	 * @param strs
	 * @return
	 */
	public static boolean isAllEmpty(String ...strs){
		if(ArrayUtils.isEmpty(strs)){
			return true;
		}
		for(String str:strs){
			if(StringUtils.isNotBlank(str)){
				return false;
			}
		}
		return true;
	}
}
