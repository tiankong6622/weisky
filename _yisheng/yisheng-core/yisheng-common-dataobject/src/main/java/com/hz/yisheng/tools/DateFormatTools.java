package com.hz.yisheng.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatTools {

	public static final Map<String,SimpleDateFormat> map = new HashMap<String, SimpleDateFormat>();
	
	public static String formatDate(Date date,String format){
		if(date==null || format==null)
			return null;
		SimpleDateFormat sdf = map.get(format);
		if(sdf==null){
				sdf = prepareFmt(format);
		}
		return sdf.format(date);
	}

	private synchronized static SimpleDateFormat prepareFmt(String format) {
		SimpleDateFormat sdf  = new SimpleDateFormat(format);
		map.put(format, sdf);
		return sdf;
	}
}
