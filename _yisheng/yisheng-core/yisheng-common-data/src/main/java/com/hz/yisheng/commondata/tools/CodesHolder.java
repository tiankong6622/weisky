package com.hz.yisheng.commondata.tools;

import org.javafans.framework.spring.common.SpringContextHolder;

import com.hz.yisheng.commondata.bo.KVConfigBO;

/**
 * Codes相关
 * @author ChenJunhui
 *
 */
public class CodesHolder {

	public static String getConfigValue(String configKey){
		KVConfigBO kVConfigBO = SpringContextHolder.getBean(KVConfigBO.class);
		return kVConfigBO.getConfigValue(configKey);
	}
}
