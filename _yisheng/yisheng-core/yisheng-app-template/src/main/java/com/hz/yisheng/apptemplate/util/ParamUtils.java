package com.hz.yisheng.apptemplate.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.hz.yisheng.admin.AdminConstants;

/**
 * 查询参数工具类
 * @author WeiSky
 *
 */
public class ParamUtils {

	/**
	 * 组装分页查询参数
	 * @param request
	 * @param pageSize
	 * @param pageKey
	 * @return
	 */
	public static Map<String,Object>  preparePageQueryParam(HttpServletRequest request){
		Integer pageNo = Integer.parseInt(request.getParameter("page"));
		if(pageNo==null || pageNo<1){
			pageNo = 1;
		}
		Integer page_size = Integer.parseInt(request.getParameter("rows"));
		long rowStart = (pageNo-1)*page_size;
		//组装查询参数
		Map<String,Object> sqlMap = ParamUtils.buildQueryParam(request);
		sqlMap.put("rowStart", rowStart);
		sqlMap.put("pageNo", pageNo);
		sqlMap.put("pageSize", page_size);
		return sqlMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,Object> buildQueryParam(HttpServletRequest request){
		Map map = request.getParameterMap();
		Set<Object> kvSet = map.entrySet();
		Map<String,Object> param = new HashMap<String,Object>(kvSet.size());
		for(Iterator<Object> itr = kvSet.iterator();itr.hasNext();){
			Map.Entry ent = (Entry) itr.next();
			String key = (String) ent.getKey();
			if(StringUtils.startsWithIgnoreCase(key, AdminConstants.PARAM_)){
				String[] values = (String[]) ent.getValue();
				if(values != null && values.length==1){
					param.put(getKey(key), values[0]);
				}
			}
		}
		return param;
	}
	
	/**
	 * 截取最终查询用的Key值
	 * @param key
	 * @return
	 */
	public static String getKey(String key){
		String tempKey = key.substring(AdminConstants.PARAM_.length());
		if(StringUtils.startsWithIgnoreCase(tempKey, AdminConstants.STRING_PREFIX)){
			return tempKey.substring(AdminConstants.STRING_PREFIX.length());
		}else if(StringUtils.startsWithIgnoreCase(tempKey, AdminConstants.DATE_PREFIX)){
			return tempKey.substring(AdminConstants.DATE_PREFIX.length());
		}else if(StringUtils.startsWithIgnoreCase(tempKey, AdminConstants.LONG_PREFIX)){
			return tempKey.substring(AdminConstants.LONG_PREFIX.length());
		}else{
			return null;
		}
	}
}
