package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.ChildHealth;

public interface ChildHealthMapper {
	/**
	 * 新增操作
	 * @param childHealth
	 */
	public void insert(ChildHealth childHealth);
	
	/**
	 * 查询婴儿健康信息
	 * @param queryParam
	 * @return
	 */
	public List<ChildHealth> list(Map<String,Object> queryParam);
	
	/**
	 * 根据婴儿ID查找健康信息
	 * @param id
	 * @return
	 */
	public List<ChildHealth> getChildHealthById(Long id);
	
	/**
	 * 健康信息记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);
	

}
