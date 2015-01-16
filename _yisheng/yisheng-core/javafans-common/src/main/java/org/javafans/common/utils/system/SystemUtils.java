package org.javafans.common.utils.system;

import org.apache.commons.lang3.StringUtils;
import org.javafans.common.constants.CommonConstants;

/**
 * 系统相关工具类
 * @author ChenJunhui
 */
public abstract class SystemUtils {

	/**
	 * 判断当前操作系统是不是windows true 是  false 就是unix或linux 其他操作系统部考虑
	 * @author ChenJunhui
	 * @return
	 */
	public static boolean isWindowsSystem(){
		String system = System.getProperties().getProperty(CommonConstants.OS_NAME);
		if(StringUtils.isNotBlank(system) && (system.toUpperCase().indexOf(CommonConstants.WINDOWS_UPPER) != -1)){
			return true;
		}
		return false;
	}
}
