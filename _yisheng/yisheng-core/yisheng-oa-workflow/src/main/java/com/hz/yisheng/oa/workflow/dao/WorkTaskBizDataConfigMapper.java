package com.hz.yisheng.oa.workflow.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.oa.workflow.orm.WorkTaskBizDataConfig;

public interface WorkTaskBizDataConfigMapper {

	public List<WorkTaskBizDataConfig> getWorkTaskBizDataConfigs(
			List<String> list);

	/** 获取列表数据 */
	public List<WorkTaskBizDataConfig> findAll(Map<String, Object> params);

	/** 获取数据个数 */
	public Long findAllCount(Map<String, Object> params);

	/** 根据ID，获取数据 */
	public WorkTaskBizDataConfig getById(Long id);

	/** 新增 */
	public void add(WorkTaskBizDataConfig workTaskBizDataConfig);

	/** 修改 */
	public void update(WorkTaskBizDataConfig workTaskBizDataConfig);

	/** 获取所有的流程 */
	public List<WorkTaskBizDataConfig> findAllFlow();

	/** 获取流程类型 */
	public WorkTaskBizDataConfig getInfoByDataObjType(String dataObjType);
}
