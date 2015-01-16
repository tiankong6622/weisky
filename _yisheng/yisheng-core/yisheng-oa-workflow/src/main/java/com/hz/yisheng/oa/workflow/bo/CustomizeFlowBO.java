package com.hz.yisheng.oa.workflow.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.admin.YiShengFlowContants;
import com.hz.yisheng.oa.workflow.bo.FlowNodeBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBizDataConfigBO;
import com.hz.yisheng.oa.workflow.core.FlowEngine;
import com.hz.yisheng.oa.workflow.core.WorkTaskBizDataHelper;
import com.hz.yisheng.oa.workflow.dto.FlowParam;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizDataConfig;

/**
 * 自定义流程处理
 * @author WeiSky
 *
 */
@Service
public class CustomizeFlowBO {
	
	@Autowired
	private WorkTaskBizDataConfigBO workTaskBizDataConfigBO;
	@Autowired
	private FlowEngine flowEngine;
	@Autowired
	private FlowNodeBO flowNodeBO;

	/**
	 * 处理自定义流程
	 * @param <O>
	 * @return 
	 */
	@Transactional("workFlowTransactionManager")
	public <O> void doCustomizeFlow(FlowParam flowParam,WorkTaskBizDataConfig config, List<O> list, Long objId){
		if (flowParam.getWorkTaskId() == null) {
			//开启流程
			flowEngine.startFlow(flowParam,  WorkTaskBizDataHelper.perpareBizDatasObj(config.getDataObjType(), list, objId));
		}else{
			// 执行流程节点任务
			flowParam.setFinished(true);
			if (flowNodeBO.getTypeByTransition(
					flowParam.getToTranstaionId()).equals(
					YiShengFlowContants.NodeType.submit_node)) {
				// 退回发起人的时候时候 重搞
				flowParam.setNodeBizDataDealType(FlowParam.BIZ_DATA_DEAL_TYPE_RESET);
			}
			flowEngine.doFlowTask(WorkTaskBizDataHelper.perpareBizDatasObj(
					config.getDataObjType(), list, objId), flowParam);
		}
	}
	
}
