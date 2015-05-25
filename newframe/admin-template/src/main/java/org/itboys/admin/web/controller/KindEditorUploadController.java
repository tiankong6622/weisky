package org.itboys.admin.web.controller;

 
import org.itboys.framework.resource.ResourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class KindEditorUploadController extends FileUploadController {

	@Autowired
	private ResourceHolder resourceHolder;

	public String getFileUploadPath() {
		return resourceHolder.getStringValue("fileUploadPath");
	}

	public String getImageWebRoot() {
		return resourceHolder.getStringValue("imgRoot");
	}

	
}
