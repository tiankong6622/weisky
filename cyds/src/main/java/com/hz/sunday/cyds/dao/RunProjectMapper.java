package com.hz.sunday.cyds.dao;

import java.util.List;
import java.util.Map;

import com.hz.sunday.cyds.orm.RunProject;

/**
 * 参赛项目接口
 * @author WeiSky
 *
 */
public interface RunProjectMapper {

	/**
	 * 新增
	 * @param RunProject
	 */
	public void insert(RunProject runProject);
	
	/**
	 * 编辑
	 * @param RunProject
	 */
	public void update(RunProject runProject);
	
	/**
	 * 根据id，删除一条信息
	 * @param id
	 */
	public void delete(Long id);
	
	/**
	 * 根据条件查询符合条件的信息条数
	 * @param param
	 * @return
	 */
	public Long getCount(Map<String, Object> param);
	
	/**
	 * 根据条件，查询符合条件的客户信息，返回的是一个集合
	 * @param param
	 * @return
	 */
	public List<RunProject> getList(Map<String, Object> param);
	
	/**
	 * 根据id，获取一条详情
	 * @param id
	 * @return
	 */
	public RunProject getSingleDetail(Long id);
}
