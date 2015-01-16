package com.hz.yisheng.oa.workflow.dto;

import java.io.Serializable;

/**
 * 流程处理的一些参数
 */
public class FlowParam implements Serializable{
	
	private static final long serialVersionUID = -5949513314844941353L;
	
	public static final Integer BIZ_DATA_DEAL_TYPE_MAIN=1;//用主流程数据
	public static final Integer BIZ_DATA_DEAL_TYPE_RESET=2;//删掉主流程数据 重新产生主流程数据
	public static final Integer BIZ_DATA_DEAL_TYPE_NEW=3;//当前节点产生新的主流程数据
	
	private Long submiter;// 流程提交人
	private Long actor;// 流程处理人 提交流程给哪个人处理
	private String flowname;// 流程名称
	private Long toTranstaionId;// 工单创建的时候要提交给谁
	private String dealContent;// 处理意见
	private Long workTaskId;//工单ID
	private boolean fistTaskNodeComplete=true;//第一个任务节点是否直接流
	private boolean finished=false;//该节点是否处理完了 没处理完任务节点停留 为true 流程往下走
	private Integer nodeBizDataDealType=BIZ_DATA_DEAL_TYPE_MAIN;//每个节点对新数据的处理要求
	private Long projectId;//项目ID
	private boolean fromInterface=false;//是从接口调过来的还是pc端来的 不同的地方 取用户信息不一样
	
	public Long getSubmiter() {
		return submiter;
	}
	public void setSubmiter(Long submiter) {
		this.submiter = submiter;
	}
	public Long getActor() {
		return actor;
	}
	public void setActor(Long actor) {
		this.actor = actor;
	}
	public String getFlowname() {
		return flowname;
	}
	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}
	public Long getToTranstaionId() {
		return toTranstaionId;
	}
	public void setToTranstaionId(Long toTranstaionId) {
		this.toTranstaionId = toTranstaionId;
	}
	public String getDealContent() {
		return dealContent;
	}
	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}
	public Long getWorkTaskId() {
		return workTaskId;
	}
	public void setWorkTaskId(Long workTaskId) {
		this.workTaskId = workTaskId;
	}
	public boolean isFistTaskNodeComplete() {
		return fistTaskNodeComplete;
	}
	public void setFistTaskNodeComplete(boolean fistTaskNodeComplete) {
		this.fistTaskNodeComplete = fistTaskNodeComplete;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public Integer getNodeBizDataDealType() {
		return nodeBizDataDealType;
	}
	public void setNodeBizDataDealType(Integer nodeBizDataDealType) {
		this.nodeBizDataDealType = nodeBizDataDealType;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public boolean isFromInterface() {
		return fromInterface;
	}
	public void setFromInterface(boolean fromInterface) {
		this.fromInterface = fromInterface;
	}
}
