package com.hz.yisheng.oa.workflow.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.oa.workflow.orm.WorkTask;

/**
 * 工单 DAO 工单基于map的 所以没有实体类
 * @author WeiSky
 *
 */
public interface WorkTaskMapper {

	/**
	 * 插入
	 * 
	 * @param task
	 */
	public void insert(WorkTask task);

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 */
	public WorkTask getById(long id);

	/**
	 * 更新工单
	 * 
	 * @param task
	 */
	public void update(WorkTask task);

	/**
	 * count 符合条件的总数
	 * 
	 * @param param
	 * @return
	 */
	public long count(Map<String, Object> param);

	/**
	 * 查询工单列表
	 * 
	 * @param param
	 * @return
	 */
	public List<WorkTask> list(Map<String, Object> param);

	/**
	 * 删除工单
	 */
	public void delete(Long taskId);
 
	public long countHistory(Map<String, Object> sqlMap);

	public List<WorkTask> listHistory(Map<String, Object> sqlMap);
}
