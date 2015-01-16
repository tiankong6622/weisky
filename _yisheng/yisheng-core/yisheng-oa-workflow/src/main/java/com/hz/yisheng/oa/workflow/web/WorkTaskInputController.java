package com.hz.yisheng.oa.workflow.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.oa.workflow.bo.FlowDefineBO;
import com.hz.yisheng.oa.workflow.bo.FlowNodeBO;
import com.hz.yisheng.oa.workflow.bo.FlowTransitionBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBizDataBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskRecordBO;
import com.hz.yisheng.oa.workflow.constants.FlowNodeType;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;
import com.hz.yisheng.oa.workflow.orm.WorkTask;
import com.hz.yisheng.oa.workflow.orm.WorkTaskRecord;
import com.hz.yisheng.webdata.SessionHolder;

public abstract class WorkTaskInputController extends BaseController {
	
	@Autowired
	protected WorkTaskBO workTaskBO;
	@Autowired
	protected FlowDefineBO flowDefineBO;
	@Autowired
	private WorkTaskRecordBO workTaskRecordBO;
	@Autowired
	private FlowTransitionBO flowTransitionBO;
	@Autowired
	private WorkTaskBizDataBO workTaskBizDataBO;
	@Autowired
	private FlowNodeBO  flowNodeBO;
	
	/**
	 * 各种工单处理页面的vm或jsp等视图解析地址
	 * @return
	 */
	protected abstract String getTaskInput();
	
	/**
	 * 有工单是 input方法里不能满足的数据 实现该方法 自行扩展
	 * @param taskId
	 * @param model
	 * @return
	 */
	protected abstract void doPrepareSpecialData(WorkTask task,HttpServletRequest request,Model model);
	
	/**
	 * 无工单时候 input方法不能满足的数据 扩展该方法
	 * @param request
	 * @param model
	 */
	protected abstract void doPrepareSpecialData(HttpServletRequest request,Model model);
	/**
	 * 通用的工单处理页面 如果有特殊数据要加载
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/worktask/input/{flowname}")
	public String input(@PathVariable("flowname") String flowname, 
			@RequestParam(value="taskId",required=false) Long taskId,HttpServletRequest request, Model model){
		if(taskId!=null && taskId>0L){
			WorkTask task = workTaskBO.getById(taskId);
			List<WorkTaskRecord> records = workTaskRecordBO.getTaskRecords(taskId);
			Long myUserId = SessionHolder.getAdminUserId();
			//boolean morePeop = MoreActorUtils.isInMoreSql(myUserId, task.getCurrentNode());
			//if(!morePeop){
				if(!task.getActor().equals(myUserId)){
					//不是当前处理人进来
					model.addAttribute("isNotCurrentActor", true);
				}
			//}
			//工单基本信息
			model.addAttribute("workTask", task);
			//工单处理记录
			model.addAttribute("workTaskRecords", records);
			//主业务数据
			model.addAttribute("bizData", workTaskBizDataBO.getMainBizDatas(taskId));
			model.addAttribute("workFlow", flowDefineBO.getById(task.getFlowId()));
			//当前节点下一步有几条流转线
			List<FlowTransition> transitions = flowTransitionBO.getTransitionsFromId(task.getCurrentNode());
			model.addAttribute("flowTransitions", transitions);
			if(!transitions.isEmpty()){
				FlowTransition nextTransition = transitions.get(0);
				model.addAttribute("flowTransition",nextTransition);
				//下一个节点是否是end节点
				FlowNode nextNode = flowNodeBO.getById(nextTransition.getToNodeId());
				if(nextNode.getType().equals(FlowNodeType.end) || nextNode.getType().equals(FlowNodeType.end_round)){
					model.addAttribute("endNode", nextNode);
				}
			}
			doPrepareSpecialData(task, request,model);
		}else{
			FlowDefine flow = flowDefineBO.getLast(flowname);
			model.addAttribute("workFlow",flow );
			model.addAttribute("flowTransition", flowTransitionBO.getFistToTransition(flow));
			model.addAttribute("flowList", flowDefineBO.findByFlowTypeId(flow.getFlowTypeId()));
			doPrepareSpecialData(request,model);
		}
		return getTaskInput(); 
	}
	
}
