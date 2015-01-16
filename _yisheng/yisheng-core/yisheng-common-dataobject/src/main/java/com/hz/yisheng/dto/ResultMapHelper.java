package com.hz.yisheng.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultMapHelper {

	/**
	 * 创建一个操作失败 的 Map 对象
	 * @param errorCode
	 * @param message
	 * @return
	 */
	public static  Map<String, Object> getErrorResultMap(int errorCode,String message){
		Map<String, Object> resultMap = new HashMap<String, Object>(3);
		resultMap.put("ok", false);
		resultMap.put("success", "false");
		resultMap.put("code", errorCode);
		resultMap.put("message", message);
		return resultMap;
	}
	
	public static <T> Map<String, Object>  getPageResultMap(long totalCount,List<T> list){
		Map<String, Object> resultMap = new HashMap<String, Object>(3);
		resultMap.put("ok", true);
		resultMap.put("success", "true");
		resultMap.put("totalCount", totalCount);
		resultMap.put("list", list);
		return resultMap;
	}
	
	public static <T> Map<String, Object>  getSuccessMap(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("ok", true);
		resultMap.put("success", "true");
		resultMap.put("success", "true");
		return resultMap;
	}
	
	public static <T> Map<String, Object>  getSuccessMap(int size){
		Map<String, Object> resultMap = new HashMap<String, Object>(size);
		resultMap.put("ok", true);
		resultMap.put("success", "true");
		return resultMap;
	}
}
