package com.hz.yisheng.commondata.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.javafans.common.constants.CommonConstants;
import org.javafans.common.utils.encryption.Digests;
import org.javafans.common.utils.io.FileUtils;
import org.javafans.resources.ResourceConfig;
import org.javafans.web.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.commondata.CommonDataConstants;
import com.hz.yisheng.commondata.bo.AttachementBO;
import com.hz.yisheng.commondata.orm.Attachement;

@Controller
public class AttachementController {

	@Autowired
	private AttachementBO attachementBO;

	/**
	 * 异步删除附件
	 * 
	 * @param id
	 * @param sign
	 *            为了防止别人乱删除 必须拿到密钥才能删
	 */
	@RequestMapping("/deleteAttachement")
	public void del(@RequestParam Long id, @RequestParam String sign, HttpServletResponse response) {
		String currentSign = Digests.md5(CommonDataConstants.TMP_SIGN + id);
		if (StringUtils.equals(sign, currentSign)) {
			attachementBO.delete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} else {
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	/**
	 * 保存附件信息（稠州银行）
	 * 
	 * @param type
	 * @param objId
	 * @param response
	 */
	@RequestMapping(value = "/appImage/save")
	public void save(@RequestParam("eimage") CommonsMultipartFile eimage, HttpServletResponse response) {
		try {
			Attachement att = new Attachement();
			FileItem item1 = eimage.getFileItem();
			String filePath1 = FileUtils.saveFile(item1.getInputStream(), ResourceConfig.getSysConfig("fileUploadPath"), item1.getName(), item1.getContentType());
			att.setPath(filePath1);
			att.setContentType("image/png");
			att.setFileName("image");
			att.setName("image");
			att.setObjId("12");
			att.setType("chouzhou");
			att.setSize(0l);
			attachementBO.insert(att);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	@RequestMapping(value = "/appImage/list/{type}/{id}")
	public void getList(@PathVariable("id") Long id, @PathVariable("type") String type, HttpServletResponse response) {
		List<Attachement> mylist = attachementBO.findBy(id, type);
		AjaxUtils.renderJson(response, mylist);

	}

	@RequestMapping(value = "/appImage/delete/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		try {
			attachementBO.delete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}

	}
}
