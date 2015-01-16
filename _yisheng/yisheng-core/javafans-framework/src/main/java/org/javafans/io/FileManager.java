package org.javafans.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.javafans.resources.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件管理器 
 * @author ChenJunhui
 *
 */
public class FileManager {

	private  static Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	public static final String fileUploadTmpPath = ResourceConfig.getSysConfig("fileUploadTmpPath");
	public static final String fileUploadPath = ResourceConfig.getSysConfig("fileUploadPath");
	
	/**
	 * 删除临时文件 copy 到正式文件夹
	 * @param path
	 */
	public static void chageFile(String filepath){
		InputStream in = null;
		OutputStream out = null;
		try{
			File  file = new File(fileUploadTmpPath+filepath);
			if(file.exists()){
				in = new FileInputStream(file);
				String fileDir = fileUploadPath+filepath;
				int idx = fileDir.lastIndexOf("/");
				String dirPath = fileDir.substring(0,idx);
				File f = new File(dirPath);
				if(!f.exists()){
					f.mkdir();
				}
				out = new FileOutputStream(fileDir) ;
				IOUtils.copy(in,out);
				file.delete();
			}
		}catch(Exception e){
			logger.error("delete tmp file error path="+filepath,e);
		}finally{
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
			File  file = new File(fileUploadTmpPath+filepath);
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	/**
	 * 根据路径删除文件
	 * @param path
	 */
	public static void deleteFile(String path){
		try{
			File  file = new File(fileUploadPath+path);
			if(file.exists()){
				file.delete();
			}
		}catch(Exception e){
			logger.error("deleteFile error path="+path,e);
		}
	}
}
