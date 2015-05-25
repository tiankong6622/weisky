package org.itboys.commons.utils.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * IO操作相关
 * @author ChenJunhui
 *
 */
public class IOUtils {

	/**
	 * 文件流转String
	 * @param is
	 * @return
	 */
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
	
	
	/**
	 * 获取网络文件的文本内容 或者网络文本文件的类容
	 * @param strUrl 网络地址
	 * @param strEncoding 网络文件的编码
	 * @return
	 */
	public static String getUrlText(String strUrl, String strEncoding){
		try{
			URL url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			InputStream in = null;
			in = url.openStream();
			String content = getContent(in, strEncoding);
			return content;
		}catch(Exception e){
			throw new RuntimeException("getUrl text fail,url="+strUrl);
		}
	}
	
	/**
	 * 讲一个web地址转成文件流
	 * @param webUrl
	 * @return
	 */
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
			throw new RuntimeException("getDataInputStream from "+webUrl+" fail:",e);
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

	private static String getContent(InputStream in, String charset)
			throws IOException {
		StringBuilder s = new StringBuilder();
		String rLine = null;
		BufferedReader bReader = new BufferedReader(new InputStreamReader(in,charset));
		while ((rLine = bReader.readLine()) != null) {
			s.append(rLine);
			s.append("\n");
		}
		return s.toString();
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
	
}
