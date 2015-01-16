package com.hz.yisheng.handchain.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JSONUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.javafans.web.springmvc.controller.WebControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.yisheng.handchain.bo.KnowledgeStockBO;
import com.hz.yisheng.handchain.orm.KnowledgeStockBean;
import com.hz.yisheng.handchain.orm.StagePlate;
import com.hz.yisheng.webdata.SessionHolder;

/***
 * 管理知识库
 * 
 * @author chengxingju­
 *
 */

@Controller
@RequestMapping(value = "/handbook/knowledge")
public class KnowledgeStockController extends BaseController {
	@Autowired
	private KnowledgeStockBO stockBO;
	@Autowired
	private KnowledgeStockBO knowledgebo;

	/**
	 * 分页查询数据记录
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		final Map<String, Object> sqlMap = QueryParamUtils
				.builderQueryMap(request);// 组装查询参数
		Page<KnowledgeStockBean> page = WebControllerUtils.preparePage(request,
				10);// 组装page对象
		page = stockBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(),
				response);
	}
	
	@RequestMapping("/list2")
	public void list2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Map<String,Object> param = QueryParamUtils.builderQueryMap(request);// 组装查询参数
		AjaxUtils.renderJson(response, knowledgebo.list(param));
	}

	/**
	 * 异步获取单个用户
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getKnowledge/{id}")
	public String getKnowledge(@PathVariable("id") Long id,
			HttpServletResponse response, Model model) {
		KnowledgeStockBean role = stockBO.findById(id);
		model.addAttribute("knowledge", role);
		return "/xxxx";
	}

	@RequestMapping("/preview-update")
	public String previewUpdate(
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (id != null) {
			KnowledgeStockBean ks = stockBO.findById(id);
			List<StagePlate> stageList = knowledgebo.listStageInfo();
			request.setAttribute("stagelist", stageList);
			// 获取数据库中现有sicktime
			List<StagePlate> ssLIST = knowledgebo.findSonInfoByFname(ks
					.getSicktime());
			request.setAttribute("platelist", ssLIST);
			model.addAttribute("stock", ks);
		} else {
			KnowledgeStockBean kb = new KnowledgeStockBean();
			List<StagePlate> stageList = knowledgebo.listStageInfo();
			request.setAttribute("stagelist", stageList);
			model.addAttribute("stock", kb);
		}
		return "/handbook/knowledgeStock";
	}

	@RequestMapping("/look")
	public String lookKnowlwdge(
			@RequestParam(value = "id", required = false) Long id,
			HttpServletResponse response, Model model) {
		if (id != null) {
			KnowledgeStockBean ks = stockBO.findById(id);
			model.addAttribute("stock", ks);
		} else {

		}
		return "/handbook/lookKnowledgeStock";
	}

	/**
	 * 删除记录
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,
			HttpServletResponse response, Model model) {
		try {
			stockBO.delete(id);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	/**
	 * 新增数据记录并保存
	 * 
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute KnowledgeStockBean knowledgestock,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		try {
			if (knowledgestock.getId() == null) {
				Object stageid = request.getParameter("stageId");
				if (stageid != null && !stageid.equals("")) {
					String stageId = stageid.toString();
					StagePlate stage = stockBO.findFname(Long
							.parseLong(stageId));
					if (stage != null) {
						knowledgestock.setSicktime(stage.getName());
					}
				} else {
					knowledgestock.setSicktime("");
				}
				Object plateid = request.getParameter("plateId");
				if (plateid != null && !plateid.equals("")) {
					String plateId = plateid.toString();
					StagePlate plate = stockBO.findFname(Long
							.parseLong(plateId));
					if (plate != null) {
						knowledgestock.setSicktype(plate.getName());
					}
				} else {
					knowledgestock.setSicktype("");
				}
				knowledgestock.setCreator(SessionHolder.getAdminUserId());
				stockBO.insertKnowlwdge(knowledgestock);
			} else {
				Object stageid = request.getParameter("stageId");
				if (stageid != null && !stageid.equals("")) {
					String stageId = stageid.toString();
					StagePlate stage = stockBO.findFname(Long
							.parseLong(stageId));
					if (stage != null) {
						knowledgestock.setSicktime(stage.getName());
					}
				} else {
					knowledgestock.setSicktime("");
				}
				Object plateid = request.getParameter("plateId");
				if (plateid != null && !plateid.equals("")) {
					String plateId = plateid.toString();
					StagePlate plate = stockBO.findFname(Long
							.parseLong(plateId));
					if (plate != null) {
						knowledgestock.setSicktype(plate.getName());
					}
				} else {
					knowledgestock.setSicktype("");
				}
				knowledgestock.setUpdater(SessionHolder.getAdminUserId());
				stockBO.updateKnowledge(knowledgestock);
			}
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("messager", "fail");
		}
		return "/handbook/knowledgeStock";
	}

	/**
	 * 前台ajax请求返回数据
	 */
	@RequestMapping("/check")
	public void check(HttpServletRequest request, HttpServletResponse response) {
		List<StagePlate> stageList = knowledgebo.listStageInfo();
		if (stageList.size() > 0) {
			AjaxUtils.renderJson(response, stageList);
		} else {
			stageList = null;
			AjaxUtils.renderJson(response, stageList);
		}
	}

	/**
	 * 前台ajax请求根据父亲节点回去相应的孩子节点
	 * 
	 */
	@RequestMapping("/validxx")
	public void valid(HttpServletRequest request, HttpServletResponse response) {
		String platestr = "";
		Object plate = request.getParameter("plate");
		if (plate != null&&!plate.equals("")) {
			platestr = plate.toString();
		}
		List<StagePlate> plateList = knowledgebo.findSonInfoByFname(platestr);
		if (plateList.size() > 0) {
			AjaxUtils.renderJson(response, plateList);
		} else {
			plateList = null;
			AjaxUtils.renderJson(response, plateList);
		}

	}
}
