package org.itboys.admin.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.itboys.commons.dto.ResultPageDOHelper;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping(value = "/admin/pic")
public class AdminUploadFileController extends BaseController {
	@Autowired
	private ResourceHolder resourceHolder;
	@RequestMapping(value = "/upload")
	public void kindEditorUpload(@RequestParam("imgFile") CommonsMultipartFile imgFile,HttpServletResponse response ){
		String imageWebRoot  =  resourceHolder.getStringValue("imgRoot");
		String fileUploadPath=resourceHolder.getStringValue("fileUploadPath");
		try{
			if(imgFile!=null && !imgFile.isEmpty()){
				FileItem item = imgFile.getFileItem();
				if(!item.isFormField()){ 
				
					String filePath = FileUtils.saveFile(item.getInputStream(), fileUploadPath, item.getName(), item.getContentType());
					String imageUrl = imageWebRoot+filePath;

					AjaxUtils.renderJson(response, ResultPageDOHelper
							.getMsgResultDO(imageUrl,
									"上传成功!"));
				}
			}else{ 
				AjaxUtils.renderJson(response, ResultPageDOHelper
						.getErrorResultDO(-2,
								"请上传图片!"));
			}
		}catch(Exception e){ 
			AjaxUtils.renderJson(response, ResultPageDOHelper
					.getErrorResultDO(-2,
							"上传发生错误!"));
		}	
	}
	
}
