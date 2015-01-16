package com.hz.yisheng.oa.workflow.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQueryUtils;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.yisheng.oa.workflow.bo.WorkTaskBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBizDataBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskRecordBO;
import com.hz.yisheng.oa.workflow.orm.WorkTask;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizData;
import com.hz.yisheng.oa.workflow.orm.WorkTaskRecord;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 工单控制器
 * @author WeiSky
 *
 */
@Controller
@RequestMapping(value = "/worktask")
public class WorkTaskController extends BaseController {
	
	@Autowired
	private WorkTaskBO workTaskBO;
	@Autowired
	private WorkTaskRecordBO workTaskRecordBO;
	@Autowired
	private WorkTaskBizDataBO workTaskBizDataBO;

	@RequestMapping(value = "/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryMap =  PageQueryUtils.preparePage(request);
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
		queryMap.put("submitor", SessionHolder.getAdminUserId());
		Page<WorkTask> page = workTaskBO.pageQuery(queryMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 我提交的工单列表
	 * @param taskType
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/mySubmitTasks/{taskType}")
	public void mySubmitTasks(@PathVariable("taskType") String taskType, 
			HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryMap =  PageQueryUtils.preparePage(request);
		queryMap.put("submitor", SessionHolder.getAdminUserId());
		if(!taskType.isEmpty() && !"".equals(taskType)){
			String[] arr = taskType.split("@@");
			List<String> list = Arrays.asList(arr);
			queryMap.put("taskTypes", list);
		}	
		Page<WorkTask> page = workTaskBO.pageQuery(queryMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	/**
	 * 获取单个工单
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getMySubmitTask")
	public void getMySubmitTask(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> queryMap =  PageQueryUtils.preparePage(request);
		queryMap.put("submitor", SessionHolder.getAdminUserId());
		List<WorkTaskBizData> list = workTaskBizDataBO.getByTypeId(queryMap);
		queryMap.put("id", list.get(0).getWorkTaskId());
		Page<WorkTask> page = workTaskBO.pageQuery(queryMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(),page.getResult(), response);
	}
	
	/**
	 * 当前处理人是我的工单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/myTask/{taskType}")
	public void myTask(@PathVariable("taskType") String taskType, HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> queryMapPage =  PageQueryUtils.preparePage(request);
		final Map<String,Object> queryMap = QueryParamUtils.builderQueryMap(request);
		long actor = SessionHolder.getAdminUserId();

		queryMap.put("actor", actor);		
		queryMap.put("rowStart", queryMapPage.get("rowStart"));
		queryMap.put("pageSize", queryMapPage.get("pageSize"));
		queryMap.put("pageNo", queryMapPage.get("pageNo"));

		//String moreSql = MoreActorUtils.getMoreActorSql(actor);
		//if(StringUtils.isNotBlank(moreSql)){
		//	queryMap.put("needMore", "1");
		//	queryMap.put("moreSql", moreSql);
		//}
		queryMap.put("status", WorkTask.STATUS_HOLD);
		if(!taskType.isEmpty() && !"".equals(taskType)){
			String[] arr = taskType.split("@@");
			List<String> list = Arrays.asList(arr);
			queryMap.put("taskTypes", list);
		}		
		
		Page<WorkTask> page = workTaskBO.pageQuery(queryMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	
	@RequestMapping(value = "/taskRecords/{taskId}")
	public void taskRecords(@PathVariable("taskId") Long taskId,HttpServletResponse response){
		List<WorkTaskRecord> records = workTaskRecordBO.getTaskRecords(taskId);
		JsonPageUtils.renderJsonPage(Long.valueOf(records.size()), records, response);
	}
	
	@RequestMapping(value = "/print/{baseDir}/{taskId}")
	public String print(@PathVariable("baseDir") String baseDir,@PathVariable("taskId") Long taskId,Model model){
		WorkTask task =  workTaskBO.getById(taskId);
		List<WorkTaskRecord> records = workTaskRecordBO.getTaskRecords(taskId);
		//工单处理记录
		model.addAttribute("workTaskRecords", records);
		model.addAttribute("task",task);
		model.addAttribute("records", workTaskRecordBO.getTaskRecords(taskId));
		model.addAttribute("bizData", workTaskBizDataBO.getMainBizDatas(taskId));
		return baseDir+"/print/"+task.getTaskType();
	}
	

	@RequestMapping("/delete/{taskId}")
	public void delete(@PathVariable("taskId") Long taskId,HttpServletResponse response){
		try{
			workTaskBO.delete(taskId);
			AjaxUtils.renderText("1");
		}catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText("0");
		}
	}
	

	//修改状态
	@RequestMapping(value = "/update/extStatus/{taskId}/{extStatus}")
	public void updatePrintStatus(@PathVariable("taskId") Long taskId,@PathVariable("extStatus") Integer extStatus,Model model,HttpServletResponse response){
		try {
			WorkTask task = new WorkTask();
			task.setId(taskId);
			task.setExtStatus(extStatus);
			workTaskBO.update(task);
			AjaxUtils.renderText("success");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText("fail");
		}
		
		
	}
}
