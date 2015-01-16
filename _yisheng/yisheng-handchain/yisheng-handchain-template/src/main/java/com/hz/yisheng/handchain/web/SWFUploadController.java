package com.hz.yisheng.handchain.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.common.utils.io.FileUtils;
import org.javafans.resources.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hz.yisheng.handchain.bo.FriendCircleBO;

@Controller
@RequestMapping("/ajaxswf")
public class SWFUploadController {
	// 用于文件上传
	@Autowired
	private FriendCircleBO friendBO;
	public static final String realPath = ResourceConfig
			.getSysConfig("fileUploadPath");
	public static final String tempPath = realPath + File.separator + "tmp";
	String res = "";
	String msg = "";

	// / <summary>
	// / 上传文件
	// / </summary>
	// / <param name="context"></param>
	@RequestMapping("/upLoadFile")
	public void ProcessRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String cmdOperate = request.getParameter("cmdOperate");
		String fileUrl = request.getParameter("fileUrl");
		// 删除文件
		if (cmdOperate != null && cmdOperate.equals("delete")) {
			res = "{ \"msg\":\"" + DeleteFile(fileUrl) + "\",\"fileUrl\":\""
					+ fileUrl + "\"}";// 这里直接删除了
			try {
				response.getWriter().write(res);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
						.getFile("Filedata");// 这里是表单的名字，在swfupload.js中this.ensureDefault("file_post_name",
												// "filedata");
				String fileName = file.getOriginalFilename();
				fileName = new String(fileName.getBytes(), "utf-8");
				InputStream is = file.getInputStream();
				String filePath = FileUtils.saveFile(is, tempPath, fileName,
						file.getContentType());
				msg = "文件上传成功";
				res = "{ \"msg\":\"" + msg + "\",\"fileUrl\":\"" + filePath
						+ "\",\"fileName\":\"" + fileName + "\"}";
				response.getWriter().write(res);
			} catch (Exception er) {
				er.printStackTrace();
				msg = "文件上传失败！";
				try {
					response.getWriter().write("{ \"msg\":\"" + msg + "\"}");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	

	// 从实际文件夹中存入临时文件夹
	private String DeleteFile(String fileUrl) {
		String msg = "";
		try {
			String filepath = realPath + File.separator + fileUrl;
			File f = new File(filepath);
			if (f.exists()) {
				FileInputStream fis = new FileInputStream(f);

				File ftemp = new File(tempPath + File.separator + fileUrl);
				FileOutputStream fos = new FileOutputStream(ftemp);
				byte[] buf = new byte[1024];
				int byteread = -1;
				while ((byteread = fis.read(buf)) != -1) {
					fos.write(buf);
					fos.flush();
				}
				fis.close();
				fos.close();
				f.delete();
			}
			msg = "删除成功";
		} catch (Exception er) {
			er.printStackTrace();
			msg = "删除失败";
		}

		return msg;
	}
}
