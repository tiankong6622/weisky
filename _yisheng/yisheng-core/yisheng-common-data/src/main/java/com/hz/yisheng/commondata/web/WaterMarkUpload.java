package com.hz.yisheng.commondata.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.javafans.common.utils.exception.Exceptions;
import org.javafans.common.utils.io.FileUtils;
import org.javafans.common.utils.string.CommonStringUtils;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.springmvc.controller.FileUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.commondata.bo.KVConfigBO;
import com.hz.yisheng.commondata.bo.WatermarkBO;

/**
 * 水印上传
 * @author ChenJunhui
 *
 */
@Controller
public class WaterMarkUpload {
	
	@Autowired
	private WatermarkBO watermarkBO;
	@Autowired
	private KVConfigBO kVConfigBO;
	
	public String getFileUploadPath() {
		return ResourceConfig.getSysConfig("fileUploadPath");
	}

	public String getImageWebRoot() {
		return ResourceConfig.getSysConfig("imageRoot");
	}
	
	@RequestMapping(value = "/doUploadWithWaterMark")
	public void doUploadWithWaterMark(@RequestParam("imgFile") CommonsMultipartFile imgFile,
			@RequestParam(value="waterMark",required=false) String waterMark,
			HttpServletResponse response ){
		response.setContentType("text/html;charset=UTF-8");
		String fileUploadPath  = getFileUploadPath(); 
		String imageWebRoot  = getImageWebRoot();
		try{
			
			if(imgFile!=null && !imgFile.isEmpty()){
				FileItem item = imgFile.getFileItem();
				if(!item.isFormField()){ 
					 //System.out.println(item.getContentType()+"=================");
						/*if(!CommonStringUtils.in(item.getContentType(), FileUploadController.allowTypes)){
							response.getWriter().print(FileUploadController.ERROR_JSON.replace("{0}", "不支持该文件格式 请上传图片"));
						}else{
							String filePath = FileUtils.saveFile(item.getInputStream(), fileUploadPath, item.getName(), item.getContentType());
							watermarkBO.prepareWaterMark(waterMark, filePath);
							String imageUrl = imageWebRoot+filePath;
							response.getWriter().print(FileUploadController.SUCCESS_JSON.replace("{0}", imageUrl));
						}*/
					String filePath = FileUtils.saveFile(item.getInputStream(), fileUploadPath, item.getName(), item.getContentType());
					watermarkBO.prepareWaterMark(waterMark, filePath);
					String imageUrl = imageWebRoot+filePath;
					response.getWriter().print(FileUploadController.SUCCESS_JSON.replace("{0}", imageUrl));
				}
			}else{
				response.getWriter().print(FileUploadController.ERROR_JSON.replace("{0}", "请上传文件"));
			}
		}catch(Exception e){
			try {
				response.getWriter().print(FileUploadController.ERROR_JSON.replace("{0}", "上传失败"));
			} catch (IOException e1) {
				e.printStackTrace();
				throw Exceptions.unchecked(e1);
			}
		}
	}

	
}
