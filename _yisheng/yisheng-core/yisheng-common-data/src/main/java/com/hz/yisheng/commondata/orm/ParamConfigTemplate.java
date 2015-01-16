package com.hz.yisheng.commondata.orm;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.javafans.common.dto.Option;
import org.javafans.common.utils.CommonUtils;

import com.google.common.collect.Lists;

/**
 * 通用 参数配置模板
 * @author ChenJunhui
 *
 */
public class ParamConfigTemplate extends Codes {
	
	private static final long serialVersionUID = 5192083513027807979L;
	
	public static final String SPLIT1=";";//一级劈开符号
	public static final String SPLIT2=";";//二级劈开符号
	public static final String TYPE_INPUT = "TYPE_INPUT";
	public static final String TYPE_TEXTAREA = "TYPE_TEXTAREA";
	public static final String TYPE_CHECKBOX = "TYPE_CHECKBOX";
	public static final String TYPE_RADIO = "TYPE_RADIO";
	
	/**
	 * 类型 比如参数是文本框 文本区 单选框 复选框等  等等
	 */
	public String paramType;

	private String extendField;
	
	/**
	 * 单选框或多选框的时候 value的 是用冒号和分号劈开的值
	 * 比如 男:1;女:0
	 * @return
	 */
	public List<Option> getChecks(){
		List<Option> options = Lists.newArrayList();
		if(StringUtils.isNotBlank(extendField) && CommonUtils.isIn(this.paramType,TYPE_CHECKBOX,TYPE_RADIO)){
			String[] valueArr = StringUtils.split(extendField.trim(), SPLIT1);
			for(String v:valueArr){
				v=v.trim();
				String[] vArr = StringUtils.split(v, SPLIT2);
				Option o = new Option();
				o.setKey(vArr[1]);
				o.setValue(vArr[0]);
				options.add(o);
			}
		}
		return options;
	}

	public static void main(String args[]){
		ParamConfigTemplate p = new ParamConfigTemplate();
		p.setParamType(TYPE_RADIO);
		p.setExtendField("男:1;女:0");
		System.out.println(ToStringBuilder.reflectionToString(p.getChecks()));
	}

	public String getParamType() {
		return paramType;
	}


	public void setParamType(String paramType) {
		this.paramType = paramType;
	}


	public String getExtendField() {
		return extendField;
	}


	public void setExtendField(String extendField) {
		this.extendField = extendField;
	}
}
