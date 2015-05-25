package org.itboys.commons.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.image.ImageUtils;
import org.itboys.commons.utils.time.TimeUtils;



/**
 * 文件Utils
 * @author chenjunhui 
 */
public class FileUtils {
	
	
	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static final String FILE_SEPARATOR = "/";

	/**
	 * 请fileDirs不要为空
	 * 
	 * @param fileDirs
	 * @return
	 */
	public static String getFileDir(final String imageDir,String... fileDirs) {
		String dir = imageDir;
		for (String fileDir : fileDirs) {
			dir = dir + FILE_SEPARATOR + fileDir;
		}
		dir += FILE_SEPARATOR;
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}

	public static String getFileName(String savedFilePath,String proFileName, String... fileDirs) {
		return getFileDir(savedFilePath,fileDirs) + getRandomFileName(proFileName);
	}

	public static String getDateFileName(String proFileName) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		String fileName = getFileName(proFileName, String.valueOf(year), String
				.valueOf(month));
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				fileName = null;
			}
		}
		return fileName;
	}

	/**
	 * 先调用其他方法包装proFileName不为空且含有"."
	 * 
	 * @param proFileName
	 * @return
	 */
	public static String getRandomFileName(String proFileName) {
		int length = proFileName.lastIndexOf(".");
		if(length==-1){
			return UUID.randomUUID().toString();
		}
		String suffix = proFileName.substring(length);
		return UUID.randomUUID().toString() + suffix;
	}

	/**
	 * 是否是Image
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean isImage(String fileType) {
		for (String type : CommonConstants.imgFileTypes) {
			if (type.equals(fileType))
				return true;
		}
		return false;
	}

	/**
	 * 将FTP地址改成web地址
	 * 
	 * @param ftpDir
	 * @param imgRoot
	 * @return
	 */
	public static String changeToWebRoot(String ftpDir, String imgRoot) {
		int index = ftpDir.indexOf(imgRoot) + imgRoot.length();
		return ftpDir.substring(index).replace(
				System.getProperty("file.separator"), "/");
	}

	public static void main(String args[]) {
		System.out.println(FileUtils.getFileDir("D:/aa", DateFormatUtils.format(new Date(), "yyyy-MM-dd")));
		/*System.out.println(getRandomFileName("aaaa.jpg"));
		System.out.println(FileUtils.getFileName("aaaa.jpg", "2011","10"));
		System.out.println(FileUtils.changeToWebRoot(FileUtils.getFileName("aaaa.jpg", "2011","10"), "d:/sdfsdf"));*/
	}
	
	public static final String IMG_START = "image/";
	
	public static boolean isImage(String[] imgTypes){
		if(imgTypes!=null && imgTypes.length>0){
			for(String imgType:imgTypes){
				if(!imgType.startsWith(IMG_START)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 保存文件
	 * @param stream
	 * @param fileName
	 * @param fileContentType
	 * @return
	 */
	public static String saveFile(InputStream stream,String saveImageDir,String fileName, String fileContentType){
		try{
			String realPath = FileUtils.getFileName(saveImageDir,fileName, String
					.valueOf(TimeUtils.getCurrentYear()), String
					.valueOf(TimeUtils.getCurrentMonth()));
			IOUtils.copyLarge(stream, new FileOutputStream(realPath));
			return changeToWebRoot(realPath, saveImageDir);	
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("close stream fail",e);
			}
		}
	}
	
	public static String saveFile(File file,String fileName,String saveFileDir, String fileContentType){
		try{
			InputStream stream = new FileInputStream(file);
			return FileUtils.saveFile(stream,saveFileDir, fileName, fileContentType);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 保存图片 并返回物理路径和 相对web路径
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @param width
	 * @param height
	 * @return String[] length=2 String[0] = 物理路径 String[1] = 相对web路径 
	 */
	public static String saveImage(File file,String savedDir,String fileName,String fileContentType,int width,int height){
		try{
			InputStream stream = new FileInputStream(file);
			return saveImage(stream,savedDir,fileName, fileContentType, width, height);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param stream
	 * @param saveImageDir 需要保存图片到那个目录
	 * @param fileName
	 * @param fileContentType
	 * @param width
	 * @param height
	 * @return
	 */
	public static String saveImage(InputStream stream,String saveImageDir,String fileName, String fileContentType,
			int width, int height){
		try {
			if (!StringUtils.startsWith(fileContentType, IMG_START)) {
				throw new RuntimeException("the file type is not image");
			}
			String realPath = FileUtils.getFileName(saveImageDir,fileName, String
					.valueOf(TimeUtils.getCurrentYear()), String
					.valueOf(TimeUtils.getCurrentMonth()));
			if (width > 0 || height > 0) { // 传了限制宽高的话 就压缩
				ImageUtils.saveImageAsJpg(stream, realPath, width, height, true);
			} else {// 不压缩的话 直接保存
				IOUtils.copyLarge(stream, new FileOutputStream(realPath));
			}
			return changeToWebRoot(realPath, saveImageDir);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("close stream fail",e);
			}
		}
	}
	
	/**
	 * 保存图片 并返回物理路径和 相对web路径
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @return String[] length=2 String[0] = 物理路径 String[1] = 相对web路径 
	 */
	public static String saveImage(File file,String savedDir,String fileName,String fileContentType){
		return FileUtils.saveImage(file, savedDir,fileName, fileContentType, 0, 0);
	}
	
	public static String saveImage(InputStream stream,String savedDir,String fileName,String fileContentType){
		return FileUtils.saveImage(stream, savedDir,fileName, fileContentType, 0, 0);
	}
}
