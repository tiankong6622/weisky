package org.javafans.common.utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.javafans.common.utils.time.TimeUtils;

public class IOUtils {

	public static String inputStream2String(InputStream is){
		BufferedReader in = null;
		Reader reader = null;
		try{
			   reader = new InputStreamReader(is);
			    in = new BufferedReader(reader);
			    StringBuilder buffer = new StringBuilder();
			    String line = "";
			    while ((line = in.readLine()) != null){
			      buffer.append(line);
			    }
			    return buffer.toString();
		} catch (Exception e){
			throw new RuntimeException(e);
		}finally{
			org.apache.commons.io.IOUtils.closeQuietly(reader);
			org.apache.commons.io.IOUtils.closeQuietly(in);
			org.apache.commons.io.IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * 将互联网文件保存本地
	 * @param url
	 * @param locatPath
	 */
	public static boolean saveInterNetFileToLocal(String url,String locatPath){
		HttpURLConnection connection = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			URL getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			in = connection.getInputStream();
			out=new FileOutputStream(locatPath);
			org.apache.commons.io.IOUtils.copy(in, out);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			org.apache.commons.io.IOUtils.closeQuietly(in);
			org.apache.commons.io.IOUtils.closeQuietly(out);
			if(connection!=null){
				connection.disconnect();
			}
		}
	}
	
	public static void createHtml(String fileName,String content) throws IOException{
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
			org.apache.commons.io.IOUtils.write(content,out);
			out.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}finally{
			org.apache.commons.io.IOUtils.closeQuietly(out);
		}
	}
	
	public static void main(String args[]){
		String dir = TimeUtils.format(new java.util.Date(), "yyyy-MM-dd");
		File file = new File("D:/images/aaa"+FileUtils.FILE_SEPARATOR+dir);
		if(!file.exists()){
			file.mkdir();
		}
		String fileName = FileUtils.FILE_SEPARATOR+dir+FileUtils.FILE_SEPARATOR+UUID.randomUUID().toString()+".pdf";
		saveInterNetFileToLocal("http://static.mochayuan.com/Activiti.pdf","D:/images/aaa"+fileName);
	}
}
