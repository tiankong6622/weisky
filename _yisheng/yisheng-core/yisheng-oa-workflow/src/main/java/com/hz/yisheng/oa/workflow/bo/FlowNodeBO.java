package com.hz.yisheng.oa.workflow.bo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.javafans.common.utils.number.ToNumberUtils;
import org.javafans.common.utils.random.RandomUtils;
import org.javafans.dao.jdbc.JdbcHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hz.yisheng.admin.bo.AdminDepartBO;
import com.hz.yisheng.admin.bo.AdminDepartUserBO;
import com.hz.yisheng.admin.bo.AdminJobBO;
import com.hz.yisheng.admin.pojo.AdminDepart;
import com.hz.yisheng.admin.pojo.AdminDepartUser;
import com.hz.yisheng.admin.pojo.AdminUserJob;
import com.hz.yisheng.oa.workflow.dao.FlowNodeMapper;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;

/**
 * 流程节点BO
 * 
 * @author WeiSky
 * 
 */
@Service
public class FlowNodeBO {

	@Autowired
	private FlowNodeMapper flowNodeMapper;
	@Autowired
	private FlowTransitionBO flowTransitionBO;
	@Autowired
	private FlowDefineBO flowDefineBO;
	@Autowired
	private AdminDepartUserBO adminDepartUserBO;
	@Autowired
	private AdminJobBO adminJobBO;
	@Autowired
	private AdminDepartBO adminDepartBO;

	/**
	 * 根据ID，获取信息
	 * 
	 * @param id
	 * @return
	 */
	public FlowNode getById(long id) {
		return flowNodeMapper.getById(id);
	}

	public List<FlowNode> getByFlowId(long flowId) {
		return flowNodeMapper.getByFlowId(flowId);
	}

	/**
	 * 批量插入
	 * 
	 * @param list
	 */
	public void batchInsert(List<FlowNode> list) {
		flowNodeMapper.batchInsert(list);
	}

	/**
	 * 根据IDS，获取信息
	 * 
	 * @param ids
	 * @return
	 */
	public List<FlowNode> getByIds(List<Long> ids) {
		return flowNodeMapper.getByIds(ids);
	}

	/**
	 * 修改节点筛选受理人SQL
	 * 
	 * @param id
	 * @return
	 */
	public String getFlowActorFilterCondition(Long id) {
		return null != flowNodeMapper.getFlowActorFilterCondition(id) ? flowNodeMapper
				.getFlowActorFilterCondition(id) : "";
	}

	/**
	 * 更新受理人信息
	 * 
	 * @param id
	 * @param actorFilterCondition
	 * @return
	 */
	public int updateFlowActorFilterCondition(Long id,
			String actorFilterCondition) {
		return flowNodeMapper.updateFlowActorFilterCondition(id,
				actorFilterCondition);
	}

	public String getTypeByTransition(Long transitionId) {
		FlowTransition ft = flowTransitionBO.getById(transitionId);
		return getById(ft.getToNodeId()).getType();
	}

	/**
	 * 根据流程节点ID和查询参数
	 * 
	 * @param nodeId
	 * @param sqlMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Long getActor(Long nodeId, Map<String, Object> sqlMap) {
		sqlMap = (sqlMap == null ? Collections.EMPTY_MAP : sqlMap);
		String sql = getFlowActorFilterCondition(nodeId);
		List<Map<String, Object>> list = StringUtils.isNotBlank(sql) ? JdbcHolder
				.doQuery("dataSource", sql, sqlMap) : Collections.EMPTY_LIST;
		// 选择受理人的 key 配置成 actor 或ACTOR
		if (list.size() == 0) {
			return null;
		}
		int ridx = RandomUtils.rd.nextInt(list.size());
		Map<String, Object> map = list.get(ridx);
		Long actor = ToNumberUtils.getLongValue(map.get("ACTOR"));
		if (actor == null) {
			actor = ToNumberUtils.getLongValue(map.get("actor"));
		}
		return actor;
	}

	public Long getFistNodeActor(String flowname, long projectId,
			Map<String, Object> sqlMap) {
		FlowDefine flow = flowDefineBO.getLastByProjectId(flowname, projectId);
		FlowTransition ft = flowTransitionBO.getFistToTransition(flow);
		return ft.getToNodeId();
	}

	/**
	 * 获取受理人ID
	 * 
	 * @param id
	 *            toNodeId
	 * @param userId
	 *            提交人ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Long findActorByToNodeIdAndUserId(Long id, Long userId) {
		Long actor = null;
		Map<String, Object> params = new HashMap<String, Object>();
		AdminDepartUser auo = adminDepartUserBO.getByUserId(userId);
		AdminUserJob job = adminJobBO.getOneAdminUserJobByUserId(userId);
		if (auo != null) {
			params.put("orgId", adminDepartUserBO.getByUserId(userId));
			params.put("postId", job.getId());
			params.put("actor", userId);
			AdminDepart org = adminDepartBO.getAdminDepart(auo.getDepartId());
			params.put("fullOrgPath", org.getFullIdPath());
			String[] arr = StringUtils.split(org.getFullIdPath().trim(), "/");
			List<Long> orgIds = Lists.newArrayListWithCapacity(arr.length);
			for (int i = 0; i < arr.length; i++) {
				if (NumberUtils.isDigits(arr[i])) {
					orgIds.add(Long.parseLong(arr[i]));
				}
			}
			params.put("orgIds", orgIds);
			params.put("parentOrgId", org.getParentId());
		}
		String dataSource = "dataSource";
		String sql = getFlowActorFilterCondition(id);
		List<Map<String, Object>> list = StringUtils.isNotBlank(sql) ? JdbcHolder
				.doQuery(dataSource, sql, params) : Collections.EMPTY_LIST;
		if (null != list && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			actor = (Long) map.get("actor");
		}
		return actor;
	}
}
