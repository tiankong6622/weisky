package com.hz.yisheng.oa.workflow.dto;

import java.util.List;

import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;

public class FlowDTO {

	private FlowDefine define;
	private List<FlowNode> nodes;
	private List<FlowTransition> transitions;
	
	public FlowDefine getDefine() {
		return define;
	}
	public void setDefine(FlowDefine define) {
		this.define = define;
	}
	public List<FlowNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<FlowNode> nodes) {
		this.nodes = nodes;
	}
	public List<FlowTransition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<FlowTransition> transitions) {
		this.transitions = transitions;
	}
}
