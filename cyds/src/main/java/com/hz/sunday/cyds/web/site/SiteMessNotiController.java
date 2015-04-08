package com.hz.sunday.cyds.web.site;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
