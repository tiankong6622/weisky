package org.itboys.commons.utils.time;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import org.itboys.commons.CommonConstants;

/**
 * 时间 日期相关封装
 * @author ChenJunhui
 */
public class TimeUtils {
	
	public static String format(Date date,String pattern){
		if(date==null){
			return null;
		}
		try{
			return DateFormatUtils.format(date, pattern);
		}catch(Exception e){
			return StringUtils.EMPTY;
		}
	}

	/**
 	* 根据所给的起始时间,间隔天数来计算终止时间
 	* 
	* @param startDate
	* @param day
	* @return 终止时间
	*/
	public static java.sql.Date getStepDay(java.util.Date date,int step){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR,step);
		return new java.sql.Date(calendar.getTime().getTime());
	}
	
	/**
	 * 对象转日期 确认能转 则调用
	 * @param obj
	 * @return
	 */
	public static Date getDateValue(Object obj) {
		if (obj == null)
			return null;
		String str = obj.toString();
		if(StringUtils.isBlank(str)){
			return null;
		}
		try {
			return DateUtils.parseDate(str.trim(),
					CommonConstants.DATE.PARSE_FORMAT_ARR);
		} catch (ParseException e) {
			throw new RuntimeException("wrong date format");
		}
	}
	
	/**
	 * 获得当前年
	 * @return
	 */
	public static int getCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 获得程序运行当前月
	 * @return
	 */
	public static int getCurrentMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int month = calendar.get(Calendar.MONTH)+1;
		return month;
	}
	
	/**
	 * 获得某个日期所属年份
	 * @param date
	 * @return
	 */
	public static int getYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	/**
	 * 获得某个时间所属月
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH)+1;
		return month;
	}
	
	/**
	 * 获得某个日期所属天
	 * @param date
	 * @return
	 */
	public static int getDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		return day;
	}
	
	/**
	 * 判断2个日期是否是同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1,Date date2){
		if(date1==null || date2==null){
			return false;
		}
		if((getYear(date1)!=getYear(date2)) ||  (getMonth(date1)!=getMonth(date2)) || (getDay(date1)!=getDay(date2))){
			return false;
		}
		return true;
	}
	
	/**
	 * 获得当前时间的格式化时间
	 * @param format
	 * @return
	 */
	public static String getCurrentTime(String format){
		return DateFormatUtils.format(new Date(), format);
	}
	
	/**
	 * 将给定的年月日转换成时间对象
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(Integer year,Integer month,Integer day){
		if(year==null || month==null || day==null)
			return null;
		Calendar calendar = Calendar.getInstance();
		try{
			calendar.set(year, month-1, day);
			return calendar.getTime();
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 获得当前时间的早晨 00:00:00分
	 * @param date
	 * @return
	 */
	public static Date getDayFirstTime(Date date){
		try {
			return DateUtils.parseDate(DateFormatUtils.format(date,
					CommonConstants.DATE.FORMAT_NO_STR),
					CommonConstants.DATE.FORMAT_NO_STR );
		} catch (ParseException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 获得某一年的第一天
	 * @return
	 */
	public static Date getYearFirstDay(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		String time = year+"-1-1";
		try {
			return DateUtils.parseDate(time, CommonConstants.DATE.FORMAT_YYYY_MM_dd);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 判断某个时间是否大于当前时间
	 * @param date
	 * @return
	 */
	public static boolean afterNow(Date date){
		if(date==null)
			return false;
		return date.after(new Date());
	}
	
	/**
	 * 判断某个时间是否小于当前时间
	 * @param date
	 * @return
	 */
	public static boolean beforeNow(Date date){
		if(date == null)
			return false;
		return date.before(new Date());
	}
	
	/**
	 * 两个时间相差的分数
	 * @param start
	 * @param end
	 * @return
	 */
	public static Integer getMinBetween(Date start,Date end){
		if(start==null || end==null){
			return null;
		}
		Long startTime = start.getTime();
		Long endTime = end.getTime();
		Long between = endTime-startTime;
		return new BigDecimal(between/60000).intValue();
	}
	
	/**
	   * 根据日期获得星期几的汉字
	   * @param date
	   * @return
	   */
	  public static String getWeekOfDate(Date date){
	    Calendar calendar=Calendar.getInstance();
	    calendar.setTime(date);
	    int intWeek=calendar.get(Calendar.DAY_OF_WEEK) - 1;
	    return CommonConstants.DATE.weekDaysName[intWeek];
	  }
	  
	  /**********************************************************************************************************/
	  /**
	   * 得到应用服务器当前日期，只有年月日，没有时分秒
	   * 
	   * @return java.sql.Date
	   */
	  public static java.sql.Date getDate(){
	    Calendar oneCalendar=Calendar.getInstance();
	    return getDate(oneCalendar.get(Calendar.YEAR),oneCalendar.get(Calendar.MONTH) + 1,oneCalendar.get(Calendar.DATE));
	  }
	  /**
	   * 根据所给年、月、日，得到日期(java.sql.Date类型)，注意：只有年月日，没有时分秒。 年、月、日不合法会抛IllegalArgumentException
	   * 
	   * @param yyyy 4位年
	   * @param MM 月
	   * @param dd 日
	   * @return 日期
	   */
	  public static java.sql.Date getDate(int yyyy,int MM,int dd){
	    return getDate(yyyy,MM,dd,0,0,0);
	  }
	  /**
	   * 根据所给年、月、日、时、分、秒，得到日期(java.sql.Date类型)。 年、月、日不合法会抛IllegalArgumentException
	   * 
	   * @param yyyy 4位年
	   * @param MM 月
	   * @param dd 日
	   * @param HH 时
	   * @param mm 分
	   * @param ss 秒
	   * @return
	   */
	  public static java.sql.Date getDate(int yyyy,int MM,int dd,int HH,int mm,int ss){
	    if(!verityDate(yyyy,MM,dd))
	      throw new IllegalArgumentException("不是有效的时间");
	    Calendar oneCalendar=Calendar.getInstance();
	    oneCalendar.clear();
	    oneCalendar.set(yyyy,MM - 1,dd,HH,mm,ss);
	    return new java.sql.Date(oneCalendar.getTime().getTime());
	  }
	  /**
	   * 根据所给年、月、日，检验是否为合法日期。
	   * 
	   * @param yyyy 4位年
	   * @param MM 月
	   * @param dd 日
	   * @return
	   */
	  public static boolean verityDate(int yyyy,int MM,int dd){
	    boolean flag=false;
	    
	    if(MM >= 1 && MM <= 12 && dd >= 1 && dd <= 31){
	      if(MM == 4 || MM == 6 || MM == 9 || MM == 11){
	        if(dd <= 30){
	          flag=true;
	        }
	      }else if(MM == 2){
	        if(yyyy % 100 != 0 && yyyy % 4 == 0 || yyyy % 400 == 0){
	          if(dd <= 29){
	            flag=true;
	          }
	        }else if(dd <= 28){
	          flag=true;
	        }
	        
	      }else{
	        flag=true;
	      }
	      
	    }
	    return flag;
	  }
	  
	  /**   
	     * 计算两个日期之间相差的天数   
	     * @param smdate 较小的时间  
	     * @param bdate  较大的时间  
	     * @return 相差天数  
	     * @throws ParseException   
	     */     
	    public static Integer daysBetween(Date smdate,Date bdate)  {     
	    	try{
	    		if(smdate==null || bdate==null)
	    			return null;
	    	    SimpleDateFormat sdf=new SimpleDateFormat(CommonConstants.DATE.FORMAT_YYYY_MM_dd);   
	 	        smdate=sdf.parse(sdf.format(smdate));   
	 	        bdate=sdf.parse(sdf.format(bdate));   
	 	        Calendar cal = Calendar.getInstance();     
	 	        cal.setTime(smdate);     
	 	        long time1 = cal.getTimeInMillis();                  
	 	        cal.setTime(bdate);     
	 	        long time2 = cal.getTimeInMillis();          
	 	        long between_days=(time2-time1)/(1000*3600*24);   
	 	             
	 	       return Integer.parseInt(String.valueOf(between_days));            
	    	}catch(Exception e){
	    		throw new RuntimeException(e);
	    	}
	    }     

}
