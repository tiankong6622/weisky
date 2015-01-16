package com.hz.yisheng.oa.workflow.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQueryUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBizDataBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskRecordBO;
import com.hz.yisheng.oa.workflow.orm.WorkTask;
import com.hz.yisheng.oa.workflow.orm.WorkTaskRecord;
import com.hz.yisheng.oa.workflow.tools.MoreActorUtils;

/**
 * 项目工单 工作任务相关
 * @author WeiSky
 *
 */
@Controller
@RequestMapping(value = "/projectWorktask")
public class ProjectWorkTaskController extends BaseController {

	@Autowired
	private WorkTaskBO workTaskBO;
	@Autowired
	private WorkTaskRecordBO workTaskRecordBO;
	@Autowired
	private WorkTaskBizDataBO workTaskBizDataBO;
	
	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryMap =  PageQueryUtils.preparePage(request);
		queryMap.put("projectId", SessionHolder.getProjectId());
		Page<WorkTask> page = workTaskBO.pageQuery(queryMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 我提交的工单
	 * @param taskType
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/mySubmitTask")
	public void mySubmitTask(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryMap =  PageQueryUtils.preparePage(request);
		queryMap.put("submitor", SessionHolder.getProjectUserId());
		queryMap.put("projectId", SessionHolder.getProjectId());
		Page<WorkTask> page = workTaskBO.pageQuery(queryMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 当前处理人是我的工单 代办事项
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/myTask/{taskType}")
	public void myTask(@PathVariable("taskType") String taskType, HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryMap =  PageQueryUtils.preparePage(request);
		long actor = SessionHolder.getProjectUserId();
		queryMap.put("actor", actor);
		queryMap.put("projectId", SessionHolder.getProjectId());
		String moreSql = MoreActorUtils.getProjectMoreActorSql(SessionHolder.getProjectId(),actor);
		if(StringUtils.isNotBlank(moreSql)){
			queryMap.put("needMore", "1");
			queryMap.put("moreSql", moreSql);
		}
		queryMap.put("status", WorkTask.STATUS_HOLD);
		if(StringUtils.isNotBlank(taskType)){
			String[] arr = taskType.split("@@");
			List<String> list = Arrays.asList(arr);
			queryMap.put("taskTypes", list);
		}
		Page<WorkTask> page = workTaskBO.pageQuery(queryMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 工单处理记录
	 * @param taskId
	 * @param response
	 */
	@RequestMapping(value = "/taskRecords/{taskId}")
	public void taskRecords(@PathVariable("taskId") Long taskId,HttpServletResponse response){
		List<WorkTaskRecord> records = workTaskRecordBO.getTaskRecords(taskId);
		JsonPageUtils.renderJsonPage(Long.valueOf(records.size()), records, response);
	}
}
