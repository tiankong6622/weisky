package com.hz.crf.model.utils;

import java.util.List;

import org.itboys.commons.dto.ResultDO;
import org.itboys.commons.dto.ResultPageDO;

/**
 * 返回给手机端的通用数据格式封装
 * 
 * @author weisky
 *
 * May 28, 2015
 */
public class ResultPageDOHelper {
	/**
	 * 创建一个失败 的 ResultDO 对象
	 * 
	 * @param string
	 * @param message
	 * @return
	 */
	public static <T> ResultDO<T> getErrorResultDO(int code, String message) {
		ResultDO<T> result = new ResultDO<T>();
		result.setOk(false);
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
	
	
	public static <T> ResultDO<T> getMsgCodeResultDO(int code, String message) {
		ResultDO<T> result = new ResultDO<T>();
		result.setOk(true);
		result.setCode(code);
		result.setMessage(message);
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
		result.setCode(0);
		result.setOk(true);
		result.setResult(t);
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

}
