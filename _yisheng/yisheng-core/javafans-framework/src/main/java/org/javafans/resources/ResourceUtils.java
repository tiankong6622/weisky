package org.javafans.resources;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.javafans.common.utils.exception.Exceptions;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 资源配置相关操作
 * @author ChenJunhui
 *
 */
public class ResourceUtils {

	/**
	 * 将 K-V 资源的配置 成如下定义的xml结构的转成map
	 * <?xml version="1.0" encoding="UTF-8"?>
		<configs>
			<config key="key1">value1</config>
			<config key="key2">value2</config>
		</configs>
	 * @author ChenJunhui
	 * @param resourcePath 资源文件的路径
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getKVResource(String resourcePath){
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		try{
			Resource resource = resourceLoader.getResource(resourcePath);
			SAXReader reader = new SAXReader();
			InputStream is = resource.getInputStream();
			Document document = reader.read(is);
			List<Element> elements = document.selectNodes("/configs/config");
			if(elements!=null && !elements.isEmpty()){
				Map<String,String> map = new HashMap<String, String>(elements.size());
				for(Element element:elements){
					map.put(element.attributeValue("key"), element.getText());
				}
				return map;
			}
		}catch(Exception e){
			throw Exceptions.unchecked(e);
		}
		
		return new HashMap<String, String>(0);
	}
	
	public static void main(String[] args){
		getKVResource("resource-config.xml");
	}
}
