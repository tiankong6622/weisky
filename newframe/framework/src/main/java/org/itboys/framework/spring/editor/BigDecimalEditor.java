package org.itboys.framework.spring.editor;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.math.NumberUtils;

public class BigDecimalEditor extends PropertyEditorSupport {

	public final static DecimalFormat df = new DecimalFormat("#.##");
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!NumberUtils.isNumber(text)){
			setValue(null);
		}else{
			setValue(new BigDecimal(df.format(new BigDecimal(text))));
		}
	}
}
