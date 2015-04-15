package com.hz.sunday.xccf.web.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.dto.page.Page;
import org.javafans.web.JSONUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.javafans.web.springmvc.controller.WebControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hz.sunday.xccf.bo.ColumnBO;
import com.hz.sunday.xccf.orm.ColumnBean;
import com.hz.yisheng.commondata.constants.DialogMode;

/**
 * 栏目管理
 * 
 * @author huanglei
 * @date 2015年4月13日
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/xccf/column")
public class ColumnController extends BaseController {

	@Autowired
	private ColumnBO columnBO;

	/**
	 * 获取分页数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		final Map<String, Object> sqlMap = QueryParamUtils
				.builderQueryMap(request);// 组装查询参数
		Page<ColumnBean> page = WebControllerUtils.preparePage(request, 10);// 组装page对象
		page = columnBO.pageQuery(page, sqlMap);
		JsonPageUtils.renderJsonPage(page.getTotalCount(), page.getResult(),
				response);
	}

	/**
	 * 跳转新增、编辑页面
	 * 
	 * @param mode
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{mode}/dispatch")
	public String previewUpdate(@PathVariable String mode,
			@RequestParam(required = false) Long id, Model model) {
		model.addAttribute("mode", mode);
		if (DialogMode.EDIT_MODE.equals(mode)) {
			model.addAttribute("column", columnBO.findById(id));
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			model.addAttribute("column", columnBO.findById(id));
			return "/column/viewColumn";
		}
		return "/column/columnDlg";
	}

	/**
	 * 新增
	 * 
	 * @param columnBean
	 * @param logos
	 * @param request
	 * @param response
	 */
	@RequestMapping("/add")
	public void add(@ModelAttribute ColumnBean columnBean,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			columnBO.insert(columnBean);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	/**
	 * 更新
	 * 
	 * @param columnBean
	 * @param logos
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	public void update(@ModelAttribute ColumnBean columnBean,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			columnBO.update(columnBean);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

	/**
	 * 根据ID，删除信息
	 * 
	 * @param id
	 * @param response
	 * @param model
	 */
	@RequestMapping("/deleteById/{id}")
	public void deleteById(@PathVariable("id") Long id,
			HttpServletResponse response, Model model) {
		try {
			columnBO.deleteById(id);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}

}
