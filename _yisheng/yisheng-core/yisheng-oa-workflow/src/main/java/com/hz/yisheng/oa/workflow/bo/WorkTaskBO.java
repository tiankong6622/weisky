package com.hz.yisheng.oa.workflow.bo;

import java.util.List;
import java.util.Map;

import org.javafans.common.utils.number.ToNumberUtils;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.hz.yisheng.admin.bo.AdminUserBO;
import com.hz.yisheng.admin.pojo.AdminUser;
import com.hz.yisheng.oa.workflow.constants.FlowTransform;
import com.hz.yisheng.oa.workflow.dao.WorkTaskMapper;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.FlowTransition;
import com.hz.yisheng.oa.workflow.orm.WorkTask;

/**
 * 工单业务层
 * @author WeiSky
 *
 */
@Service
public class WorkTaskBO {

	@Autowired
	private WorkTaskMapper workTaskMapper;
	@Autowired
	private FlowNodeBO flowNodeBO;
	@Autowired
	private FlowTransitionBO flowTransitionBO;
	@Autowired
	private AdminUserBO adminUserBO;
	@Autowired
	private WorkTaskRecordBO workTaskRecordBO;
	@Autowired
	private WorkTaskBizDataBO workTaskBizDataBO;
	@Autowired
	private FlowDefineBO flowDefineBO;

	/**
	 * 插入
	 * 
	 * @param task
	 */
	public void insert(WorkTask task) {
		workTaskMapper.insert(task);
	}

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 */
	public WorkTask getById(long id) {
		WorkTask task = workTaskMapper.getById(id);
		return task;
	}

	/**
	 * 更新工单
	 * 
	 * @param task
	 */
	public void update(WorkTask task) {
		workTaskMapper.update(task);
	}

	/**
	 * 查询已审批工单列表
	 * 
	 * @param sqlMap
	 * @return
	 */
	public Page<WorkTask> pageQueryHistory(final Map<String, Object> sqlMap) {
		Page<WorkTask> page = PageQueryUtils.pageQuery(sqlMap, new PageQuery<WorkTask>() {
			public long count() {
				return workTaskMapper.countHistory(sqlMap);
			}

			public List<WorkTask> list() {
				return workTaskMapper.listHistory(sqlMap);
			}
		});
		Long projectId = ToNumberUtils.getLongValue(sqlMap.get("projectId"));
		boolean isProject = (projectId != null && projectId > 0L);
		return makePage(page, isProject);
	}

	/**
	 * 分页查询
	 * 
	 * @param queryMap
	 * @return
	 */
	public Page<WorkTask> pageQuery(final Map<String, Object> queryMap) {
		Page<WorkTask> page = PageQueryUtils.pageQuery(queryMap, new PageQuery<WorkTask>() {
			public long count() {
				return workTaskMapper.count(queryMap);
			}

			public List<WorkTask> list() {
				return workTaskMapper.list(queryMap);
			}
		});
		Long projectId = ToNumberUtils.getLongValue(queryMap.get("projectId"));
		boolean isProject = (projectId != null && projectId > 0L);
		return makePage(page, isProject);
	}

	public Page<WorkTask> makePage(Page<WorkTask> page, Boolean isProject) {
		if (!page.getResult().isEmpty()) {
			List<Long> currentNodeIds = Lists.transform(page.getResult(), FlowTransform.getTaskCurrentNodeIds);
			List<Long> transitionIds = Lists.transform(page.getResult(), FlowTransform.getFromTransitionIds);
			List<Long> actors = Lists.transform(page.getResult(), FlowTransform.getActors);
			List<Long> submiters = Lists.transform(page.getResult(), FlowTransform.getSubmiters);
			List<Long> flowIds = Lists.transform(page.getResult(), FlowTransform.getFlowIds);
			List<AdminUser> actorUsers = null;
			List<AdminUser> submitUsers = null;
			actorUsers = adminUserBO.getUserByIds(actors);
			submitUsers = adminUserBO.getUserByIds(submiters);

			List<FlowTransition> transitions = flowTransitionBO.getByIds(transitionIds);
			List<FlowDefine> flows = this.flowDefineBO.getByIds(flowIds);
			List<FlowNode> flowNodes = flowNodeBO.getByIds(currentNodeIds);
			for (WorkTask task : page.getResult()) {
				for (FlowNode node : flowNodes) {
					if (task.getCurrentNode().equals(node.getId())) {
						task.setNode(node);
						task.setNodeName(node.getName());
						break;
					}
				}
				for (FlowDefine flow : flows) {
					if (task.getFlowId().equals(flow.getId())) {
						task.setFlowName(flow.getName());
						break;
					}
				}

				for (FlowTransition ft : transitions) {
					if (task.getFromTransition().equals(ft.getId())) {
						task.setFromTransitionName(ft.getName());
						break;
					}
				}
				for (AdminUser au : actorUsers) {
					if (task.getActor().equals(au.getId())) {
						task.setActorName(au.getUserName());
						break;
					}
				}
				for (AdminUser au : submitUsers) {
					if (task.getSubmitor().equals(au.getId())) {
						task.setSubmitterName(au.getUserName());
						break;
					}
				}
			}
		}
		return page;
	}

	public void delete(Long taskId) {
		workTaskMapper.delete(taskId);
	}

}
