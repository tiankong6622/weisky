package com.hz.yisheng.handchain.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
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

/***
 * 管理知识库
 * @author chengxingju­
 *
 */

@Controller
@RequestMapping(value = "/handbook/knowledge")
public class KnowledgeStockController extends BaseController{
	@Autowired
	private KnowledgeStockBO stockBO;
	
	/**
	 * 分页查询数据记录
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		final Map<String,Object> sqlMap = QueryParamUtils.builderQueryMap(request);//组装查询参数
		Page<KnowledgeStockBean> page = WebControllerUtils.preparePage(request,10);//组装page对象
		page = stockBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(), response);
	}
	/**
	 * 异步获取单个用户
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getKnowledge/{id}")
	public String getKnowledge(@PathVariable("id") Long id,HttpServletResponse response, Model model){
		KnowledgeStockBean role = stockBO.findById(id);
		model.addAttribute("knowledge", role);
		return "/xxxx";
	}
	
	
	@RequestMapping("/preview-update")
	public String previewUpdate(@RequestParam(value="id",required=false) Long id,
			HttpServletResponse response,Model model){
		if(id != null){
			KnowledgeStockBean ks = stockBO.findById(id);
			model.addAttribute("stock", ks);
		}else{
			KnowledgeStockBean kb = new KnowledgeStockBean();
			model.addAttribute("stock",kb);
		}
		return "/handbook/knowledgeStock";
	}
	
	@RequestMapping("/look")
	public String lookKnowlwdge(@RequestParam(value="id",required=false) Long id,
			HttpServletResponse response,Model model){
		if(id != null){
			KnowledgeStockBean ks = stockBO.findById(id);
			model.addAttribute("stock", ks);
		}else{
			
		}
		return "/handbook/lookKnowledgeStock";
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response, Model model){
	try{
		stockBO.delete(id);
		JSONUtils.objToJSON(response, 1);
	}catch(Exception e){
		e.printStackTrace();
		JSONUtils.objToJSON(response, 0);
	}
	}
	
	
	/**
	 * 新增数据记录并保存
	 * 
	 */
	@RequestMapping("/save")
	public String save(@ModelAttribute KnowledgeStockBean knowledgestock,HttpServletRequest request, HttpServletResponse response,Model model){
		try{
		 if(knowledgestock.getId()==null){
			 stockBO.insertKnowlwdge(knowledgestock);
		 }else{
			 stockBO.updateKnowledge(knowledgestock);
		 }
		 model.addAttribute("messager", "success");
		}catch(Exception e){
			e.printStackTrace();
			 model.addAttribute("messager", "fail");
		}
		return "/handbook/knowledgeStock";
	}
}
