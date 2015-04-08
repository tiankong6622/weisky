package com.hz.sunday.cyds.web.site;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.sunday.commondata.bo.AttachementBO;
import com.hz.sunday.commondata.orm.Attachement;
import com.hz.sunday.cyds.bo.MessageNoticeBO;
import com.hz.sunday.cyds.orm.MessageNotice;

/**
 * 首页
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/site")
public class CydsIndexController extends BaseController{
	
	@Autowired
	private AttachementBO attachementBO;
	@Autowired
	private MessageNoticeBO messageNoticeBO;

	/**
	 * 前端首页
	 * @param request
	 * @param response
	 * @param runProject
	 * @return
	 */
	@RequestMapping(value={"/index","/",""})
	public String cydsIndex(Model model, HttpServletRequest request, HttpServletResponse response){
		//轮播的图片
		List<Attachement> lunboImgList = attachementBO.findBy("-6", "messnoti6");
		model.addAttribute("lunboImgList", lunboImgList);
		
		//新闻中心
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(1, 4);
		model.addAttribute("newsList", newsList);
		
		//通知公告
		List<MessageNotice> noticeList = messageNoticeBO.getByMtypeLimit(2, 6);
		model.addAttribute("noticeList", noticeList);
		
		
		return "/cyds/site/site-index";
	}
	
	
	
}
