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
import com.hz.yisheng.apptemplate.bo.ApplyBO;
import com.hz.yisheng.apptemplate.orm.Apply;
import com.hz.yisheng.commondata.bo.AttachementBO;
import com.hz.yisheng.commondata.orm.Attachement;
/**
 * 应用控制
 * @author loard
 *
 */
@Controller
@RequestMapping("/admin/apply")
public class AdminApplyController extends BaseController{
	
	
	@Autowired
	private ApplyBO applyBO;
	
	@Autowired
	private AttachementBO attachementBO;
	
	/**
	 * 更新添加
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
			Apply apply = applyBO.select(id);
			model.addAttribute("apply", apply);
		}else{
			Apply apply = new Apply();
			model.addAttribute("apply", apply);
		}
		return "/adminapply/input";
	}
	/**
	 * 保存
	 * @param apply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/save")
	public String save(@RequestParam(value="appImaget",required=false) CommonsMultipartFile appImaget,
					   @RequestParam(value="tdcAddresst",required=false) CommonsMultipartFile tdcAddresst,Apply apply,HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			List<Attachement> list = attachementBO.prepareAttachement(appImaget);
			List<Attachement> tlist = attachementBO.prepareAttachement(tdcAddresst);
			for(Attachement a :list){
				apply.setAppImage(a.getFileName());
				apply.setPath(a.getPath());
			}
			for(Attachement t : tlist){
				apply.setTdcAddress(t.getPath());
			}
			if(apply.getId() != null){
				applyBO.update(apply);
				for(Attachement a : list){
					a.setType("Apply");
					a.setObjId(apply.getId()+"");
					a.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for(Attachement t : tlist){
					t.setType("Apply");
					t.setObjId(apply.getId()+"");
					t.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				attachementBO.batchIn(list);
				attachementBO.batchIn(tlist);
			}else{
				applyBO.insert(apply);
				for(Attachement a : list){
					a.setType("Apply");
					a.setObjId(apply.getId()+"");
					a.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				for(Attachement t : tlist){
					t.setType("Apply");
					t.setObjId(apply.getId()+"");
					t.setCreaterId(LoginUserContext.getLoginUser().getId());
				}
				attachementBO.batchIn(list);
				attachementBO.batchIn(tlist);
			}
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/adminapply/input";
	}
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id",required=false) Long id,HttpServletRequest request,HttpServletResponse response){
		try {
			applyBO.delete(id);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

}
