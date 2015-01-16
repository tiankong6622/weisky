package com.hz.yisheng.commondata.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.commondata.orm.Module;

/**
 * 功能模块DAO
 * @author GuoRui
 */
public interface ModuleMapper {

	public List<Module> list(Map<String,Object> queryParam);
	
	/**
	 * 根据动态条件查询功能模块 查询count
	 * @param sqlMap 查询条件同list 方法
	 * @return
	 */
	public long count(Map<String,Object> sqlMap);
	
	/**
	 * 插入
	 * @param module
	 */
	public void insert(Module module);
	
	/**
	 * 更新
	 * @param module
	 */
	public int update(Module module);
	
	/**
	 * 删除
	 * @param id
	 */
	public int delete(Long id);

	/*
	 * 获取具有权限的模板
	 */
	public List<Module> getModule();
}
