package org.itboys.framework.template;

import java.util.List;

import com.google.common.collect.Lists;

import org.itboys.commons.dto.Attribute;

/**
 * vm模板生成 key value对象
 * @author ChenJunhui
 */
public class VmContextModel {
	
	private List<Attribute> attrs = Lists.newArrayList();
	
	public VmContextModel addAttribute(String key,Object value){
		attrs.add(new Attribute(key,value));
		return this;
	}

	public List<Attribute> getAttrs() {
		return attrs;
	}
}
