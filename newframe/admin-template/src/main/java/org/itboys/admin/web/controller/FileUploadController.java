package org.itboys.admin.web.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.commons.utils.string.CommonStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * io相关
 * @author ChenJunhui
 */

public abstract class FileUploadController {

	public static String SUCCESS_JSON = "{\"error\":0,\"url\":\"{0}\"}";
	public static String ERROR_JSON = "{\"error\":1,\"message\":\"{0}\"}";
	
	//允许上传的文件类型
	public static String[] allowTypes = new String[]{"image/jpeg","image/bmp", "image/gif","image/png","image/pjpeg","image/x-png","image/jpg"};
	
	
	public abstract String getFileUploadPath();
	
	public abstract String getImageWebRoot();
	
	@RequestMapping(value = "/kindEditorUpload")
	public void kindEditorUpload(@RequestParam("imgFile") CommonsMultipartFile imgFile,HttpServletResponse response ){
		response.setContentType("text/html;charset=UTF-8");
		String fileUploadPath  = getFileUploadPath(); 
		String imageWebRoot  = getImageWebRoot();
		try{
			if(imgFile!=null && !imgFile.isEmpty()){
				FileItem item = imgFile.getFileItem();
				if(!item.isFormField()){ 
				/*	if(!CommonStringUtils.in(item.getContentType(), allowTypes)){
						response.getWriter().print(ERROR_JSON.replace("{0}", "不支持该文件格式"));
					}else{
						String filePath = FileUtils.saveFile(item.getInputStream(), fileUploadPath, item.getName(), item.getContentType());
						String imageUrl = imageWebRoot+filePath;
						response.getWriter().print(SUCCESS_JSON.replace("{0}", imageUrl));
					}*/
					String filePath = FileUtils.saveFile(item.getInputStream(), fileUploadPath, item.getName(), item.getContentType());
					String imageUrl = imageWebRoot+filePath;
					response.getWriter().print(SUCCESS_JSON.replace("{0}", imageUrl));
				}
			}else{
				response.getWriter().print(ERROR_JSON.replace("{0}", "请上传文件"));
			}
		}catch(Exception e){
			try {
				response.getWriter().print(ERROR_JSON.replace("{0}", "上传失败"));
			} catch (IOException e1) {
				 
			}
		}
	}
	
	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void upload(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try{
			if(ServletFileUpload.isMultipartContent(request)){
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				List<FileItem> items = upload.parseRequest(request);
				if(items!=null && !items.isEmpty()){
					//上传文件路径
					String fileUploadPath  = getFileUploadPath(); 
					String imageWebRoot  = getImageWebRoot();
					for(FileItem item:items){
						if(!item.isFormField()){ //不知道为什么上传了一个文件 会有两个文件 一个是在临时文件夹的
							if(!CommonStringUtils.in(item.getContentType(), allowTypes)){
								response.getWriter().print(ERROR_JSON.replace("{0}", "不支持该文件格式"));
							}else{
								String filePath = FileUtils.saveFile(item.getInputStream(), fileUploadPath, item.getName(), item.getContentType());
								String imageUrl = imageWebRoot+filePath;
								response.getWriter().print(SUCCESS_JSON.replace("{0}", imageUrl));
							}
						}
					}
				}else{
					response.getWriter().print(ERROR_JSON.replace("{0}", "请上传文件"));
				}
			}
		}catch(Exception e){
			try {
				response.getWriter().print(ERROR_JSON.replace("{0}", "上传失败"));
			} catch (IOException e1) { 
			}
		}
	}
}
