package org.javafans.common.utils.encode;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码相关
 * @author ChenJunhui
 */
public abstract class CodeUtils {
	
	protected static  Logger logger = LoggerFactory.getLogger(CodeUtils.class);
	
	public static String changeRequestEncode(String str ,String preEncode,String encode){
		if(StringUtils.isNotBlank(str)){
			try {
				str = new String(str.getBytes(preEncode), encode);
				if(logger.isDebugEnabled()){
					logger.debug("changeRequestEncode str="+str);
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("change code error!", e);
			}
		}
		return str;
	}
}
