package org.itboys.framework.spring.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import org.itboys.commons.CommonConstants;

/**
 * 处理时间绑定 多种格式绑定 
 * see org.openfans.common.utils.time.TimeUtils.PARSE_FORMAT_ARR
 * @author ChenJunhui
 */
public class DateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isBlank(text)) {
			setValue(null);
		}else {
			try {
				setValue(DateUtils.parseDate(text, CommonConstants.DATE.PARSE_FORMAT_ARR));
			}
			catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}

}
