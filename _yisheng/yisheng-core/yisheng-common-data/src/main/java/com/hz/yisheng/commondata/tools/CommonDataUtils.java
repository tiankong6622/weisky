package com.hz.yisheng.commondata.tools;

import org.javafans.framework.spring.common.SpringContextHolder;

import com.hz.yisheng.commondata.bo.CityBO;
import com.hz.yisheng.commondata.orm.City;

/**
 * @author Liuguanjun
 * 2013-7-12  13:26
 */
public class CommonDataUtils {
	
	public static String getAddressByCityid(Integer cityId){
		CityBO cityBO =  SpringContextHolder.getBean(CityBO.class);
		City city = cityBO.getCity(Long.valueOf(cityId));
		return city==null||city.getFullName()==null?"":city.getFullName().replaceAll("/", "");
	}
}