package org.javafans.template;

import java.util.List;

import org.javafans.common.dto.Attribute;

import com.google.common.collect.Lists;

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
