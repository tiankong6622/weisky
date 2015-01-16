package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.Sports;

public interface SportsMapper {
	/**
	 * 插入 
	 * @param sports
	 */
	public void insert(Sports sports);
	/**
	 * 更新
	 * @param sports
	 */
	public void update(Sports sports);
	/**
	 * 查询所有的运动记录
	 * @param queryParam
	 * @return
	 */
	public List<Sports> list(Map<String,Object> queryParam);
	/**
	 * 记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);
	
	/**
	 * 根据id查询运动记录
	 * @param id
	 * @return
	 */
	public List<Sports> getSportsByCustomerId(Long id);
}
