package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import org.javafans.common.dto.Option;

import com.hz.yisheng.commondata.orm.Codes;

/**
 * 数据字典DAO层
 * @author Liuguanjun
 */
public interface CodesMapper {

	public List<Codes> list(Map<String, Object> map);
	
	public Long count(Map<String, Object> map);
	
	public List<Option> getCodesByType(Map<String, Object> map);
	
	public void insert(Codes codes);
	
	public void update(Codes codes);
	
	public int delete(Long id);
	
	public Codes getById(Long id);
	/**
	 * 根据type和上次更新时间
	 * 获取更新列表
	 * @param typeList
	 * @param date
	 * @return
	 */
	public List<Codes> getCodesByTypeAndUpdateTime(Map<String,Object> map);

	/**
	 * 根据type和上次更新时间
	 * 获取更新列表
	 * @param type
	 * @param date
	 * @return
	 */
	public List<Codes> getCodesByTypeUpdateTime(Map<String,Object> map);
}
