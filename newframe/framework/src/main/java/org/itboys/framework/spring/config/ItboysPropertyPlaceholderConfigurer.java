package org.itboys.framework.spring.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 重写spring的属性设置
 * @author 俊哥
 *
 */
public class ItboysPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	
	public ItboysPropertyPlaceholderConfigurer(){
		//如果java启动参数加了  -Dprofile-name=real 那么就会加载 classpath:/real.properties 的属性文件 
		//如果java启动参数加了  -Dprofile-name=test 那么就会加载 classpath:/test.properties 的属性文件
		//以此类推 如果启动参数没加任何东西 就会加载开发环境 classpath:/dev.properties 的属性文件
		String profile = System.getProperty("profile-name");
		String proName = StringUtils.isBlank(profile)?"classpath:/dev.properties":"classpath:/"+profile+".properties";
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource(proName);
		this.setLocation(resource);
	}
	
}
