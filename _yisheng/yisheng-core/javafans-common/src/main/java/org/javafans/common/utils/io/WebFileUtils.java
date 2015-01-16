/*package org.javafans.common.utils.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


*//**
 * 对网络文件操作的一些方法
 * @author chenjunhui
 *//*
public  class WebFileUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(WebFileUtils.class);
	
	public static final String DEFAULT_ENCODE = "UTF-8";
	
	*//**
	 * 获取网络文件的文本内容 或者网络文本文件的类容
	 * @param strUrl 网络地址
	 * @param strEncoding 网络文件的编码
	 * @return
	 *//*
	public static String getHtmlText(String strUrl, String strEncoding){
		try{
			URL url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			InputStream in = null;
			in = url.openStream();
			String content = getContent(in, strEncoding);
			return content;
		}catch(Exception e){
			throw new RuntimeException("getHtml text fail,url="+strUrl);
		}
	}
	

	private static String getContent(InputStream in, String charset)
			throws IOException {
		StringBuilder s = new StringBuilder();
		String rLine = null;
		BufferedReader bReader = new BufferedReader(new InputStreamReader(in,
				charset));
		while ((rLine = bReader.readLine()) != null) {
			s.append(rLine);
			s.append("\n");
		}
		return s.toString();
	}
	
	
	*//**
	 * 抓取图片放到字节数据里
	 * @param url
	 * @return
	 * @throws Exception
	 *//*
	public static byte[] getImageFileBytes(String url) throws Exception{
		URL httpurl = new URL(url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) httpurl
				.openConnection();
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("GET");
		String fileType = httpURLConnection.getContentType();
		InputStream inputStream = httpURLConnection.getInputStream();
		byte[] bArr =  getImageToByteArr(inputStream,fileType);
		return bArr;
	}
	
	
	private static byte[] getImageToByteArr(InputStream inputStream,String fileType) throws Exception{
		DataInputStream dis = null;
		ByteArrayOutputStream baos = null;
		byte[] b = null;
		try {
			if (fileType!=null
					&& fileType.indexOf("image") != -1) {
				dis = new DataInputStream(inputStream);
				baos = new ByteArrayOutputStream();
				int ch = 0;
				while ((ch = dis.read()) != -1) {
					baos.write(ch);
				}
				b = baos.toByteArray();
			} else {
				b = new byte[inputStream.available()];
				inputStream.read(b);
			}
			
		} catch (IOException e) {
			logger.error("read file error:", e);
			throw new Exception("read file error:", e);
		} finally {
			if (baos != null) {
				baos.close();
			}
			if (dis != null) {
				dis.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return b;
        
    }
	
	
	public static void imgUrlToFile(String fileName,String imgUrl) throws Exception{
		OutputStream os = new FileOutputStream(fileName);
		byte[] b =  WebFileUtils.getImageFileBytes(imgUrl);
		os.write(b);
		os.flush();
		os.close();
	}
	
	
	public static InputStream getDataInputStream(String webUrl){
		HttpURLConnection httpURLConnection=null;
		InputStream inputStream = null;
		DataInputStream dis = null;
		try{
			URL httpurl = new URL(webUrl);
			httpURLConnection = (HttpURLConnection) httpurl
					.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestMethod("GET");
			inputStream = httpURLConnection.getInputStream();
			dis = new DataInputStream(inputStream);
		}catch(Exception e){
			logger.error("getDataInputStream from "+webUrl+" fail:",e);
		}finally{
			try{
				if(dis!=null){
					dis.close();
				}
				if(inputStream!=null){
					inputStream.close();
				}
				if(httpURLConnection != null){
					httpURLConnection.disconnect();
				}
			}catch(IOException ie){
				ie.printStackTrace();
			}
			
		}
		return dis;
	}
	
	public static String replace(String text, String rstr, String beRstr){
		return text.replaceAll(rstr, beRstr);
	}
	
	public static void outPutXml(Writer out, Document document){
		XMLWriter writer = null;
		try{
           *//** 将document中的内容写入对应的输出流中 *//*
		   writer = new XMLWriter(out);
           writer.write(document);
        }catch(Exception ex){
        	throw new RuntimeException(ex);
       }finally{
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
       }
	}
	
	
	public static void main(String args[]) throws Exception{
		String text = WebFileUtils.getHtmlText("http://www.maidoutuan.com/bory/front/nuomi/main_new5546.css", "UTF-8");
		
		String regex = "([\\s]*http://static.nuomi.com.+?\\.(png|jpg|gif))";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(text);
		String baseDir = "D:/tmp1";
		File f = new File(baseDir);
		if(!f.exists()){
			f.mkdir();
		}
		Set<String> set = new HashSet<String>();
		while(matcher.find()){
			set.add( matcher.group(1));
		}
		System.out.println(set.size());
		for(String urlString:set){
			String regstr = urlString;
			regstr = regstr.replace("http://static.nuomi.com/img/", "");
			String[] imgArr = regstr.trim().split("/");
			int length = imgArr.length;
			if(length==1){
				WebFileUtils.imgUrlToFile(baseDir+"/"+regstr, urlString);
			}else{
				String dir = baseDir;
				for(int i=0;i<imgArr.length-1;i++){
					dir=dir+"/"+imgArr[i];
					File file = new File(dir);
					if(!file.exists()){
						file.mkdir();
					}
				}
				WebFileUtils.imgUrlToFile(dir+"/"+imgArr[imgArr.length-1], urlString);
			}
		}
	}
}
*/