package org.javafans.common.utils.time;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 时间相关的工具类
 * @author chenjunhui
 */
public abstract class TimeUtils_old {

	public static final String  FORMAT_NO_STR = "yyyyMMdd";
	
	public static final String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_YYYY_MM_dd = "yyyy-MM-dd";
	public static final String FORMAT_YYYY_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_YYYY_MM_dd_HH_mm_ss_S = "yyyy-MM-dd HH:mm:ss.S";
	
	public static final String[] PARSE_FORMAT_ARR = new String[]{FORMAT_DEFAULT,FORMAT_YYYY_MM_dd,FORMAT_YYYY_MM_dd_HH_mm,FORMAT_YYYY_MM_dd_HH_mm_ss_S};
	 
	public static int getCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	public static int getCurrentMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int month = calendar.get(Calendar.MONTH)+1;
		return month;
	}
	
	
	public static int getYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
	public static int getMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH)+1;
		return month;
	}
	
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
	
	public static long getTimems(Date date){
		if(date==null)
			return new Date().getTime();
		return date.getTime();
	}
	
	public static long getTimems(Date date,int days){
		if(date==null)
			date = new Date();
		return getTimems(DateUtils.addDays(date, days));
	}
	
	public static String getCurrentTime(String format){
		return DateFormatUtils.format(new Date(), format);
	}
	
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
			return DateUtils.parseDate(DateFormatUtils.format(date, FORMAT_NO_STR), new String[]{FORMAT_NO_STR}) ;
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
			return DateUtils.parseDate(time, new String[]{"yyyy-MM-dd"});
		} catch (ParseException e) {
			return null;
		}
	}
	
	
	public static boolean afterNow(Date date){
		if(date==null)
			return false;
		return date.after(new Date());
	}
	
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
	   * 得到应用服务器当前日期，只有年月日，没有时分秒
	   * 
	   * @return java.sql.Date
	   */
	  public static java.sql.Date getDate(){
	    Calendar oneCalendar=Calendar.getInstance();
	    return getDate(oneCalendar.get(Calendar.YEAR),oneCalendar.get(Calendar.MONTH) + 1,oneCalendar.get(Calendar.DATE));
	  }
	  
	  /**
	   * 根据月数提到与现在相隔的天数到分钟
	   * 
	   * @param month
	   * @return
	   */
	  public static String getDateByMonth(int month){
	    java.sql.Date date=TimeUtils.getDate();
	    date.setMonth(month - 1);
	    Date startDate=new Date();
	    java.sql.Date endDate=TimeUtils_old.converToDate(TimeUtils_old.getLastDay(date,""));
	    // int day=DateUtil.getIntervalDay(startDate,endDate);
	    long startdate=startDate.getTime();
	    long enddate=endDate.getTime();
	    
	    long interval=enddate - startdate;
	    
	    double day=interval / (1000 * 60 * 60 * 24);
	    
	    int intervalday=(int)(day);
	    if(day > intervalday){
	      intervalday++;
	    }
	    int hours=(int)(interval % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
	    int minue=(int)(interval % (1000 * 60 * 60 * 24) % (1000 * 60 * 60) / (1000 * 60));
	    return intervalday + "天" + hours + "小时" + minue + "分钟";
	  }
	  
	  /**
	   * 根据时间得到与现在相隔的天数到分钟
	   * 
	   * @param endDate
	   * @return
	   */
	  public static String getDateByDate(Date endDate){
	    Date startDate=new Date();
	    // 得到开始和结束时间的毫秒值
	    long startdate=startDate.getTime();
	    long enddate=endDate.getTime();
	    // 开始和结束时间的差
	    long interval=enddate - startdate;
	    // 间隔天
	    double day=interval / (1000 * 60 * 60 * 24);
	    
	    int intervalday=(int)(day);
	    if(day > intervalday){
	      intervalday++;
	    }
	    int hours=(int)(interval % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
	    int minue=(int)(interval % (1000 * 60 * 60 * 24) % (1000 * 60 * 60) / (1000 * 60));
	    
	    return intervalday + "天" + hours + "小时" + minue + "分钟";
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
	   * 获取应用服务器系统时间，既有年月日，又有时分秒
	   * 
	   * @return java.sql.Date
	   */
	  public static java.sql.Date getSystemCurrentTime(){
	    long currentTime=System.currentTimeMillis();
	    return new java.sql.Date(currentTime);
	  }
	  
	  /**
	   * 获取应用服务器系统时间，既有年月日，又有时分秒，并按照格式串返回
	   * 
	   * @param type 格式字符串
	   * @return
	   */
	  public static String getSystemCurrentTime(String type){
	    Calendar calendar=Calendar.getInstance();
	    Object obj=null;
	    java.util.Date date=calendar.getTime();
	    return converToString(date,type);
	  }
	  
	  /**
	   * 获得Oracle格式的字符串时间：YYYY-MM-DD HH24:MI:SS
	   * 
	   * @param date
	   * @return
	   */
	  public static String getOracleFormatDateStr(java.util.Date date){
	    return converToString(date,"YYYY-MM-DD HH24:MI:SS");
	  }
	  
	  /**
	   * 两种时间格式的转换:java.util.Date to java.sql.Date
	   * 
	   * @param java.util.Date date
	   * @return java.sql.Date
	   */
	  public static java.sql.Date converUtilTOSql(java.util.Date date){
	    java.sql.Date d=java.sql.Date.valueOf(date.toString());
	    return d;
	  }
	  
	  /**
	   * 两种时间格式的转换：java.sql.Date to java.util.Date
	   * 
	   * @param java.sql.Date date
	   * @return java.util.Date
	   */
	  public static java.util.Date converSqlTOUtil(java.sql.Date date){
	    return (java.util.Date)date;
	  }
	  
	  /**
	   * (原方法名getFullDate) 转换日期格式成字符串，根据传递的时间获得指定格式的时间信息。 转换的格式类型可以自行定义（其中年yyyy月MM日dd时HH分mm秒ss）：<br>
	   * (1)转换成yyyy-MM-dd HH:mm:ss格式：2005-5-25 10:50:24<br>
	   * (2)转换成yyyy年MM月dd日 HH:mm:ss格式：2005年5月25日 10:50:24<br>
	   * (3)转换成yyyyMMddHHmmss格式：20061024152356<br>
	   * (4)转换成yyyy-MM-dd格式：2006-12-11<br>
	   * (5)转换成yyyyMMdd格式：20061211<br>
	   * (5)转换成yyyyMM格式：200612<br>
	   * (6)转换类型null时，格式默认为yyyy-MM-dd HH:mm:ss
	   * 
	   * @param Date
	   * @param type 格式类型
	   * @return
	   */
	  public static String converToString(java.util.Date dt,String type){
	    String returnStr=null;
	    if(dt == null)
	      return null;
	    else{
	      // YYYY-MM-DD HH24:MI:SS
	      type=StringReplace("YYYY","yyyy",type);
	      type=StringReplace("DD","dd",type);
	      type=StringReplace("SS","ss",type);
	      type=StringReplace("hh24","HH",type);
	      type=StringReplace("HH24","HH",type);
	      type=StringReplace("mm","MM",type);
	      type=StringReplace("mi","mm",type);
	    }
	    if(type == null || type.trim().equals("")){
	      returnStr=DateFormat.getDateTimeInstance().format(dt);
	    }else{
	      SimpleDateFormat f=new SimpleDateFormat(type);
	      returnStr=f.format(dt);
	    }
	    return returnStr;
	  }
	  
	  /**
	   * 根据日期获得星期
	   * 
	   * @param date
	   * @return
	   */
	  public static String getWeekOfDate(Date date){
	    String[] weekDaysName={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	    String[] weekDaysCode={"0","1","2","3","4","5","6"};
	    Calendar calendar=Calendar.getInstance();
	    calendar.setTime(date);
	    int intWeek=calendar.get(Calendar.DAY_OF_WEEK) - 1;
	    return weekDaysName[intWeek];
	  }
	  
	  /**
	   * 转换日期格式，如果转换类型不存在则返回原来字符串，如果原始字符串为null则返回null。<br>
	   * (1)从yyyyMMddHHmmss格式转为yyyy-MM-dd HH:mm:ss格式<br>
	   * (2)从yyyy-MM-dd格式转为yyyyMMdd格式，其中原始格式中间分隔符任意<br>
	   * (3)从yyyy-MM-dd格式转为yyyyMM格式<br>
	   * 
	   * @param s
	   * @param type
	   * @exception AppException
	   * @return
	   */
	  public static String converToString(String s,int type) throws Exception{
	    String strRet=null;
	    if(s == null || s.equals(""))
	      return null;
	    switch(type){
	      case 1:
	        try{
	          strRet=s.substring(0,4) + "-" + s.substring(4,6) + "-" + s.substring(6,8) + " " + s.substring(8,10) + ":" + s.substring(10,12) + ":" + s.substring(12,14);
	        }catch(Exception e){
	          throw new Exception("不合法的时间字符串(正确格式应该为yyyyMMddHHmmss)");
	        }
	        break;
	      case 2:
	        try{
	          strRet=s.substring(0,4) + s.substring(5,7) + s.substring(8,10);
	        }catch(Exception e){
	          throw new Exception("不合法的时间字符串(正确格式应该为yyyy-MM-dd)");
	        }
	        break;
	      case 3:
	        int yyyy,
	        MM;
	        try{
	          Date oneDay=stringToDate(s,"",true);
	          Calendar ca=Calendar.getInstance();
	          ca.clear();
	          ca.setTime(oneDay);
	          yyyy=ca.get(Calendar.YEAR);
	          MM=ca.get(Calendar.MONTH) + 1;
	        }catch(Exception e){
	          throw new Exception("不合法的时间字符串(正确格式应该为yyyy-MM-dd)");
	        }
	        if(yyyy < 1000 || yyyy > 9999)
	          throw new Exception("错误的年份");
	        if(MM < 10){
	          strRet="0" + MM;
	        }else{
	          strRet="" + MM;
	        }
	        strRet=yyyy + strRet;
	        break;
	      default:
	        strRet=s;
	    }
	    return strRet;
	  }
	  
	  /**
	   * 转换字符串时间类型为指定格式的字符串时间。 原始时间字符串只能是下面四种中的一个： (1)从yyyyMMddHHmmss格式 (2)从yyyyMMdd格式 (3)从yyyy-MM-dd HH:mm:ss格式 (4)从yyyy-MM-dd格式。 返回的字符串类型可以自行定义，其中：yyyy-年、MM-月、dd-日、HH-时、mm-分、ss-秒
	   * 
	   * @param dt
	   * @param type
	   * @return
	   * @throws AppException
	   */
	  public static String converToString(String dt,String type){
	    Date oneDay=converToDate(dt);
	    return converToString(oneDay,type);
	    
	  }
	  
	  /**
	   * 转换字符串格式日期为java.sql.Date日期，如果原始字符串为null则返回null。 自动支持从以下格式字符串转换：<br>
	   * (1)从yyyyMMddHHmmss格式 (2)从yyyyMMdd格式 (3)从yyyy-MM-dd HH:mm:ss格式 (4)从yyyy-MM-dd格式 (5)从yyyy-MM格式(自动改为1日)
	   * 
	   * @param s
	   * @param type
	   * @exception AppException
	   * @return java.sql.Date
	   */
	  public static java.sql.Date converToDate(String s){
	    if(s == null || s.equals(""))
	      return null;
	    if(s.length() == 6){
	      s=s + "01";
	    }
	    String yyyy,MM,dd;
	    String HH="00",mm="00",ss="00";
	    int len=s.length();
	    if(len == 8 && numberVerify(s)){
	      yyyy=s.substring(0,4);
	      MM=s.substring(4,6);
	      dd=s.substring(6,8);
	    }else if(len == 14){
	      yyyy=s.substring(0,4);
	      MM=s.substring(4,6);
	      dd=s.substring(6,8);
	      HH=s.substring(8,10);
	      mm=s.substring(10,12);
	      ss=s.substring(12,14);
	    }else{
	      String val="-";
	      if(s.indexOf("/") >= 0){
	        val="/";
	      }
	      String temp;
	      yyyy=s.substring(0,s.indexOf(val));
	      MM=s.substring(s.indexOf(val) + 1,s.lastIndexOf(val));
	      temp=s.substring(s.lastIndexOf(val) + 1,s.length());
	      if(temp.indexOf(" ") > 0){
	        // 有时分秒
	        dd=temp.substring(0,temp.indexOf(" "));
	        temp=temp.substring(temp.indexOf(" ") + 1,temp.length());
	        HH=temp.substring(0,temp.indexOf(":"));
	        mm=temp.substring(temp.indexOf(":") + 1,temp.lastIndexOf(":"));
	        ss=temp.substring(temp.lastIndexOf(":") + 1,temp.length());
	        if(ss.indexOf(".") > 0){
	          ss=ss.substring(0,ss.lastIndexOf("."));
	        }
	      }else{
	        dd=temp;
	      }
	    }
	    
	    try{
	      return getDate(Integer.parseInt(yyyy),Integer.parseInt(MM),Integer.parseInt(dd),Integer.parseInt(HH),Integer.parseInt(mm),Integer.parseInt(ss));
	    }catch(NumberFormatException e){
	      return null;
	    }
	  }
	  
	  /**
	   * 得到将date增加指定年数后的date
	   * 
	   * @param date
	   * @param intBetween
	   * @return date加上intBetween年数后的日期
	   */
	  public static java.sql.Date getStepYear(Date date,int intBetween){
	    Calendar calo=Calendar.getInstance();
	    calo.setTime(date);
	    calo.add(Calendar.YEAR,intBetween);
	    return new java.sql.Date(calo.getTime().getTime());
	  }
	  
	  /**
	   * 获取输入时间后n个月的时间
	   * 
	   * @param date
	   * @param intBetween
	   * @return date加上intBetween月数后的日期
	   */
	  public static java.sql.Date getStepMonth(Date date,int intBetween){
	    Calendar calo=Calendar.getInstance();
	    calo.setTime(date);
	    calo.add(Calendar.MONTH,intBetween);
	    return new java.sql.Date(calo.getTime().getTime());
	  }
	  
	  /**
	   * 获取输入时间后n个月的yyyyMM(原来的此方法叫做getYearAndMonth)。 月数为正表示向以后，负表示向以前，时间字符串格式可以为：<br>
	   * (1)从yyyyMMddHHmmss格式<br>
	   * (2)从yyyyMMdd格式<br>
	   * (3)从yyyy-MM-dd HH:mm:ss格式<br>
	   * (4)从yyyy-MM-dd格式<br>
	   * (5)从yyyyMM格式<br>
	   * 
	   * @param inputDate
	   * @param step
	   * @return
	   */
	  public static String getStepMonth(String dateStr,int step){
	    if(dateStr != null && dateStr.length() == 6){
	      dateStr=dateStr + "01";
	    }
	    Date oneDay=converToDate(dateStr);
	    Calendar ca=Calendar.getInstance();
	    ca.clear();
	    ca.setTime(oneDay);
	    
	    ca.set(Calendar.MONTH,ca.get(Calendar.MONTH) + step);
	    
	    int yyyy=ca.get(Calendar.YEAR);
	    int MM=ca.get(Calendar.MONTH) + 1;
	    String month="" + MM;
	    
	    if(MM < 10){
	      month="0" + MM;
	    }
	    return "" + yyyy + month;
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
	   * 根据所给的起始时间,间隔天数来计算终止时间，并转换成指定格式字符串
	   * 
	   * @param date
	   * @param step
	   * @param type
	   * @return
	   */
	  public static String getStepDay(java.util.Date date,int step,String type){
	    return converToString(getStepDay(date,step),type);
	  }
	  
	  /**
	   * 根据所给的起始,终止时间来计算间隔天数 间隔时间只记录整数部分，例如1号2点到3号23点，间隔2天21小时也仅记入2天
	   * 
	   * @param startDate
	   * @param endDate
	   * @return 间隔天数
	   */
	  public static int getIntervalDay(java.util.Date startDate,java.util.Date endDate){
	    if(startDate == null || endDate == null)
	      return 0;
	    long startdate=startDate.getTime();
	    long enddate=endDate.getTime();
	    long interval=enddate - startdate;
	    double day=interval / (1000 * 60 * 60 * 24);
	    int intervalday=(int)(day);
	    if(day > intervalday){
	      intervalday++;
	    }
	    return intervalday;
	    
	  }
	  
	  /**
	   * 根据所给的起始,终止时间来计算间隔秒数
	   * 
	   * @param startDate
	   * @param endDate
	   * @return
	   */
	  public static int getIntervalSecond(java.util.Date startDate,java.util.Date endDate){
	    if(startDate == null || endDate == null)
	      return 0;
	    long startdate=startDate.getTime();
	    long enddate=endDate.getTime();
	    long interval=enddate - startdate;
	    double second=interval / 1000;
	    int intervalSecond=(int)second;
	    if(second > intervalSecond){
	      intervalSecond++;
	    }
	    return intervalSecond;
	  }
	  
	  /**
	   * 根据所给的起始,终止时间来计算间隔月数。 如果提供的开始时间和结束时间不合法，则返回0
	   * 
	   * @param startDate YYYYMM
	   * @param startDate YYYYMM
	   * @return 间隔月数
	   */
	  public static int getIntervalMonth(String s,String s1){
	    try{
	      String startDate=converToString(s,"yyyyMM");
	      String endDate=converToString(s1,"yyyyMM");
	      int i=Integer.parseInt(startDate.substring(0,4)) * 12 + Integer.parseInt(startDate.substring(4,6));
	      int j=Integer.parseInt(endDate.substring(0,4)) * 12 + Integer.parseInt(endDate.substring(4,6));
	      int intervalMonth=j - i;
	      return intervalMonth;
	    }catch(Exception e){
	      return 0;
	    }
	  }
	  
	  /**
	   * 根据所给的起始,终止时间来计算间隔月数
	   * 
	   * @param Date
	   * @param Date
	   * @return
	   */
	  public static int getIntervalMonth(java.util.Date startDate,java.util.Date endDate){
	    SimpleDateFormat f=new SimpleDateFormat("yyyyMM");
	    String str=f.format(startDate);
	    String end=f.format(endDate);
	    return getIntervalMonth(str,end);
	  }
	  
	  /**
	   * 获取当前的年份
	   * 
	   * @return
	   */
	  public static int getYear(){
	    Calendar calendar=Calendar.getInstance();
	    int yyyy=calendar.get(1);
	    return yyyy;
	  }
	  
	  /**
	   * 得到日期字符串的年份部分，必须是4位数字，如果不是合法年份，则会抛出错误
	   * 
	   * @param dateStr
	   * @return
	   */
	  public static int getYear(String dateStr){
	    Date oneDay=converToDate(dateStr);
	    Calendar ca=Calendar.getInstance();
	    ca.clear();
	    ca.setTime(oneDay);
	    int yyyy=ca.get(Calendar.YEAR);
	    return yyyy;
	  }
	  
	  /**
	   * 返回指定时间当月的最后一天，格式： (1)从yyyyMMdd格式<br>
	   * (2)从yyyy-MM-dd格式<br>
	   * (3)从yyyy年MM月dd日格式<br>
	   * 
	   * @param ss
	   * @return
	   */
	  public static String getLastDay(String ss,String type){
	    if(type == null || type.equals("")){
	      type="1";
	    }
	    String s=null;
	    if(ss.length() != 6){
	      s=converToString(ss,"yyyyMM");
	    }else{
	      s=ss;
	    }
	    int i=Integer.parseInt(s.substring(0,4));
	    int j=Integer.parseInt(s.substring(4,6));
	    String s1="";
	    if(j == 2){
	      if(i % 4 == 0 && i % 100 != 0 || i % 400 == 0){
	        s1="29";
	      }else{
	        s1="28";
	      }
	    }else if(j == 4 || j == 6 || j == 9 || j == 11){
	      s1="30";
	    }else{
	      s1="31";
	    }
	    String mm="";
	    if(j < 10){
	      mm="0" + String.valueOf(j);
	    }else{
	      mm=String.valueOf(j);
	    }
	    if(type.equals("1"))
	      return String.valueOf(i) + mm + s1;
	    else if(type.equals("2"))
	      return String.valueOf(i) + "-" + mm + "-" + s1;
	    else if(type.equals("3"))
	      return String.valueOf(i) + "年" + String.valueOf(j) + "月" + s1 + "日";
	    return String.valueOf(i) + "年" + String.valueOf(j) + "月" + s1 + "日";
	  }
	  
	  /**
	   * 返回指定时间当月的第一天，格式： (1)从yyyyMMdd格式<br>
	   * (2)从yyyy-MM-dd格式<br>
	   * (3)从yyyy年MM月dd日格式<br>
	   * 
	   * @param ss
	   * @return
	   */
	  public static String getFirstDay(String ss,String type){
	    if(type == null || type.equals("")){
	      type="1";
	    }
	    String s=null;
	    if(ss.length() != 6){
	      s=converToString(ss,"yyyyMM");
	    }else{
	      s=ss;
	    }
	    int i=Integer.parseInt(s.substring(0,4));
	    int j=Integer.parseInt(s.substring(4,6));
	    String s1="01";
	    String mm="";
	    if(j < 10){
	      mm="0" + String.valueOf(j);
	    }else{
	      mm=String.valueOf(j);
	    }
	    
	    if(type.equals("1"))
	      return String.valueOf(i) + mm + s1;
	    else if(type.equals("2"))
	      return String.valueOf(i) + "-" + mm + "-" + s1;
	    else if(type.equals("3"))
	      return String.valueOf(i) + "年" + String.valueOf(j) + "月" + "1" + "日";
	    return String.valueOf(i) + "年" + String.valueOf(j) + "月" + "01" + "日";
	  }
	  
	  /**
	   * 返回指定时间当月的最后一天，格式： (1)从yyyyMMdd格式<br>
	   * (2)从yyyy-MM-dd格式<br>
	   * (5)从yyyy年MM月dd日格式<br>
	   * 
	   * @param sql.date dt
	   * @return
	   */
	  public static String getLastDay(java.sql.Date dt,String type){
	    String s=converToString(dt,"yyyyMM");
	    return getLastDay(s,type);
	  }
	  
	  /**
	   * 返回指定时间当月的第一天，格式： (1)从yyyyMMdd格式<br>
	   * (2)从yyyy-MM-dd格式<br>
	   * (5)从yyyy年MM月dd日格式<br>
	   * 
	   * @param sql.date dt
	   * @return
	   */
	  public static String getFirstDay(java.sql.Date dt,String type){
	    String s=converToString(dt,"yyyyMM");
	    return getFirstDay(s,type);
	  }
	  
	  /**
	   * 返回当前时间的下个月第一天，格式yyyyMMdd
	   * 
	   * @return
	   */
	  public static String getFirstDayOfNextMonth(){
	    String s=converToString(getDate(),"yyyyMMdd");
	    return getStepMonth(s.substring(0,6),1) + "01";
	  }
	  
	  /**
	   * 获取指定时间的上个年月，格式yyyyMM
	   * 
	   * @param dt
	   * @return
	   */
	  public static String descreaseYearMonth(String dt){
	    String s=converToString(dt,"yyyyMMdd");
	    int i=(new Integer(s.substring(0,4))).intValue();
	    int j=(new Integer(s.substring(4,6))).intValue();
	    if(--j >= 10)
	      return s.substring(0,4) + (new Integer(j)).toString();
	    if(j > 0 && j < 10)
	      return s.substring(0,4) + "0" + (new Integer(j)).toString();
	    else
	      return (new Integer(i - 1)).toString() + (new Integer(j + 12)).toString();
	  }
	  
	  /**
	   * 计算从出生时间到指定时间的年龄
	   * 
	   * @param birthday 出生时间
	   * @param endDate 计算的终止时间
	   * @return int
	   */
	  public static int getAge(Date birthday,Date endDate){
	    int month=getIntervalMonth(birthday,endDate);
	    if(month <= 0)
	      return 0;
	    else
	      return month / 12;
	  }
	  
	  /**
	   * 根据身份证号码获得年龄
	   * 
	   * @param s
	   * @return
	   * @throws Exception
	   */
	  public static int getAge(String id) throws Exception{
	    int i=-1;
	    int j=id.length();
	    String s1="";
	    if(j == 15){
	      s1=id.substring(6,8);
	      s1="19" + s1;
	    }else if(j == 18){
	      s1=id.substring(6,10);
	    }else
	      throw new Exception("错误的身份证号");
	    int k=Calendar.getInstance().get(1);
	    i=k - (new Integer(s1)).intValue();
	    return i;
	  }
	  
	  /**
	   * 以今天为生日，根据年龄获取出生日期
	   * 
	   * @param age
	   * @return
	   */
	  public static Date getBirtday(int age){
	    Calendar calendar=Calendar.getInstance(Locale.CHINESE);
	    long l=calendar.getTimeInMillis();
	    calendar.set(calendar.get(1) - age,calendar.get(2),calendar.get(5));
	    return new Date(calendar.getTimeInMillis());
	  }
	  
	  /**
	   * 根据身份证号码获取出生日期(正确的身份证返回出生日期，错误的返回当前数据库的日期)
	   * 
	   * @param id
	   * @return
	   * @exception Exception
	   */
	  public static String getBirtday(String id) throws Exception{
	    String birthday="";
	    int idLength=id.length();
	    String yy="";
	    int YY=0;
	    String mm="";
	    int MM=0;
	    String dd="";
	    int DD=0;
	    boolean leapYear=false;
	    String today=converToString(new Date(),"yyyy-mm-dd");
	    
	    if(idLength == 15){
	      yy="19" + id.substring(6,8);
	      mm=id.substring(8,10);
	      dd=id.substring(10,12);
	    }else if(idLength == 18){
	      yy=id.substring(6,10);
	      mm=id.substring(10,12);
	      dd=id.substring(12,14);
	    }else
	      return(converToString(new Date(),"yyyy-mm-dd"));
	    YY=(new Integer(yy)).intValue();
	    MM=(new Integer(mm)).intValue();
	    DD=(new Integer(dd)).intValue();
	    if(YY < 1900 || YY > 2200)
	      return(today);
	    
	    if(((YY % 4) != 0) && ((YY % 100) != 0)){ // 判断是否为闰年
	      leapYear=false;
	    }else{
	      leapYear=true;
	    }
	    if(MM == 2){
	      if(leapYear){
	        if(DD < 1 || DD > 29)
	          return(today);
	      }else{
	        if(DD < 1 || DD > 28)
	          return(today);
	      }
	    }
	    if((MM == 1) || (MM == 3) || (MM == 5) || (MM == 7) || (MM == 8) || (MM == 10) || (MM == 12)){
	      if(DD < 1 || DD > 31)
	        return(today);
	    }
	    if((MM == 4) || (MM == 6) || (MM == 9) || (MM == 11)){
	      if(DD < 1 || DD > 30)
	        return(today);
	    }
	    birthday=yy + "-" + mm + "-" + dd;
	    return birthday;
	  }
	  
	  /**
	   * 根据身份证号码获取性别(返回值：1－男，2－女，空为身份证号码错误)
	   * 
	   * @param id
	   * @return
	   * @exception Exception
	   */
	  public static String getGender(String iDCard){
	    int gender=3;
	    System.out.print(iDCard);
	    if(iDCard.length() == 15){
	      gender=(new Integer(iDCard.substring(14,15))).intValue() % 2;
	    }else if(iDCard.length() == 18){
	      int number17=(new Integer(iDCard.substring(16,17))).intValue();
	      gender=number17 % 2;
	    }
	    if(gender == 1)
	      return "1";
	    else if(gender == 0)
	      return "2";
	    else
	      return "";
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
	   * 检查日期字符串是否正确。格式：yyyy-MM-dd
	   * 
	   * @param dateString
	   * @return
	   */
	  public static boolean checkDateString(String dateString){
	    boolean check=false;
	    try{
	      Date oneDay=stringToDate(dateString,"",true);
	      Calendar ca=Calendar.getInstance();
	      ca.clear();
	      ca.setTime(oneDay);
	      int yyyy=ca.get(Calendar.YEAR);
	      if(yyyy >= 1000 && yyyy <= 9999){
	        check=true;
	      }
	    }catch(Exception e){
	      check=false;
	    }
	    return check;
	  }
	  
	  /**
	   * 比较两个时间的年月，如果第一个比第二个晚，则返回true，否则返回false
	   * 
	   * @param dt
	   * @param s1
	   * @return
	   */
	  public static boolean yearMonthGreater(String dt1,String dt2){
	    return yearMonthGreater(converToDate(dt1),converToDate(dt2));
	  }
	  
	  /**
	   * 比较两个时间的年月，如果第一个比第二个晚，则返回true，否则返回false
	   * 
	   * @param dt1
	   * @param dt2
	   * @return
	   */
	  public static boolean yearMonthGreater(Date dt1,java.util.Date dt2){
	    String s=converToString(dt1,"yyyyMM");
	    String s1=converToString(dt2,"yyyyMM");
	    String s2=s.substring(0,4);
	    String s3=s1.substring(0,4);
	    String s4=s.substring(4,6);
	    String s5=s1.substring(4,6);
	    if(Integer.parseInt(s2) > Integer.parseInt(s3))
	      return true;
	    if(Integer.parseInt(s2) == Integer.parseInt(s3))
	      return Integer.parseInt(s4) > Integer.parseInt(s5);
	    else
	      return false;
	  }
	  
	  /**
	   * 将月数量转换成x年x个月格式显示。 如month2YearMonth("16")="1年4个月"
	   * 
	   * @param s
	   * @return
	   */
	  public static String month2YearMonth(String s){
	    String s1="";
	    if(s == null || "0".equals(s) || "".equals(s.trim()))
	      return "0月";
	    int i=Integer.parseInt(s);
	    int j=i / 12;
	    int k=i % 12;
	    if(j > 0){
	      s1=j + "年";
	    }
	    if(k > 0){
	      s1=s1 + k + "个月";
	    }
	    return s1;
	  }
	  
	  /**
	   * 以指定字符串to替换原始字符串source中的from字符串 例如用单引号替换双引号：StringReplace("\"","\'",oldString)
	   * 
	   * @param from
	   * @param to
	   * @param source
	   * @return
	   */
	  public static String StringReplace(String from,String to,String source){
	    if(source == null)
	      return null;
	    int i=-1;// shenyg修改假如要替换的位置正好是0这样不被过滤的 所以修改当前方法
	    i=source.indexOf(from);
	    while(i != -1){
	      source=source.substring(0,i).concat(to).concat(source.substring(i + from.length()));
	      i=source.indexOf(from);
	    }
	    return(source);
	  }
	  
	  /**
	   * 判断传入的字符串是否是１０进制数字 依次取出字符串的每一位,判断取出的字符的ascii码是否在'0'和'9'对应ascii之间
	   * 
	   * @param name
	   * @return boolean
	   * @roseuid 3E486D4E009B
	   */
	  public static boolean numberVerify(String name){
	    boolean isNumberChar=true;
	    int i=0;
	    char x;
	    // while (isNumberChar || i+1 >= name.length()) {沈云刚修改 如果当前传入字符串长度小于等于i+1的话 那回获取一个空字符
	    // 例如当前传入参数的长度为8 循环的结果i为7 i+1一定大于等于8
	    while(isNumberChar && i < name.length()){
	      x=name.charAt(i);
	      if(x < '0' || x > '9'){
	        isNumberChar=false;
	      }
	      i++;
	    }
	    return isNumberChar;
	  }
	  
	  /**
	   * 将yyyy-MM-dd格式的时间转换成java.sql.Date对象 For Example: TypeCast.stringToDate("1995-1-5","parameter name",false);
	   * 
	   * @param strDate A String whose should be parsed.
	   * @param paraName
	   * @param isCanNull if this parameter is false,strDate must be not null.
	   * @return java.sql.Date
	   * @throws ManageInputException
	   */
	  public static Date stringToDate(String strDate,String paraName,boolean isCanNull) throws Exception{
	    strDate=strDate.trim();
	    if(strDate == null || strDate.equals("")){
	      if(isCanNull)
	        return null;
	      else
	        throw new Exception("|  输入的参数：" + paraName + "为空，请 输入 |");
	    }
	    Date targetDate=null;
	    try{
	      targetDate=TimeUtils_old.converToDate(strDate);
	    }catch(Exception e){
	      throw new Exception("|  输入的参数：" + paraName + "无法转换成时间类型 |");
	    }
	    return targetDate;
	  }
	  
	  public static Long getEndTime(Date date,int step){
			if(date==null)
				return new Date().getTime();
			 return TimeUtils_old.getStepDay(date, step).getTime();
	 }
	  
	  
	  public static Date getDateValue(Object obj){
		  if(obj==null)
			  return null;
		  String str  = obj.toString();
		  try {
			return DateUtils.parseDate(str.trim(),PARSE_FORMAT_ARR);
		} catch (ParseException e) {
			throw new RuntimeException("wrong date format");
		}
	  }
	  
	  /* 以下内容都可以找到替换方法 */
	  /*
	   * public static java.util.Date increaseYear(java.util.Date date, int step) { return getStepYear(date,step); } public static java.util.Date increaseMonth(java.util.Date date, int step) { return getStepMonth(date,step); } public static String increaseYearMonth(String s)throws AppException { return
	   * getStepMonth(s,1); } public static java.sql.Date increaseDay(java.sql.Date date, int step) { return getStepDay(date, step); } public static String getYearAndMonth(String s) { return converToString(s,"yyyy年MM月"); } public static String getChineseDate(java.util.Date date) { return
	   * converToString(date,"yyyy年MM月dd日"); } public static String getStrDate(Date date){ return converToString(date,"yyyyMMdd"); } public static String getAddIssue(String str, int how) { return getStepMonth(str, how); } public static int amoungMonths(String startDate, String endDate) { return
	   * getIntervalMonth(startDate,endDate); } public static String getCurrentDate_String(String s) { return getSystemCurrentTime(s); } public static int convertDateToYear(java.util.Date date) { return Integer.parseInt(converToString(date,"yyyy")); } public static String
	   * convertDateToYearMonth(java.util.Date date) { return converToString(date,"yyyyMM"); } public static String convertDateToYearMonthDay(java.util.Date date) { return converToString(date,"yyyyMMdd"); } public static java.util.Date getCurrentDate() { return getSystemCurrentTime(); } public static
	   * String getYearAndMonth(String dateStr, int n){ return getStepMonth(dateStr,n); } public static String getYearMonthByMonth(String s) { return converToString(s,"yyyy年MM月"); } public static int getCurrentYear(){ return getYear(); } public static String getCurrentYearMonth() { return
	   * getSystemCurrentTime("yyyyMM"); } public static boolean yearMonthGreatEqual(String s, String s1) { return yearMonthGreater(s,s1); } public static String getStrHaveAcross(String s) { return converToString(s,"yyyy-MM-dd"); } public static Date getDateByAge(int i) { return getBirtday(i); } public
	   * static String dateToString(java.util.Date date, String s) { return converToString(date,s); } public static String getDate(java.util.Date date, String s) { return converToString(date,s); } public static java.util.Date stringToDate(String s, String s1) { return converToDate(s); } public static
	   * String getMonthBetween(String s, String s1) { return String.valueOf(getIntervalMonth(s,s1)); } public static java.util.Date getDateBetween(java.util.Date date, int i) { return getStepDay(date,i); } public static String getDateBetween_String(java.util.Date date, int i,String type) { return
	   * getStepDay(date, i, type); } public static int daysBetweenDates(java.util.Date date, java.util.Date date1) { return getIntervalDay(date1,date); } public static int calBetweenTwoMonth(String s, String s1) { return getIntervalMonth(s1,s); }
	   */
	  // public static String converToString(java.util.Date dt, int type) {
	  // }
	  // public static String getFullDate(java.sql.Date dt) {
	  // return converToString(dt, null);
	  // }  
	public static void main(String args[]){
		System.out.println(TimeUtils.getDayFirstTime(new Date()) );
	}
}
