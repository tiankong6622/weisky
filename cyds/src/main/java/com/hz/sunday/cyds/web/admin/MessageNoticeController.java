package com.hz.sunday.cyds.web.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.dto.page.PageQueryUtils;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.sunday.commondata.bo.AttachementBO;
import com.hz.sunday.commondata.orm.Attachement;
import com.hz.sunday.cyds.bo.MessageNoticeBO;
import com.hz.sunday.cyds.orm.MessageNotice;

/**
 * 新闻、通知公告、相关资料
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/admin/messnoti")
public class MessageNoticeController extends BaseController{
	
	@Autowired
	private MessageNoticeBO messageNoticeBO;
	@Autowired
	private AttachementBO attachementBO;
	
	/**
	 * 信息列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void getList(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> param = PageQueryUtils.preparePage(request);
		List<MessageNotice> list = messageNoticeBO.getList(param);
		Long count = messageNoticeBO.getCount(param);
		JsonPageUtils.renderJsonPage(count, list, response);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param customer
	 */
	@RequestMapping("/save")
	public String save(@RequestParam("files") CommonsMultipartFile[] achmentFiles, Model model,
			HttpServletRequest request, HttpServletResponse response, MessageNotice messageNotice){
		try{
			
			if(messageNotice.getId() != null){
				messageNoticeBO.update(messageNotice);
			}else{
				messageNoticeBO.insert(messageNotice);
			}
			
			//对附件的操作
			List<Attachement> files = attachementBO.prepareAttachement(achmentFiles);
			if(files != null && files.size() > 0){
				for(Attachement a : files){
					a.setType("messnoti" + messageNotice.getMtype());//设置附件类型
					a.setObjId(String.valueOf(messageNotice.getId()));//设置附件所属项目的id
				}
				attachementBO.batchIn(files);
			}
			
			model.addAttribute("message", "success");//保存成功
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "failed");//保存失败
		}
		return "/cyds/messnoti/admin-single-detail";
	}
	
	/**
	 * 获取单条信息
	 * @return
	 */
	@RequestMapping("/getSingleDetail")
	public String getSingleDetail(@RequestParam("id") Long id, Model model,
			HttpServletRequest request, HttpServletResponse response){
		MessageNotice mn = messageNoticeBO.getSingleDetail(id);
		if(mn != null){
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(id), mtype);
			model.addAttribute("mnattach", list);
			model.addAttribute("messnoti", mn);
		}
		return "/cyds/messnoti/admin-single-detail";
	}
	
	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response){
		try{
			messageNoticeBO.delete(id);
			AjaxUtils.renderText(response, "1");//删除成功
		}catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, "0");//删除失败
		}
	}
	
	/**
	 * 前端轮播的图片
	 * @param achmentFiles
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toLunBoPage")
	public String toLunBoPage(HttpServletRequest request, HttpServletResponse response){
		return "/cyds/messnoti/lunbo-img";
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param customer
	 */
	@RequestMapping("/saveImg")
	public String saveImg(@RequestParam("files") CommonsMultipartFile[] achmentFiles, Model model,
			HttpServletRequest request, HttpServletResponse response, MessageNotice messageNotice){
		try{
			//对附件的操作
			List<Attachement> files = attachementBO.prepareAttachement(achmentFiles);
			if(files != null && files.size() > 0){
				for(Attachement a : files){
					messageNotice.setTitle(a.getFileName());
					messageNotice.setMtype(6);
					messageNoticeBO.insert(messageNotice);
					a.setType("messnoti" + messageNotice.getMtype());//设置附件类型
					a.setObjId("-6");//设置id
				}
				attachementBO.batchIn(files);
			}
			
			model.addAttribute("message", "success");//保存成功
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "failed");//保存失败
		}
		return "/cyds/messnoti/lunbo-img";
	}
}
