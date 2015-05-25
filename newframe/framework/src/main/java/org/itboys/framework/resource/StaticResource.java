package org.itboys.framework.resource;

import org.itboys.framework.spring.context.SpringContextHolder;

public class StaticResource {

	private ResourceHolder resourceHolder = SpringContextHolder.getBean("staticResourceHolder");
	
	public Object get(String key){
		return resourceHolder.getValue(key);
	}
}
