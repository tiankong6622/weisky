package org.itboys.framework.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.itboys.framework.resource.PropertiesUtils;


/**
 * 初始属性监听器 启动时 将common.properties 的所有属性放到 application里
 * @author ChenJunhui
 */
public class InitPropertiesListner implements ServletContextListener {
	
	private String properties; //需要将哪个熟悉文件值弄到 application里
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/**把common.properties所有变量放在application里**/
		ServletContext application = event.getServletContext();
		Properties commonProperties;
		try {
			commonProperties = PropertiesUtils.loadProperties(properties);
			if(commonProperties!=null){
				Set<Object> keySet = commonProperties.keySet();
				for(Iterator<Object> iter = keySet.iterator();iter.hasNext();){
					String key = (String) iter.next();
					String value = commonProperties.getProperty(key);
					application.setAttribute(key, value);
				}
			}

		} catch (IOException e) {
			commonProperties = new Properties();
		}
		
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

}
