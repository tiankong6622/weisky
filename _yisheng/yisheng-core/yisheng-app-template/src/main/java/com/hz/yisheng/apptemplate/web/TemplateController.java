package com.hz.yisheng.apptemplate.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.admin.filte.LoginUserContext;
import com.hz.yisheng.admin.utils.JSONUtils;
import com.hz.yisheng.apptemplate.bo.TemplateBO;
import com.hz.yisheng.apptemplate.orm.Template;
import com.hz.yisheng.commondata.bo.AttachementBO;
import com.hz.yisheng.commondata.orm.Attachement;
/**
 * 模块控制
 * @author loard
 *
 */
@Controller
@RequestMapping("/admin/template")
public class TemplateController extends BaseController{

	@Autowired
	private TemplateBO templateBO;
	@Autowired
	private AttachementBO attachementBO;
	
	/**
	 * 更新添加模块
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	public String update(@RequestParam(value="id",required=false) Long id,
						 HttpServletRequest request,HttpServletResponse response,Model model){
		if(id != null){
			Template template = templateBO.select(id);
			model.addAttribute("tp", template);
		}else{
			
		}
		return "/adminapply/template";
	}
	@RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Long id,
			HttpServletRequest request,HttpServletResponse response,Model model){
		if(id != null){
			Template template = new Template();
			Integer sort = 0;
			try {
				sort = templateBO.getMaxsort(id) + 1;
			} catch (Exception e) {
				sort = 0;
			}
			template.setAppId(id);
			template.setSort(sort);
			model.addAttribute("tp", template);
		}
		return "/adminapply/template";
	}
	/**
	 * 保存
	 * @param template
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/save")
	public String save(@RequestParam(value="aImage",required=false) CommonsMultipartFile apath,
					   @RequestParam(value="abImage",required=false) CommonsMultipartFile abpath,
					   @RequestParam(value="iImage",required=false) CommonsMultipartFile ipath,
					   @RequestParam(value="ibImage",required=false) CommonsMultipartFile ibpath,
					   Template template,HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			List<Attachement> alist = attachementBO.prepareAttachement(apath);
			List<Attachement> ablist = attachementBO.prepareAttachement(abpath);
			List<Attachement> ilist = attachementBO.prepareAttachement(ipath);
			List<Attachement> iblist = attachementBO.prepareAttachement(ibpath);
			for (Attachement a : alist){
				template.setAndroidImage(a.getFileName());
				template.setApath(a.getPath());
			}
			for (Attachement ab : ablist) {
				template.setAndroidImageB(ab.getFileName());
				template.setAbpath(ab.getPath());
			}
			for (Attachement i : ilist) {
				template.setIosImage(i.getFileName());
				template.setIpath(i.getPath());
			}
			for (Attachement ib : iblist) {
				template.setIosImageB(ib.getFileName());
				template.setIbpath(ib.getPath());
			}
			if(template.getId() != null){
				templateBO.update(template);
				for (Attachement a : alist){
					a.setType("Template");
					a.setObjId(template.getId()+"");
					a.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for (Attachement ab : ablist) {
					ab.setType("Template");
					ab.setObjId(template.getId()+"");
					ab.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for (Attachement i : ilist) {
					i.setType("Template");
					i.setObjId(template.getId()+"");
					i.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for (Attachement ib : iblist) {
					ib.setType("Template");
					ib.setObjId(template.getId()+"");
					ib.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				attachementBO.batchIn(alist);
				attachementBO.batchIn(ablist);
				attachementBO.batchIn(ilist);
				attachementBO.batchIn(iblist);
			}else{
				templateBO.insert(template);
				Long id = templateBO.getId();
				for (Attachement a : alist){
					a.setType("Template");
					a.setObjId(id+"");
					a.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for (Attachement ab : ablist) {
					ab.setType("Template");
					ab.setObjId(id+"");
					ab.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for (Attachement i : ilist) {
					i.setType("Template");
					i.setObjId(id+"");
					i.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for (Attachement ib : iblist) {
					ib.setType("Template");
					ib.setObjId(id+"");
					ib.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				attachementBO.batchIn(alist);
				attachementBO.batchIn(ablist);
				attachementBO.batchIn(ilist);
				attachementBO.batchIn(iblist);
			}
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/adminapply/template";
	}
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id",required=false) Long id,
					   HttpServletRequest request,HttpServletResponse response){
		try {
			templateBO.delete(id);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}
}
