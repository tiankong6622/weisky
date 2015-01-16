package com.hz.yisheng.oa.workflow.orm;

import org.javafans.orm.entity.base.BaseAdminEntity;

/**
 * 多人临时处理同一条工单
 * @author WeiSKY
 *
 */
public class TempNodeSql extends BaseAdminEntity{

	private static final long serialVersionUID = -848320109073712434L;
	
	private Long nodeId;//工单ID
	private String nodeSql;//查询用的sql
	private Long projectId;//项目ID
	
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeSql() {
		return nodeSql;
	}
	public void setNodeSql(String nodeSql) {
		this.nodeSql = nodeSql;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}
