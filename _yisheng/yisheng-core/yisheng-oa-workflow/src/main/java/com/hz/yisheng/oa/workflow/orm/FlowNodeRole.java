package com.hz.yisheng.oa.workflow.orm;

import java.io.Serializable;

/**
 * 流程节点允许的角色ID 
 * @author WeiSky
 *
 */
public class FlowNodeRole implements Serializable{
	
	private static final long serialVersionUID = 6812111613438706400L;
	
	private Long id;
	private Long nodeId;
	private Long roleId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
