package com.hz.yisheng.oa.workflow.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.admin.utils.JSONUtils;
import com.hz.yisheng.commondata.annotation.AdminPermissionCheck;
import com.hz.yisheng.commondata.annotation.ProjectPermissionCheck;
import com.hz.yisheng.oa.workflow.bo.FlowDefineBO;
import com.hz.yisheng.oa.workflow.bo.FlowNodeBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBO;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBizDataConfigBO;
import com.hz.yisheng.oa.workflow.orm.FlowDefine;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.WorkTask;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizDataConfig;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 流程定义控制器
 * 
 * @author WeiSky
 * 
 */
@Controller
@RequestMapping(value = "/flow/define")
public class FlowDefineController extends BaseController {

	@Autowired
	private FlowDefineBO flowDefineBO;
	@Autowired
	private FlowNodeBO flowNodeBO;
	@Autowired
	private WorkTaskBO workTaskBO;
	@Autowired
	protected WorkTaskBizDataConfigBO workTaskBizDataConfigBO;

	/**
	 * 返回自定义流程页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toCustomizeflowPage")
	public String toCustomizeflowPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<WorkTaskBizDataConfig> list = workTaskBizDataConfigBO
				.findPageDataByMap(null);
		model.addAttribute("flowNameList", list);
		return "/flow/customizeflow";
	}

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = QueryParamUtils.builderQueryMap(request);
		List<FlowDefine> list = flowDefineBO.list(param);
		JsonPageUtils.renderJsonPage(Long.valueOf(list.size()), list, response);
	}

	@RequestMapping("/listByProject")
	public void listByProject(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> param = QueryParamUtils.builderQueryMap(request);
		param.put("projectId", SessionHolder.getProjectId());
		List<FlowDefine> list = flowDefineBO.list(param);
		JsonPageUtils.renderJsonPage(Long.valueOf(list.size()), list, response);
	}

	@RequestMapping("/deployByProject")
	@ProjectPermissionCheck("flow:deploy")
	public void deployByProject(String json, HttpServletResponse response) {
		try {
			AjaxUtils.renderText(
					response,
					flowDefineBO.deployByJson(json,
							SessionHolder.getProjectId()));
		} catch (Exception e) {
			logger.error("deploy flow fail", e);
			AjaxUtils.renderText(response, "fail");
		}
	}

	@RequestMapping("/deploy")
	@AdminPermissionCheck("flow:deploy")
	public void deploy(String json, HttpServletResponse response) {
		try {
			AjaxUtils.renderText(response, flowDefineBO.deployByJson(json));
		} catch (Exception e) {
			logger.error("deploy flow fail", e);
			AjaxUtils.renderText(response, "fail");
		}
	}

	/**
	 * 自定义流程的部署
	 * 
	 * @param json
	 * @param response
	 */
	@RequestMapping("/customizeDeploy")
	public void customizeDeploy(String json, HttpServletResponse response) {
		try {
			flowDefineBO.customizeDeployByJson(json);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			logger.error("deploy flow fail", e);
			JSONUtils.objToJSON(response, 0);
		}
	}

	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") Long id,
			@RequestParam(required = false, value = "taskId") Long taskId,
			Model model) {
		if (taskId != null) {
			WorkTask task = workTaskBO.getById(taskId);
			FlowNode node = flowNodeBO.getById(task.getCurrentNode());
			model.addAttribute("node", node);
		}
		model.addAttribute("flow", flowDefineBO.getById(id));
		return "/flow/view";
	}

	/**
	 * 停止，启用流程
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	public void deleteById(@RequestParam("id") Long id,
			@RequestParam("status") Integer status, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			FlowDefine flowDefine = new FlowDefine();
			flowDefine.setId(id);
			flowDefine.setStatus(status);
			flowDefineBO.updateById(flowDefine);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception er) {
			er.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	/**
	 * 返回combobox所使用的流程json
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/choiceProcess")
	public void choiceProcess(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam("dataObjType") Integer dataObjType) {
		try {
			List<FlowDefine> list = flowDefineBO.choiceProcess(dataObjType);
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			if (null != list && list.size() > 0) {
				for (FlowDefine fd : list) {
					sb.append("{");
					sb.append("\"value\":\"" + fd.getFlowname() + "\",");
					sb.append("\"text\":\"" + fd.getName() + "\"");
					sb.append("},");
				}
				if (sb.toString().endsWith(","))
					sb = new StringBuffer(sb.toString().substring(0,
							sb.toString().length() - 1));
			}
			sb.append("]");
			PrintWriter pw = response.getWriter();
			pw.print(sb.toString());
			pw.close();
		} catch (Exception er) {
			er.printStackTrace();
			PrintWriter pw;
			try {
				pw = response.getWriter();
				pw.print("[]");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
