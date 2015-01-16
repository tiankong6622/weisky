package com.hz.yisheng.oa.workflow.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.oa.workflow.dao.TempNodeSqlMapper;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.TempNodeSql;
import com.hz.yisheng.oa.workflow.tools.MoreActorUtils;

/**
 * 多人临时处理同一条工单
 * @author WeiSKY
 *
 */
@Service
public class TempNodeSqlBO {

	@Autowired
	private TempNodeSqlMapper tempNodeSqlMapper;
	@Autowired
	private FlowNodeBO flowNodeBO;
	@Autowired
	private FlowDefineBO flowDefineBO;
	
	@Transactional("workFlowTransactionManager")
	public void insert(TempNodeSql tempNodeSql){
		FlowNode node = flowNodeBO.getById(tempNodeSql.getNodeId());
		FlowDefine flow = flowDefineBO.getById(node.getFlowId());
		tempNodeSql.setProjectId(flow.getProjectId());
		tempNodeSqlMapper.insert(tempNodeSql);
		MoreActorUtils.reSetMoreActorSql(flow.getProjectId());
	}
	
	@Transactional("workFlowTransactionManager")
	public void update(TempNodeSql tempNodeSql){
		tempNodeSqlMapper.update(tempNodeSql);
		FlowNode node = flowNodeBO.getById(tempNodeSql.getNodeId());
		FlowDefine flow = flowDefineBO.getById(node.getFlowId());
		MoreActorUtils.reSetMoreActorSql(flow.getProjectId());
	}
	
	
	public String getByNodId(Long nodeId){
		return tempNodeSqlMapper.getByNodId(nodeId);
	}
	
	public List<TempNodeSql> list(Map<String,Object> param){
		return tempNodeSqlMapper.list(param);
	}
	
	public TempNodeSql getByNodeId(long nodeId){
		Map<String,Object> param = new HashMap<String, Object>(1);
		param.put("nodeId", nodeId);
		List<TempNodeSql> sqls = tempNodeSqlMapper.list(param);
		return sqls.isEmpty()?null:sqls.get(0);
	}
}
