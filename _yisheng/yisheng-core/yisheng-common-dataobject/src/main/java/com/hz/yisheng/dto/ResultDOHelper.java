package com.hz.yisheng.dto;

import java.util.List;

/**
 * ResultDO 相关的对象创建
 * 
 * @author ChenJunhui
 * 
 */
public class ResultDOHelper {

	private static String ErrorMsg = "系统异常";

	/**
	 * 创建一个操作失败 的 ResultDO 对象
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static <T> ResultDO<T> getErrorResultDO(int errorCode, String message) {
		ResultDO<T> result = new ResultDO<T>();
		result.setOk(false);
		result.setCode(errorCode);
		result.setMessage(message);
		return result;
	}

	/**
	 * 创建一个成功 的 ResultDO 对象
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static <T> ResultDO<T> getResultDO(T t) {
		ResultDO<T> result = new ResultDO<T>();
		result.setOk(true);
		result.setResult(t);
		return result;
	}

	/**
	 * 创建一个成功的ResultDO对象带有Message
	 * 
	 * @param t
	 * @param message
	 * @return
	 */
	public static <T> ResultDO<T> getMsgResultDO(T t, String message) {
		ResultDO<T> result = new ResultDO<T>();
		result.setOk(true);
		result.setResult(t);
		result.setMessage(message);
		return result;
	}

	public static <T> ResultDO<T> getMsgCodeResultDO(Integer code, String message) {
		ResultDO<T> result = new ResultDO<T>();
		result.setOk(true);
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

	/**
	 * 创建一个操作失败 的 ResultPageDO 对象
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static <T> ResultPageDO<T> getErrorResultPageDO(int errorCode, String message) {
		ResultPageDO<T> result = new ResultPageDO<T>();
		result.setOk(false);
		result.setCode(errorCode);
		result.setMessage(message);
		return result;
	}

	/**
	 * 创建一个成功 的 ResultDO 对象
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static <T> ResultPageDO<T> getResultPageDO(long count, List<T> list) {
		ResultPageDO<T> result = new ResultPageDO<T>();
		result.setOk(true);
		result.setList(list);
		result.setCount(count);
		return result;
	}

	/**
	 * 创建一个操作复制对象失败 的 T extends BaseResultDO的 对象
	 * 
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static <T extends BaseResultDO> T getErrorComplexDO(Class<T> clazz, int errorCode, String message) {
		try {
			T t = clazz.newInstance();
			t.setOk(false);
			t.setCode(errorCode);
			t.setMessage(message);
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
