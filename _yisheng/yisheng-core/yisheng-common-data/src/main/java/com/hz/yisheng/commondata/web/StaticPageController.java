package com.hz.yisheng.commondata.web;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.javafans.web.AjaxUtils;
import org.javafans.web.JsonPageUtils;
import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.commondata.bo.StaticPageBO;
import com.hz.yisheng.commondata.orm.StaticPage;

@Controller
@RequestMapping("/staticPage")
public class StaticPageController  extends BaseController{

	@Autowired
	private StaticPageBO staticPageBO;
		
	@RequestMapping("/save")
	public void insert(@RequestParam("file") CommonsMultipartFile file,StaticPage page,HttpServletResponse response){
		if(page != null){
			try{
				if(file!=null && !file.isEmpty()){
					FileItem item = file.getFileItem();
					OutputStream os = new FileOutputStream(page.getPreviewPath());
					IOUtils.copy(item.getInputStream(), os);
				}
				if(staticPageBO.getByTitle(page.getTitle()) == null){
					page.setProjectId(0l);
					page.setStatus(0);//默认为未同步
					staticPageBO.insert(page);
				}else{
					staticPageBO.update(page);
				}	
				
				AjaxUtils.renderText(response, "1");
			}catch(Exception e){
				logger.error("StaticPageController:save"+page, e);
				e.printStackTrace();
				AjaxUtils.renderText(response, "0");
			}
		}
	}
	
	@RequestMapping("/list")
	public void indexHtmlList(HttpServletResponse response){
		List<StaticPage> menus = staticPageBO.list();
		JsonPageUtils.renderJsonPage(menus!=null&&menus.size()>0?menus.size():0L, menus, response);
	}
	
	@RequestMapping("/getById")
	public String input(@RequestParam(value="id",required=false) Long id,Model model){
		if(id != null  && id > 0 ){
			StaticPage page = staticPageBO.getById(id);
			model.addAttribute("indexHtml",page);
		}
		return "/meirongyuan/staticpage/input";
	}
	
	@RequestMapping("/delete/{id}")
	public void del(@PathVariable("id") Long id,HttpServletResponse response){
		try{
			if(id != null){
				staticPageBO.del(id);
				AjaxUtils.renderText(response, "1");
			}else{
				AjaxUtils.renderText(response, "-1");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("StaticPageController:del  id="+id, e);
			AjaxUtils.renderText(response, "0");
		}
	}
	
	@RequestMapping("/syncToFilePath/{id}")
	public void syncToFilePath(@PathVariable("id") Long id,HttpServletResponse response){
		try {			
			StaticPage page = staticPageBO.getById(id);
			page.setAddress(page.getPreviewAddress());
			page.setStatus(1);//设置状态为同步
			staticPageBO.update(page);
			AjaxUtils.renderText(response, "1");			
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, "0");
			logger.error("StaticPageController:syncToFilePath", e);
		}
		
		
	}
	
}
