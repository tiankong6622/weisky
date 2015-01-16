package org.javafans.web.controller;

import org.javafans.common.utils.io.FileUtils;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.AjaxUtils;
import java.io.IOException;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
   
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.multipart.MultipartHttpServletRequest;  

/**
 * Uploadify 上传文件的父类
 * @author ChenJunhui
 *
 */
public abstract class BaseUploadifyController {

	public static final String fileUploadPath =  ResourceConfig.getSysConfig("fileUploadTmpPath");
	
	/**
	 * 将返回结果 做些特殊处理 比如关联附件表的ID
	 * @param result
	 * @return
	 */
	protected abstract String prepareFileResult(String result);
	
	
	 /** 
     * 上传文件 
     * @return 
     * @throws IOException  
     * @throws IllegalStateException  
     */  
    @RequestMapping(value = "/uploadify", method = RequestMethod.POST)  
    public void upload(HttpServletRequest request, HttpServletResponse response){  
        String responseStr="";  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
        try{
        	 int i=0;
        	 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
                 // 上传文件   
                 MultipartFile mf = entity.getValue();    
                 String filePath = FileUtils.saveFile(mf.getInputStream(), fileUploadPath, mf.getOriginalFilename(), mf.getContentType());
                 filePath = prepareFileResult(filePath);
                 if(i>0){
                	 responseStr=responseStr+","+filePath;
                 }else{
                	 responseStr=responseStr+filePath;
                 }
             }  
        	 AjaxUtils.renderHtml(response, responseStr);
        }catch(Exception e){
        	throw new RuntimeException(e);
        }
       
    }  
}
