package com.hz.yisheng.oa.workflow.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.oa.workflow.orm.WorkTaskRecord;

/**
 * 工单处理记录DAO
 * @author WeiSky
 *
 */
public interface WorkTaskRecordMapper {
	
	/**
	 * 插入
	 * @param task
	 */
	public void insert(WorkTaskRecord record);

	/**
	 * 更新工单
	 * @param task
	 */
	public void update(WorkTaskRecord task);
	
	/**
	 * count 符合条件的总数
	 * @param param
	 * @return
	 */
	public long count(Map<String, Object> param);
	
	/**
	 * 查询工单处理记录列表
	 * @param param
	 * @return
	 */
	public List<WorkTaskRecord> list(Map<String, Object> param);
	
	/**
	 * 查找某个节点最近的一次处理记录 回退的时候用
	 * @param nodeId
	 * @return
	 */
	public WorkTaskRecord getLastRecord(Map<String, Object> param);
	
	
}
