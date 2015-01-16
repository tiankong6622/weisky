package com.hz.yisheng.oa.workflow.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.dto.page.PageQueryUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.admin.utils.JSONUtils;
import com.hz.yisheng.oa.workflow.bo.WorkTaskBizDataConfigBO;
import com.hz.yisheng.oa.workflow.constants.DialogMode;
import com.hz.yisheng.oa.workflow.dto.TypeJson;
import com.hz.yisheng.oa.workflow.orm.WorkTaskBizDataConfig;

@Controller
@RequestMapping("/config")
public class WorkTaskBizDataConfigController extends BaseController {

	@Autowired
	protected WorkTaskBizDataConfigBO workTaskBizDataConfigBO;

	public static final String FLOW_CONFIGDLG = "/flow/configDlg";

	/**
	 * 获取基础数据列表
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/list")
	public void list(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> params = PageQueryUtils.preparePage(request);
		// 获取列表数据
		List<WorkTaskBizDataConfig> list = workTaskBizDataConfigBO
				.findPageDataByMap(params);
		// 获取数据个数
		Long count = workTaskBizDataConfigBO.findAllCount(params);

		JSONUtils.rendJsonPage(count, list, response);
	}

	/**
	 * 跳转新增，编辑页面
	 * 
	 * @param mode
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{mode}/dispatch")
	public String dispatch(@PathVariable String mode,
			@RequestParam(required = false) Long id, Model model) {
		model.addAttribute("mode", mode);
		if (DialogMode.EDIT_MODE.equals(mode)) {
			WorkTaskBizDataConfig config = workTaskBizDataConfigBO.getById(id);
			model.addAttribute("config", config);
		}
		return FLOW_CONFIGDLG;
	}

	/**
	 * 新增
	 * 
	 * @param workTaskBizDataConfig
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/add")
	public void add(
			@ModelAttribute WorkTaskBizDataConfig workTaskBizDataConfig,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			workTaskBizDataConfigBO.add(workTaskBizDataConfig);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	/**
	 * 修改
	 * 
	 * @param workTaskBizDataConfig
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/update")
	public void update(
			@ModelAttribute WorkTaskBizDataConfig workTaskBizDataConfig,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			workTaskBizDataConfigBO.update(workTaskBizDataConfig);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	/**
	 * 返回combobox所使用的时间json
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/choiceFlow")
	public void choiceFlow(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			List<WorkTaskBizDataConfig> list = workTaskBizDataConfigBO
					.choiceFlow();
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			if (null != list && list.size() > 0) {
				for (WorkTaskBizDataConfig lt : list) {
					sb.append("{");
					sb.append("\"value\":\"" + lt.getId() + ","
							+ lt.getDataObjType() + "\",");
					sb.append("\"text\":\"" + lt.getDataObjTypeName() + "\"");
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

	/**
	 * 返回combobox所使用的状态json
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/choiceStatus")
	public void choiceStatus(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			List<TypeJson> list = workTaskBizDataConfigBO.choiceStatus();
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			if (null != list && list.size() > 0) {
				for (TypeJson lt : list) {
					sb.append("{");
					sb.append("\"value\":\"" + lt.getId() + "\",");
					sb.append("\"text\":\"" + lt.getText() + "\"");
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
