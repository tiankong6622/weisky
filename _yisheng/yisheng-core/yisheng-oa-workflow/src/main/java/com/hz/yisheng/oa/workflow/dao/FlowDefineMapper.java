package com.hz.yisheng.oa.workflow.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.oa.workflow.orm.FlowDefine;

/**
 * 流程定义DAO
 * @author WeiSky
 *
 */
public interface FlowDefineMapper {

	public void insert(FlowDefine define);
	
	/**
	 * 获取最新流程
	 * @param flowName
	 * @return
	 */
	public FlowDefine getLast(String flowName);
	
	/**
	 * 获取最新流程
	 * @param flowName
	 * @return
	 */
	public FlowDefine getLastByProjectId(@Param("flowName") String flowName,@Param("projectId") long projectId);
	
	
	public FlowDefine getById(long flowId);
	
	public List<FlowDefine> list(Map<String,Object> param);
	
	public void update(FlowDefine define);
	
	public List<FlowDefine> getByIds(List<Long> list);

	/** 获取流程信息*/
	public List<FlowDefine> findProcessInfoByMap(Map<String, Object> params);

	/** 停止、启用流程*/
	public void updateById(FlowDefine flowDefine);
	
}
