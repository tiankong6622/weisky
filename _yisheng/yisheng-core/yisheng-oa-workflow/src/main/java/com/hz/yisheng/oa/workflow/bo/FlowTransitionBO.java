package com.hz.yisheng.oa.workflow.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.yisheng.oa.workflow.dao.FlowTransitionMapper;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;

/**
 * 流程中转线业务层
 * @author WeiSky
 *
 */
@Service
public class FlowTransitionBO {

	@Autowired
	private FlowTransitionMapper flowTransitionMapper;
	@Autowired
	private FlowNodeBO flowNodeBO;
	@Autowired
	private FlowDefineBO flowDefineBO;
	
	public FlowTransition getByFromId(long fromId){
		return flowTransitionMapper.getByFromId(fromId);
	}
	
	public FlowTransition getByToId(long toId){
		return flowTransitionMapper.getByToId(toId);
	}
	
	public FlowTransition getById(long id){
		return flowTransitionMapper.getById(id);
	}
	
	public void batchInsert(List<FlowTransition> list){
		flowTransitionMapper.batchInsert(list);
	}
	
	public List<FlowTransition>  getByIds(List<Long> ids){
		return flowTransitionMapper.getByIds(ids);
	}
	
	public FlowTransition getByFromIdCode(long fromId, String nodeCode){
		return flowTransitionMapper.getByFromIdCode(fromId,nodeCode);
	}
	
	/**
	 * 获得第一条流转线
	 * @param flowName
	 * @return
	 */
	public FlowTransition getFistToTransition(String flowName){
		FlowDefine flow = flowDefineBO.getLast(flowName);
		return this.getFistToTransition(flow);
	}
	
	public FlowTransition getFistToTransition(FlowDefine flow){
		long startNodeId = flow.getStartNodeId();
		//找开始节点
		FlowNode startNode = flowNodeBO.getById(startNodeId);
		//开始节点只有一条流转线
		FlowTransition startToTransition =getByFromId(startNode.getId());
		FlowNode node = flowNodeBO.getById(startToTransition.getToNodeId());
		return getByFromId(node.getId());
	}
	
	public FlowTransition getFistToTransition(FlowDefine flow,String nodeCode){
		long startNodeId = flow.getStartNodeId();
		//找开始节点
		FlowNode startNode = flowNodeBO.getById(startNodeId);
		//开始节点只有一条流转线
		FlowTransition startToTransition =getByFromId(startNode.getId());
		FlowNode node = flowNodeBO.getById(startToTransition.getToNodeId());
		return getByFromIdCode(node.getId(),nodeCode);
	}
	
	/**
	 * 根据fromID获取多条流转线
	 * @param fromId
	 * @return
	 */
	public List<FlowTransition> getTransitionsFromId(long fromId){
		return flowTransitionMapper.getTransitionsFromId(fromId);
	}
}
