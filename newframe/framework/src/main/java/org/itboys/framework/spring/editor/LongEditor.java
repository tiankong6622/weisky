package org.itboys.framework.spring.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Integer 转换 如果是非 Long 的 直接转 null 
 * @author ChenJunhui
 *
 */
public class LongEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(NumberUtils.isDigits(text)?Long.parseLong(text):null);
	}
}
