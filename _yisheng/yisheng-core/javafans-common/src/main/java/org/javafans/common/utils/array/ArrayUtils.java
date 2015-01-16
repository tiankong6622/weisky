package org.javafans.common.utils.array;

/**
 * 数组相关操作封装
 * @author ChenJunhui
 */
public abstract class ArrayUtils {

	public static boolean isEmpty(Object[] arr){
		return (arr==null || arr.length==0);
	}
	
	/**
	 * 判断数组arr中是否包括对象t
	 * @param arr
	 * @param t
	 * @return
	 */
	public static <T> boolean contains(T[] arr,T t){
		if(arr==null || arr.length==0 || t==null)
			return false;
		for(T obj:arr){
			if(t.equals(obj)){
				return true;
			}
		}
		return false;
	}
}
