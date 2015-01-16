package com.hz.yisheng.oa.workflow.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.yisheng.admin.YiShengFlowContants;
import com.hz.yisheng.oa.workflow.constants.FlowNodeType;
import com.hz.yisheng.oa.workflow.core.FlowHolder;
import com.hz.yisheng.oa.workflow.dao.FlowDefineMapper;
import com.hz.yisheng.oa.workflow.dto.FlowDTO;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 流程定义业务层
 * 
 * @author WeiSky
 * 
 */
@Service
public class FlowDefineBO {

	@Autowired
	private FlowDefineMapper flowDefineMapper;
	@Autowired
	private FlowTransitionBO flowTransitionBO;
	@Autowired
	private FlowNodeBO flowNodeBO;

	/**
	 * 基于json部署流程
	 * 
	 * @param json
	 */
	@Transactional("workFlowTransactionManager")
	public String deployByJson(String json) {
		return this.deployByJson(json, 0L);
	}

	/**
	 * 自定义流程 基于json部署流程
	 * 
	 * @param json
	 */
	@Transactional("workFlowTransactionManager")
	public String customizeDeployByJson(String json) {
		FlowDTO dto = FlowHolder.jsonTemplate2FlowDTOCustomize(json);
		FlowDefine define = dto.getDefine();
		SessionHolder.prepareAdminAndProjectLoginData(define);
		define.setTemplate(json);
		define.setStartNodeId(0L);
		FlowDefine last = flowDefineMapper.getLast(define.getFlowname());
		if (last == null) {
			define.setVersion(1);
		} else {
			define.setVersion(last.getVersion() + 1);
		}
		define.setProjectId(0L);
		flowDefineMapper.insert(define);

		List<FlowNode> nodes = dto.getNodes();
		for (FlowNode node : nodes) {
			node.setFlowId(define.getId());
		}
		flowNodeBO.batchInsert(nodes);
		nodes = flowNodeBO.getByFlowId(define.getId());
		for (FlowNode node : nodes) {
			if (node.getType().equals(FlowNodeType.start)
					|| node.getType().equals(FlowNodeType.start_round)) {
				define.setStartNodeId(node.getId());
				flowDefineMapper.update(define);
				break;
			}
		}

		List<FlowTransition> transitions = dto.getTransitions();
		for (FlowTransition ft : transitions) {
			for (FlowNode fn : nodes) {
				if (StringUtils.equals(fn.getCode(), ft.getFrom())) {
					ft.setFromNodeId(fn.getId());
				}
				if (StringUtils.equals(fn.getCode(), ft.getTo())) {
					ft.setToNodeId(fn.getId());
				}
			}
		}
		flowTransitionBO.batchInsert(transitions);
		return define.getFlowname();
	}

	/**
	 * 基于json部署流程
	 * 
	 * @param json
	 */
	@Transactional("workFlowTransactionManager")
	public String deployByJson(String json, long projectId) {
		FlowDTO dto = FlowHolder.jsonTemplate2FlowDTO(json);
		FlowDefine define = dto.getDefine();
		if (projectId > 0) {
			SessionHolder.prepareProjectLoginData(define);
		} else {
			SessionHolder.prepareAdminAndProjectLoginData(define);
		}
		define.setTemplate(json);
		define.setStartNodeId(0L);
		FlowDefine last = projectId > 0L ? flowDefineMapper.getLastByProjectId(
				define.getFlowname(), projectId) : flowDefineMapper
				.getLast(define.getFlowname());
		if (last == null) {
			define.setVersion(1);
		} else {
			define.setVersion(last.getVersion() + 1);
		}
		define.setProjectId(projectId);
		flowDefineMapper.insert(define);

		List<FlowNode> nodes = dto.getNodes();
		for (FlowNode node : nodes) {
			node.setFlowId(define.getId());
		}
		flowNodeBO.batchInsert(nodes);
		nodes = flowNodeBO.getByFlowId(define.getId());
		for (FlowNode node : nodes) {
			if (node.getType().equals(FlowNodeType.start)) {
				define.setStartNodeId(node.getId());
				flowDefineMapper.update(define);
				break;
			}
		}

		List<FlowTransition> transitions = dto.getTransitions();
		for (FlowTransition ft : transitions) {
			for (FlowNode fn : nodes) {
				if (StringUtils.equals(fn.getCode(), ft.getFrom())) {
					ft.setFromNodeId(fn.getId());
				}
				if (StringUtils.equals(fn.getCode(), ft.getTo())) {
					ft.setToNodeId(fn.getId());
				}
			}
		}
		flowTransitionBO.batchInsert(transitions);
		return define.getFlowname();
	}

	/**
	 * 根据自定义的流程所属哪个模块的ID获取列表
	 * 
	 * @param typeId
	 * @return
	 */
	public List<FlowDefine> findByFlowTypeId(Long typeId) {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		return list(param);
	}

	public List<FlowDefine> list(Map<String, Object> param) {
		return flowDefineMapper.list(param);
	}

	public FlowDefine getById(long flowId) {
		return flowDefineMapper.getById(flowId);
	}

	public FlowDefine getLastByProjectId(String flowName, long projectId) {
		return flowDefineMapper.getLastByProjectId(flowName, projectId);
	}

	public FlowDefine getLast(String flowName) {
		return flowDefineMapper.getLast(flowName);
	}

	public List<FlowDefine> getByIds(List<Long> ids) {
		return flowDefineMapper.getByIds(ids);
	}

	/**
	 * 获取对应的流程
	 * 
	 * @param dataObjType
	 * @return
	 */
	public List<FlowDefine> choiceProcess(Integer dataObjType) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (1 == dataObjType) {// 请假流程
			params.put("dataObjType",
					YiShengFlowContants.DataObjType.LEAVEN_TYPE);
		} else if (2 == dataObjType) {// 加班流程
			params.put("dataObjType",
					YiShengFlowContants.DataObjType.OVERTIME_TYPE);
		}
		return flowDefineMapper.findProcessInfoByMap(params);
	}

	/**
	 * 停止、启用流程
	 * 
	 * @param flowDefine
	 */
	public void updateById(FlowDefine flowDefine) {
		flowDefineMapper.updateById(flowDefine);
	}
}
