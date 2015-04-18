package com.hz.sunday.xccf.web.admin;

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

import com.hz.sunday.xccf.bo.MessageInfoBO;
import com.hz.sunday.xccf.orm.MessageInfo;
import com.hz.yisheng.commondata.bo.AttachementBO;
import com.hz.yisheng.commondata.orm.Attachement;

/**
 * 信息管理
 * 
 * @author huanglei
 * @date 2015年4月18日
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/msgInfo")
public class MessageInfoController extends BaseController{
	
	/** 轮番页面*/
	private static final String TURN_PAGE = "/xccf/news/lunbo-img";
	
	@Autowired
	private MessageInfoBO messageInfoBO;
	@Autowired
	private AttachementBO attachementBO;
	
	/**
	 * 跳转轮番图片新增页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toLunBoPage")
	public String toLunBoPage(HttpServletRequest request, HttpServletResponse response) {
		return TURN_PAGE;
	}
	
	/**
	 * 保存
	 * 
	 * @param achmentFiles
	 * @param model
	 * @param request
	 * @param response
	 * @param messageInfo
	 * @return
	 */
	@RequestMapping("/saveImg")
	public String saveImg(@RequestParam("files") CommonsMultipartFile[] achmentFiles, Model model,
			HttpServletRequest request, HttpServletResponse response, MessageInfo messageInfo){
		try{
			//对附件的操作
			List<Attachement> files = attachementBO.prepareAttachement(achmentFiles);
			if(files != null && files.size() > 0){
				for(Attachement attachement : files){
					messageInfo.setTitle(attachement.getFileName());
					messageInfoBO.insert(messageInfo);
					attachement.setType("messnoti" + messageInfo.getMtype());//设置附件类型
					attachement.setObjId("-10");//设置id
				}
				attachementBO.batchIn(files);
			}
			
			model.addAttribute("message", "success");//保存成功
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "failed");//保存失败
		}
		return TURN_PAGE;
	}
}
