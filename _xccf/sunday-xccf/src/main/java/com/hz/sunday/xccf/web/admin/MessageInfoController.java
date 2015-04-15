package com.hz.sunday.xccf.web.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import com.hz.sunday.xccf.bo.MessageInfoBO;
import com.hz.sunday.xccf.orm.MessageInfo;
import com.hz.yisheng.commondata.bo.AttachementBO;
import com.hz.yisheng.commondata.orm.Attachement;

/**
 * 新闻、通知公告、相关资料
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/admin/messinfo")
public class MessageInfoController extends BaseController{
	
	@Autowired
	private MessageInfoBO messageInfoBO;
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
		List<MessageInfo> list = messageInfoBO.getList(param);
		Long count = messageInfoBO.getCount(param);
		JsonPageUtils.renderJsonPage(count, list, response);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @param customer
	 */
	@RequestMapping("/save")
	public String save(@RequestParam(value="files", required=false) CommonsMultipartFile achmentFiles, 
			@RequestParam(value="toUrl",required=false) String toUrl,Model model,
			HttpServletRequest request, HttpServletResponse response, MessageInfo messageNotice){
		try{
			
			if(messageNotice.getId() != null){
				messageInfoBO.update(messageNotice);
			}else{
				messageInfoBO.insert(messageNotice);
			}
			
			//对附件的操作
			if(achmentFiles != null){
				List<Attachement> files = attachementBO.prepareAttachement(achmentFiles);
				if(files != null && files.size() > 0){
					for(Attachement a : files){
						a.setType("messnoti" + messageNotice.getMtype());//设置附件类型
						a.setObjId(String.valueOf(messageNotice.getId()));//设置附件所属项目的id
					}
					attachementBO.batchIn(files);
				}
			}
			model.addAttribute("message", "success");//保存成功
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "failed");//保存失败
		}
		if(!StringUtils.isBlank(toUrl)){
			return "/xccf/messinfo/" + toUrl;
		}else{
			return "/xccf/messinfo/admin-single-detail";
		}
	}
	
	/**
	 * 获取单条信息
	 * @return
	 */
	@RequestMapping("/getSingleDetail")
	public String getSingleDetail(@RequestParam("id") Long id, @RequestParam(value="toUrl",required=false) String toUrl, 
			Model model, HttpServletRequest request, HttpServletResponse response){
		MessageInfo mn = messageInfoBO.getSingleDetail(id);
		if(mn != null){
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(id), mtype);
			model.addAttribute("mnattach", list);
			model.addAttribute("messnoti", mn);
		}
		if(!StringUtils.isBlank(toUrl)){
			return "/xccf/messinfo/" + toUrl;
		}else{
			return "/xccf/messinfo/admin-single-detail";
		}
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
			messageInfoBO.delete(id);
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
		return "/xccf/messinfo/lunbo-img";
	}
	
}
