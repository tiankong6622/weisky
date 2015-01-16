package com.hz.yisheng.oa.workflow.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.oa.workflow.orm.WorkTaskBizData;

/**
 * 单各个节点处理记录关联的业务对象数据 DAO接口
 * @author WeiSky
 *
 */
public interface WorkTaskBizDataMapper {

	/**
	 * 批量插入业务数据
	 * @param list
	 */
	public void batchInsert(List<WorkTaskBizData> list);
	/**
	 * 根据条件查询
	 * @param param
	 * @return
	 */
	public List<WorkTaskBizData> list(Map<String, Object> param);
	
	public void deleteByTaskId(Long workTaskId);
}
