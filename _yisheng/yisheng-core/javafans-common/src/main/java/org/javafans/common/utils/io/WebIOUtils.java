package org.javafans.common.utils.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * webIO相关操作
 * @author ChenJunhui
 */
public class WebIOUtils {

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
	
	public static void main(String[] args){
		String result = WebIOUtils.getUrlText("http://220.241.102.211/index/query-info", "UTF-8");
		System.out.println(result);
	}
}
