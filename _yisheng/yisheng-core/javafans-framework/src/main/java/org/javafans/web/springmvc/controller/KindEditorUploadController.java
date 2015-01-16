package org.javafans.web.springmvc.controller;


import org.javafans.resources.ResourceConfig;
import org.javafans.web.springmvc.controller.FileUploadController;
import org.springframework.stereotype.Controller;

@Controller
public class KindEditorUploadController extends FileUploadController {


	public String getFileUploadPath() {
		return ResourceConfig.getSysConfig("fileUploadPath");
	}

	public String getImageWebRoot() {
		return ResourceConfig.getSysConfig("imageRoot");
	}

	
}
