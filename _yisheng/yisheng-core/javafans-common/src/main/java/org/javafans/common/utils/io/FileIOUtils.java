package org.javafans.common.utils.io;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class FileIOUtils {

	/**
	 * 关闭流
	 * @param stream
	 */
	public static void close(Closeable stream){
		if(stream!=null){
			try {
				stream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * http下载文件
	 * @param response
	 * @param fileName 文件名
	 * @param filePath 文件路径
	 */
	public static void download(HttpServletRequest request,HttpServletResponse response,String fileName,String filePath){
		OutputStream out = null;
		InputStream in = null;
		try{
			String codeFileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
			  if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
		        	codeFileName = URLEncoder.encode(fileName, "UTF-8");//NND IE
		        }
			response.setCharacterEncoding("utf-8");
			//response.setContentType("multipart/form-data");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;fileName=" + codeFileName);
			out = response.getOutputStream();
			in = new FileInputStream(filePath);
			IOUtils.copy(in, out);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			close(in);
			close(out);
		}
	}
	
	
	/**
	 * 获取 /yyyy/MM/file.ext 格式的原始文件名
	 * 比如 /2012/9/file.ext 为 file.ext
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName){
		if(StringUtils.isBlank(fileName) && fileName.indexOf("/")!=-1){
			return fileName;
		}
		return StringUtils.substring(fileName, fileName.lastIndexOf("/")+1);
	}


}
