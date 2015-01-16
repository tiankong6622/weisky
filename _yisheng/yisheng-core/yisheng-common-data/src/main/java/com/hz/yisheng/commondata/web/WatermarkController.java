package com.hz.yisheng.commondata.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.CommonUtils;
import org.javafans.common.utils.io.FileUtils;
import org.javafans.common.utils.query.QueryParamUtils;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.bo.WatermarkBO;
import com.hz.yisheng.commondata.orm.Watermark;
/**
 * 水印 controller
 * @author lenovo
 */
@Controller
public class WatermarkController extends BaseController{

	@Autowired
	private WatermarkBO watermarkBO;
	
	/**
	 * 获取列表到页面
	 * @return
	 * OL
	 */
	@RequestMapping("/watermark/input")
	public String watermark(Model model){
		return "/watermark/list";
	}
	/**
	 * 异步查询列表
	 * @param response
	 * @param request
	 */
	@RequestMapping("/watermark/list")
	public void list(HttpServletResponse response,HttpServletRequest request){
		final Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);
		sqlMap.put("projectId", 0L);//SessionHolder.getProjectId()
		List<Watermark>menus = watermarkBO.list(sqlMap);
		JsonPageUtils.renderJsonPage(menus!=null&&menus.size()>0?menus.size():0L, menus, response);
	}
	
	
	@RequestMapping("/watermark/lisByProject")
	public void lisByProject(HttpServletResponse response,HttpServletRequest request){
		final Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);
		sqlMap.put("projectId", SessionHolder.getProjectId());;
		List<Watermark>menus = watermarkBO.list(sqlMap);
		JsonPageUtils.renderJsonPage(menus!=null&&menus.size()>0?menus.size():0L, menus, response);
	}
	
	/**
	 * 异步查询-根据id
	 * @param response
	 * @param request
	 */
	@RequestMapping("/watermark/getById")
	public void getById(HttpServletResponse response,HttpServletRequest request){
		String id = request.getParameter("id");
		if(StringUtils.isNotEmpty(id)){
			Watermark watermark = watermarkBO.getById(Long.parseLong(id));
			AjaxUtils.renderJson(response, watermark);
		}
	}
	/**
	 * 新增(保存)水印
	 */
	@RequestMapping("/admin/watermark/save")
	public void save(@ModelAttribute Watermark entity,HttpServletRequest request,HttpServletResponse response,@RequestParam("watermarkPic") CommonsMultipartFile watermarkPic){
		try{
			if(entity.getType() == 1){
				if(null != watermarkPic && !watermarkPic.isEmpty()){
					if(watermarkPic.getSize()>0){
						FileItem item = watermarkPic.getFileItem();
						String filePath = FileUtils.saveFile(item.getInputStream(), ResourceConfig.getSysConfig("fileUploadPath"), item.getName(), item.getContentType());
						entity.setWatermark(filePath);
					}
				}
			}
			if(entity.getFlag()!=null&&entity.getFlag()!=4){
				entity.setX(-1);
				entity.setY(-1);
			}

			if(entity.getId()==null){
				entity.setProjectId(0L);//entity.setAdvertisementId(advertisementId);
				entity.setObjId(0L);
				watermarkBO.insert(entity);
			}else {
				watermarkBO.update(entity);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("save project menu fail",e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("/admin/watermark/delete")
	public void deleteWatermark(@RequestParam long id,HttpServletResponse response){
		if(StringUtils.isNotEmpty(String.valueOf(id))){
			try{
				AjaxUtils.renderText(response, String.valueOf(watermarkBO.delete(id)));
			}catch(Exception e){
				logger.error("deleteWatermark project menu fail",e);
				AjaxUtils.renderText(response,CommonConstants.FAIL);
			}
		}
	}
	
	@RequestMapping("/watermar/updateStatus")
	public void updateStatus(Long id,Integer value,HttpServletResponse response){
		try{
			if(CommonUtils.isIn(value, Watermark.STATUS_NO,Watermark.STATUS_YES)){
				Watermark wmark = new Watermark();
				wmark.setId(id);
				wmark.setStatus(value);
				watermarkBO.update(wmark);
				AjaxUtils.renderText(response,CommonConstants.SUCCESS);
			}else{
				AjaxUtils.renderText(response, CommonConstants.FAIL);
			}
		}catch (Exception e) {
			logger.error("update status fail",e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
		
	}
}
