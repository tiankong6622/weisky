package com.hz.yisheng.handchain.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.utils.io.FileUtils;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JSONUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hz.yisheng.commondata.bo.AttachementBO;
import com.hz.yisheng.commondata.orm.Attachement;
import com.hz.yisheng.handchain.bo.KnowledgeStockBO;
import com.hz.yisheng.handchain.dao.KnowledgeStockMapper;
import com.hz.yisheng.handchain.orm.Customer;
import com.hz.yisheng.handchain.orm.StagePlate;
import com.hz.yisheng.webdata.SessionHolder;

/**
 * 知识库阶段与模块管理
 * 
 * @author chengxingju
 *
 */
@Controller
@RequestMapping(value = "/handbook/stage")
public class StageAndPlateController extends BaseController {
	@Autowired
	private KnowledgeStockMapper knowledgeMapper;
	@Autowired
	private KnowledgeStockBO knowledgebo;
	@Autowired
	private AttachementBO attachementBO;

	/***
	 * 获取所有列表信息
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/list")
	public void list(HttpServletResponse response, HttpServletRequest request) {
		List<StagePlate> getStageMenu = knowledgebo.getAllMenus();
		JSONUtils.rendJsonPage(
				getStageMenu != null && getStageMenu.size() > 0 ? Long
						.parseLong(getStageMenu.size() + "") : 0L,
				getStageMenu, response);
	}

	/**
	 * 新增记录
	 * 
	 * @param stageplate
	 * @param model
	 * @param response
	 */
	@RequestMapping("/save")
	public String insertStage(StagePlate stagePlate, Model model,
			HttpServletResponse response) {
			model.addAttribute("messager", "success");
		try {
			if (stagePlate.getId() != null) {
				knowledgebo.updateStage(stagePlate);
			} else {
				String name = stagePlate.getName();
				StagePlate stage = knowledgebo.listByFname(name);
				if (stage == null) {
					knowledgebo.insertStage(stagePlate);
					model.addAttribute("messager", "success");
				} else {
					model.addAttribute("messager", "already");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("messager", "faild");
		}
		return "/handbook/inputStage";
	}

	/**
	 * 新增菜单
	 * 
	 * @param adminMenu
	 * @param model
	 * @param response
	 */
	@RequestMapping("/saveChild")
	public String insertChildMenu(@ModelAttribute StagePlate stagePlate, Model model,@RequestParam("img") CommonsMultipartFile img,HttpServletResponse response) {
		try {
			if (stagePlate.getId() != null) {
				knowledgebo.updateStage(stagePlate);
				//修改前有图片
				if(attachementBO.findAttachementBy(stagePlate.getId(), "stage")!= null){
					if(img.getFileItem().getName()!=""){
						attachementBO.delete(attachementBO.findAttachementBy(stagePlate.getId(), "stage").getId());
						saveAttachement(img,stagePlate);
					}
				}else{
					if(img.getFileItem().getName()!=""){
						saveAttachement(img,stagePlate);
					}
				}
				model.addAttribute("messager", "success");
			} else {
				if (stagePlate.getParentId() != null) {
					stagePlate.set_parentId(stagePlate.getParentId());
					StagePlate fstageplate = knowledgebo
							.listByParentid(stagePlate.getParentId());
					stagePlate.setPlate(fstageplate.getName());
				}
				StagePlate stage = knowledgebo.listByFid(stagePlate.getName(),
						stagePlate.get_parentId());
				if (stage != null) {
					model.addAttribute("messager", "already");
				} else {
					knowledgebo.insertStage(stagePlate);
					//保存图片
					if(stagePlate.getId()!=null){
						saveAttachement(img,stagePlate);
					}
					model.addAttribute("messager", "success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("messager", "fail");
		}
		return "/handbook/addStagePlate";
	}
	
	/**
	 * 添加头像图片
	 */
	public void saveAttachement(@RequestParam("img") CommonsMultipartFile img,@ModelAttribute StagePlate stagePlate){
			Attachement attachement = new Attachement();
			attachement.setFileName(img.getFileItem().getName());
			attachement.setObjId(stagePlate.getId().toString());
			attachement.setType("stage");
			attachement.setSize(img.getSize());
			attachement.setCreaterId(SessionHolder.getAdminUserId());
			attachement.setContentType(img.getContentType());
			try {
				attachement.setPath(FileUtils.saveFile(img.getFileItem().getInputStream(), ResourceConfig.getSysConfig("fileUploadPath"), img.getFileItem().getName(), img.getFileItem().getContentType()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			attachementBO.insert(attachement);
	}

	/**
	 * 返回预添加菜单页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/preview-add")
	public String previewAdd(
			@RequestParam(value = "id", required = false) Long id, Model model,
			HttpServletResponse response, HttpServletRequest request) {
		if (id != null) {
			StagePlate am = knowledgebo.getAdminMenu(id);
			model.addAttribute("menu", am);
		} else {
			StagePlate am = new StagePlate();
			model.addAttribute("menu", am);
		}
		return "/handbook/inputStage";
	}
	
	@RequestMapping("/plate_add")
	public String plateAdd(@RequestParam(value = "id", required = false) Long id,@RequestParam(value = "parentId", required = false) Long parentId,@RequestParam(value = "flag", required = false) int flag, Model model,HttpServletResponse response, HttpServletRequest request) {
		//编辑
		if (flag==0) {
			StagePlate am = knowledgebo.getChildMenu(id);
			model.addAttribute("menu", am);
			model.addAttribute("flag", flag);
		} else{
			//新增
			model.addAttribute("parentId", parentId);
			model.addAttribute("id", null);
			model.addAttribute("flag", flag);
		}
		return "/handbook/addStagePlate";
	}

	/**
	 * 删除菜单
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		try {
			knowledgebo.deleteStage(id);
			Attachement attachement = attachementBO.findAttachementBy(id, "stage");
			if(attachement != null){
				attachementBO.deleteAll(id.toString(), "stage");
			}
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	/**
	 * 根据阶段id，查询出相对应的版块信息
	 * 
	 * @param stageId
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/valid")
	public void valid(HttpServletRequest request, HttpServletResponse response) {
		Long stageid = 0l;
		List<StagePlate> plateList  = new ArrayList<StagePlate>();
		Object stageId = request.getParameter("id");
		if (stageId != null&&!stageId.equals("")) {
			stageid = Long.parseLong(stageId.toString());
			System.out.println(stageId.toString());
		}
		if (!stageid.equals("")&&stageid!=0l) {
			plateList = knowledgebo.listPlateInfo(stageid);
			if (plateList.size() > 0) {
				AjaxUtils.renderJson(response, plateList);
			}else{
				plateList = null;
				AjaxUtils.renderJson(response, plateList);
			}
		} else {
			AjaxUtils.renderJson(response, plateList);
		}
	}
}
