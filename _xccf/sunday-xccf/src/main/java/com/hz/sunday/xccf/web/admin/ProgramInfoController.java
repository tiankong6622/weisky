package com.hz.sunday.xccf.web.admin;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.sunday.xccf.bo.MessageInfoBO;
import com.hz.sunday.xccf.orm.MessageInfo;
import com.hz.yisheng.commondata.constants.DialogMode;

/**
 * 论坛日程
 * 
 * @author huanglei
 * @date 2015年4月17日
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/program")
public class ProgramInfoController extends BaseController {
	
	/** 论坛日程页面*/
	private static final String PROGRAM_PAGE = "/xccf/program/programDlg";

	@Autowired
	private MessageInfoBO messageInfoBO;

	/**
	 * 日程列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = PageQueryUtils.preparePage(request);
		List<MessageInfo> list = messageInfoBO.getList(param);
		Long count = messageInfoBO.getCount(param);
		JsonPageUtils.renderJsonPage(count, list, response);
	}

	/**
	 * 跳转新增/编辑页面
	 * 
	 * @param id
	 * @param mode
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/{mode}/dispatch")
	public String dispatch(
			@RequestParam(value = "id", required = false) Long id,
			@PathVariable String mode, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			MessageInfo info = messageInfoBO.getSingleDetail(id);
			if (null != info) {
				model.addAttribute("messnoti", info);
			}
		}
		return PROGRAM_PAGE;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param messageNotice
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Model model, HttpServletRequest request,
			HttpServletResponse response, MessageInfo messageNotice) {
		try {
			if (messageNotice.getId() != null) {
				messageInfoBO.update(messageNotice);
			} else {
				messageInfoBO.insert(messageNotice);
			}
			model.addAttribute("message", "success");// 保存成功
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "failed");// 保存失败
		}
		return PROGRAM_PAGE;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/deleteById")
	public void deleteById(@RequestParam("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			messageInfoBO.delete(id);
			AjaxUtils.renderText(response, "1");// 删除成功
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, "0");// 删除失败
		}
	}

}
