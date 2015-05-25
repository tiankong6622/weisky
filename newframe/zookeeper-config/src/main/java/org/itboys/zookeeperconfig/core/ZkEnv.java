package org.itboys.zookeeperconfig.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.curator.utils.EnsurePath;

import com.google.common.collect.Maps;

public class ZkEnv {

	// TODO  本工程 不要依赖其他的包 除了zk的包 可以用jdk自带的IO 解析
	public static void writeToZk() {
		String userDir = System.getProperty("user.dir");
		String userHome= System.getProperty("user.home");
		String envFile=userHome+File.separator+"env.conf";
		Map<String , Object> envConf=Maps.newHashMap();
		FileReader fr;
		try {
			fr = new FileReader(envFile);
		
		BufferedReader br=new BufferedReader(fr);
		String temp;
		while((temp=br.readLine())!=null){
			int index=temp.indexOf("=");
			String key=temp.substring(0, index);
			String value=temp.substring(index+1);
			envConf.put(key, value);
			
		}
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			
		}
		// 文件路径分隔符 File.separator
		//TODO 于教授 把 test/resource 的 env.conf 放到 当前系统的用户路径
		// 比如 C盘  C:\Users\Administrator 看你自己是哪个路径
		//然后 叫兽.于 解析resource后 逐行读取 env.conf 去除zookeeper的server配置和端口 以及当前项目名称
		// 然后叫兽 解析 env-config.xml 这个文件也放到当前系统的用户路径
		// 于教授把 env-config.conf 的配置全部写入 zk 遇到第二个没有 = 号的 就当一个新的project 即zk的一个新路径
		//每个 没有=号的 project 和 xxx=bbb 的配置写入zk xxx为key bbb为value
		// 可以百度 java 文件读行 =号劈开 过滤空格 等等
	}
	
	public static Properties  loadProjectConfig(String projectName){
		String userDir = System.getProperty("user.dir");
		String userHome= System.getProperty("user.home");
		String envFile=userHome+File.separator+"env.conf";
		Properties prop=new Properties();
		FileReader fr;
		try {
			fr = new FileReader(envFile);
		
		BufferedReader br=new BufferedReader(fr);
		String temp;
		while((temp=br.readLine())!=null){
			int index=temp.indexOf("=");
			String key=temp.substring(0, index);
			String value=temp.substring(index+1);
			prop.put(key, value);
			
		}
		System.out.println("----------------------");
		System.out.println(prop);
		br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			
		}
		//TODO 于教授 把 test/resource 的 env.conf 放到 当前系统的用户路径
		// 比如 C盘  C:\Users\Administrator 看你自己是哪个路径
		//然后 叫兽.于 解析resource后 逐行读取 env.conf 去除zookeeper的server配置和端口 以及当前项目名称
		//于教授 把 projectName 在ZK 配置路径下的 配置读出来 封装成 Properties 返回
		// 可以i百度 java Properties
		return prop;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.home"));
		writeToZk();
		loadProjectConfig("1");
	}
}
