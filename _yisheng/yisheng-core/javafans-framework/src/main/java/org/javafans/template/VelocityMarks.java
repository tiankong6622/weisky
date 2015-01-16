package org.javafans.template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.javafans.common.dto.Attribute;
import org.javafans.common.utils.exception.Exceptions;
import org.javafans.common.utils.io.FileIOUtils;
import org.springframework.stereotype.Service;

/**
 * velocity 根据模板生成文件相关
 * @author ChenJunHui
 */
@Service
public class VelocityMarks {
	
	private String encode;
	@Resource
	private VelocityEngine velocityEngine;
	
	
	/**
	 * 根据模板参数 将模板解析后的内容 输出到writer 中
	 * @param vmContextModel
	 * @param writer
	 */
	public  void mergeTemplate(String templateName,VmContextModel vmContextModel,Writer writer) {
		VelocityContext context = new VelocityContext();
		List<Attribute> attrs = vmContextModel.getAttrs();
		for (Attribute attr : attrs) {
			context.put(attr.getKey(), attr.getValue());
		}
		try {
			velocityEngine.mergeTemplate(templateName, encode, context,writer);
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}finally{
			FileIOUtils.close(writer);
		}
	}
	
	/**
	 * 返回模板参数 将模板解析后的内容 
	 * @param vmContextModel
	 * @param writer
	 */
	public  String getMergedTemplateContent(String templateName,VmContextModel vmContextModel) {
		StringWriter writer = new StringWriter();
		mergeTemplate(templateName,vmContextModel, writer);
		return writer.getBuffer().toString();
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}
	
}