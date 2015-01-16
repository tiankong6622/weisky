package org.javafans.common.utils.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 执行linux命令
 * @author ChenJunhui
 *
 */
public class LinuxCommand {

	/**
	 * 执行linux的命令并返回结果
	 * @param command
	 * @return
	 */
	public static String execute(String command){
		BufferedReader br = null;
		Reader in = null;
		InputStream is = null;
		try {
			Process process =  Runtime.getRuntime().exec(command);
			process.getOutputStream();
			is = process.getInputStream();
			in = new InputStreamReader(is);
			br = new BufferedReader(in);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
		   return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(br);
		}
	}
	
	public static List<String> executeToList(String command){
		BufferedReader br = null;
		Reader in = null;
		InputStream is = null;
		List<String> result = new ArrayList<String>();
		String[] com = new String[]{"/bin/sh","-c",command};
		try {
			Process process =  Runtime.getRuntime().exec(com);
			process.getInputStream();
			is = process.getInputStream();
			in = new InputStreamReader(is);
			br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				result.add(line);
			}
		   return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(br);
		}
	}
}