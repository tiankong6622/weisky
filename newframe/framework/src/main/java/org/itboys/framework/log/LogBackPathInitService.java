package org.itboys.framework.log;

import java.util.Map;

/**
 * 初始化logback的一些路径的 
 * @author ChenJunhui
 *
 */
public class LogBackPathInitService {

	private Map<String,String> logPathMap;
	
	 public void init(){  
       //TODO 还没搞 
     }  

	public Map<String, String> getLogPathMap() {
		return logPathMap;
	}

	public void setLogPathMap(Map<String, String> logPathMap) {
		this.logPathMap = logPathMap;
	}
}
