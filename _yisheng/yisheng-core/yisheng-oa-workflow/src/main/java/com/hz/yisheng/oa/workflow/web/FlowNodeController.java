package com.hz.yisheng.oa.workflow.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.javafans.dao.jdbc.JdbcHolder;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.hz.yisheng.admin.bo.AdminDepartBO;
import com.hz.yisheng.admin.bo.AdminDepartUserBO;
import com.hz.yisheng.admin.bo.AdminJobBO;
import com.hz.yisheng.admin.pojo.AdminDepart;
import com.hz.yisheng.admin.pojo.AdminDepartUser;
import com.hz.yisheng.admin.pojo.AdminUserJob;
import com.hz.yisheng.oa.workflow.bo.FlowNodeBO;
import com.hz.yisheng.oa.workflow.bo.TempNodeSqlBO;
import com.hz.yisheng.oa.workflow.orm.FlowNode;
import com.hz.yisheng.oa.workflow.orm.TempNodeSql;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 流程节点管理控制器
 * 
 * @author WeiSky
 * 
 */
@Controller
@RequestMapping(value = "/flow/node")
public class FlowNodeController extends BaseController {

	@Autowired
	private FlowNodeBO flowNodeBO;
	/*
	 * @Autowired private KVConfigBO kVConfigBO;
	 */
	@Autowired
	private AdminDepartUserBO adminDepartUserBO;
	@Autowired
	private AdminJobBO adminJobBO;
	@Autowired
	private AdminDepartBO adminDepartBO;
	@Autowired
	private TempNodeSqlBO tempNodeSqlBO;

	/**
	 * 获取所有的流程信息
	 * 
	 * @param flowId
	 * @param response
	 */
	@RequestMapping("/list/{flowId}")
	public void list(@PathVariable("flowId") Long flowId,
			HttpServletResponse response) {
		List<FlowNode> list = flowNodeBO.getByFlowId(flowId);
		JsonPageUtils.renderJsonPage(Long.valueOf(list.size()), list, response);
	}

	/**
	 * 调整节点管理页面
	 * 
	 * @param flowId
	 * @param model
	 * @return
	 */
	@RequestMapping("/manage/{flowId}")
	public String manage(@PathVariable("flowId") Long flowId, Model model) {
		model.addAttribute("flowId", flowId);
		return "/flow/nodes";
	}

	/**
	 * 修改节点筛选受理人SQL
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getFilterActorCondition")
	public void getFilterActorCondition(@RequestParam("id") Long id,
			HttpServletResponse response) {
		AjaxUtils.renderText(response,
				flowNodeBO.getFlowActorFilterCondition(id));
	}

	/**
	 * 更新受理人信息
	 * 
	 * @param node
	 * @param response
	 */
	@RequestMapping("/updateFilterActorCondition")
	public void updateFilterActorCondition(@ModelAttribute FlowNode node,
			HttpServletResponse response) {
		AjaxUtils.renderText(
				response,
				String.valueOf(flowNodeBO.updateFlowActorFilterCondition(
						node.getId(), node.getActorFilterCondition())));
	}

	/**
	 * 获取被选择的受理人
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getSelectActors")
	@SuppressWarnings("unchecked")
	public void getSelectActors(@RequestParam("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			Map<String, Object> sqlMap = new HashMap<String, Object>();

			Long actor = SessionHolder.getAdminUserId();
			AdminDepartUser auo = adminDepartUserBO.getByUserId(actor);
			AdminUserJob job = adminJobBO.getOneAdminUserJobByUserId(actor);
			if (auo != null) {
				sqlMap.put("orgId", adminDepartUserBO.getByUserId(actor));
				sqlMap.put("postId", job.getId());
				sqlMap.put("actor", actor);
				AdminDepart org = adminDepartBO.getAdminDepart(auo
						.getDepartId());
				sqlMap.put("fullOrgPath", org.getFullIdPath());
				String[] arr = StringUtils.split(org.getFullIdPath().trim(),
						"/");
				List<Long> orgIds = Lists.newArrayListWithCapacity(arr.length);
				for (int i = 0; i < arr.length; i++) {
					if (NumberUtils.isDigits(arr[i])) {
						orgIds.add(Long.parseLong(arr[i]));
					}
				}
				sqlMap.put("orgIds", orgIds);
				sqlMap.put("parentOrgId", org.getParentId());
			}
			// queryParam格式用 A@@L@@B;A1@@S@@B1 格式 第一个代表 查询key 第二个代表 类型 L long S
			// string
			String queryParam = request.getParameter("queryParam");
			if (StringUtils.isNotBlank(queryParam)) {
				String[] arr = StringUtils.split(StringUtils.trim(queryParam),
						";");
				for (String s : arr) {
					if (StringUtils.indexOf(s, "@@") != -1) {
						String[] arr2 = StringUtils.split(s, "@@");
						if (arr2.length == 3) {
							String key = arr2[0];
							String type = arr2[1];
							String value = arr2[2];
							if (type.equals("L")) {// long 类型的
								sqlMap.put(key, Long.parseLong(value));
							}
							// 其他类型扩展
						}
					}
				}
			}
			// String dataSource = kVConfigBO.getConfigValue("adminDataSource");
			String dataSource = "dataSource";
			String sql = flowNodeBO.getFlowActorFilterCondition(id);
			List<Map<String, Object>> list = StringUtils.isNotBlank(sql) ? JdbcHolder
					.doQuery(dataSource, sql, sqlMap) : Collections.EMPTY_LIST;
			JsonPageUtils.renderJsonPage(Long.valueOf(list.size()), list,
					response);
		} catch (Exception e) {
			AjaxUtils.renderText(response, "-1");
		}
	}

	@RequestMapping("/moreActionConditionSave/{nodeId}")
	public void moreActionConditionSave(@PathVariable("nodeId") Long nodeId,
			HttpServletResponse response) {
		try {
			String sql = tempNodeSqlBO.getByNodId(nodeId);
			TempNodeSql tns = new TempNodeSql();
			tns.setNodeId(nodeId);
			tns.setNodeSql(flowNodeBO.getFlowActorFilterCondition(nodeId));
			if (sql == null || sql.length() <= 0) {
				tempNodeSqlBO.insert(tns);
			} else {
				tempNodeSqlBO.update(tns);
			}
			AjaxUtils.renderText("1");
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText("0");
		}
	}

	public void ccc() {
		try {
			// upate
			// insert
			AjaxUtils.renderText("success");
		} catch (Exception e) {
			AjaxUtils.renderText("falis");
		}
	}

}
