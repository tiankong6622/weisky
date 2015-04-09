package com.hz.sunday.cyds.web.site;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.sunday.commondata.bo.AttachementBO;
import com.hz.sunday.commondata.orm.Attachement;
import com.hz.sunday.cyds.bo.MessageNoticeBO;
import com.hz.sunday.cyds.orm.MessageNotice;

/**
 * 相关信息的列表或者详情
 * @author WeiSky
 *
 */
@Controller
@RequestMapping("/site/messnoti")
public class SiteMessNotiController extends BaseController{

	@Autowired
	private MessageNoticeBO messageNoticeBO;
	@Autowired
	private AttachementBO attachementBO;
	
	/**
	 * 新闻详情
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getNewSingleDetail")
	public String getNewSingleDetail(@RequestParam("id") Long id, Model model,
			HttpServletRequest request, HttpServletResponse response){
		MessageNotice mn = messageNoticeBO.getSingleDetail(id);
		model.addAttribute("newSingleDetail", mn);
		return "/cyds/messnoti/news-detail";
	}
	
	/**
	 * 新闻列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getNewsList")
	public String getNewsList(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtype(1);
		model.addAttribute("newsList", newsList);
		return "/cyds/messnoti/news-more";
	}
	
	/**
	 * 通知公告详情
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getNoticeSingleDetail")
	public String getNoticeSingleDetail(@RequestParam("id") Long id, Model model,
			HttpServletRequest request, HttpServletResponse response){
		MessageNotice mn = messageNoticeBO.getSingleDetail(id);
		model.addAttribute("noticeSingleDetail", mn);
		return "/cyds/messnoti/notice-detail";
	}
	
	/**
	 * 通知公告列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getNoticeList")
	public String getNoticeList(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtype(2);
		model.addAttribute("noticeList", newsList);
		return "/cyds/messnoti/notice-more";
	}
	
	/********************************纯文字介绍********************************/
	/**
	 * 大赛介绍
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/aboutDs")
	public String aboutDs(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(3, 1);
		if(newsList != null && newsList.size() > 0){
			MessageNotice mn = newsList.get(0);
			model.addAttribute("aboutDs", mn);
			//设置大赛介绍的附件列表
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(mn.getId()), mtype);
			model.addAttribute("aboutDsAttach", list);
		}
		return "/cyds/messnoti/aboutds";
	}
	
	/**
	 * 纯文字介绍通用方法
	 * @param toUrl
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/commtext/{toUrl}")
	public String commText(@PathVariable("toUrl") String toUrl, Model model, @RequestParam("mtype") int mtype,
			HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(mtype, 1);
		if(newsList != null && newsList.size() > 0){
			MessageNotice mn = newsList.get(0);
			model.addAttribute("mnoti", mn);
			//设置大赛介绍的附件列表
			String mt = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(mn.getId()), mt);
			model.addAttribute("commText", list);
		}
		return "/cyds/messnoti/"+toUrl;
	}
	
	
	
	
	/**
	 * 参赛指南--参赛条件
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/zntj")
	public String zntj(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(4, 1);
		if(newsList != null && newsList.size() > 0){
			MessageNotice mn = newsList.get(0);
			model.addAttribute("zntj", mn);
			
			//设置参赛条件的附件列表
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(mn.getId()), mtype);
			model.addAttribute("zntjAttach", list);
		}
		return "/cyds/messnoti/zntj";
	}
	
	/**
	 * 参赛指南--报名渠道及方式
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/znbmqd")
	public String znbmqd(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(7, 1);
		if(newsList != null && newsList.size() > 0){
			MessageNotice mn = newsList.get(0);
			model.addAttribute("znbmqd", mn);
			
			//设置参赛条件的附件列表
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(mn.getId()), mtype);
			model.addAttribute("znbmqdAttach", list);
		}
		return "/cyds/messnoti/znbmqd";
	}
	
	/**
	 * 参赛指南--赛程安排
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/znscap")
	public String znscap(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(8, 1);
		if(newsList != null && newsList.size() > 0){
			MessageNotice mn = newsList.get(0);
			model.addAttribute("znscap", mn);
			
			//设置参赛条件的附件列表
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(mn.getId()), mtype);
			model.addAttribute("znscapAttach", list);
		}
		return "/cyds/messnoti/znscap";
	}
	
	/**
	 * 参赛指南--政策支持
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/znzczc")
	public String znzczc(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(9, 1);
		if(newsList != null && newsList.size() > 0){
			MessageNotice mn = newsList.get(0);
			model.addAttribute("zczc", mn);
			
			//设置参赛条件的附件列表
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(mn.getId()), mtype);
			model.addAttribute("zczcAttach", list);
		}
		return "/cyds/messnoti/znzczc";
	}
	
	/**
	 * 参赛指南--参赛资料下载
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/znzlxz")
	public String znzlxz(Model model, HttpServletRequest request, HttpServletResponse response){
		List<MessageNotice> newsList = messageNoticeBO.getByMtypeLimit(3, 1);
		if(newsList != null && newsList.size() > 0){
			MessageNotice mn = newsList.get(0);
			model.addAttribute("zlxz", mn);
			
			//设置参赛条件的附件列表
			String mtype = "messnoti" + mn.getMtype();
			List<Attachement> list = attachementBO.findBy(String.valueOf(mn.getId()), mtype);
			model.addAttribute("zlxzAttach", list);
		}
		return "/cyds/messnoti/znzlxz";
	}
	
	
	
}
