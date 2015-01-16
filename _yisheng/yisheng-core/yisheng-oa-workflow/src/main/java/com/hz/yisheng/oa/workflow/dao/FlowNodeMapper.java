package com.hz.yisheng.oa.workflow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.yisheng.oa.workflow.orm.FlowNode;

/**
 * 流程节点DAO
 * @author WeiSky
 *
 */
public interface FlowNodeMapper {

	public void batchInsert(List<FlowNode> list);
	
	public FlowNode getById(long id);
	
	public List<FlowNode> getByFlowId(long flowId);
	
	public List<FlowNode> getByIds(List<Long> list);
	
	public String getFlowActorFilterCondition(Long id);
	
	public int updateFlowActorFilterCondition(@Param("id") Long id,@Param("actorFilterCondition") String actorFilterCondition);
}
