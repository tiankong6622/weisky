package org.itboys.commons.utils.random;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import org.itboys.commons.utils.common.CommonUtils;


/**
 * 随机数相关utils
 * @author ChenJunHui
 */
public abstract class RandomUtils {
	
	public static final Random rd = new Random();
	/**
	 * 难认识的字符
	 */
	public static final String[] nanrenzifu = new String[]{"0","O","o","0","i","l","1"};
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	/**
	 * 生成 num 个 长度为 length的随机数字 并用 splite符号隔开
	 * @param length
	 * @param splite
	 * @param num
	 * @return
	 */
	public static String getIntRandom(int length,String splite,int num){
		if(num<=0 || length<=0 || splite==null)
			throw new IllegalArgumentException("wrong arguments");
		String str = StringUtils.EMPTY;
		for(int i=0;i<num;i++){
			str = splite+buildRandom(length)+str;
		}
		str = str.substring(splite.length());
		return str;
		
	}

	/**
	 * 获得length长度的随机数
	 * @param length
	 * @return
	 */
	public static String getIntRandom(int length){
		if(length<=0){
			throw new IllegalArgumentException("must be gt 0");
		}
		String str = StringUtils.EMPTY;
		for(int i=0;i<length;i++){
			str = str+Math.abs(rd.nextInt()%10);
		}
		return str;
	}
	
	/**
	 * 获取length位数字和字符组合的随机数
	 * @param length
	 * @return
	 */
	public static String getRandomStr(int length){
	 StringBuilder sb = new StringBuilder();
	 for(int i=0;i <length;i++){ 
		 sb.append(Integer.toString(rd.nextInt(36),36)); 
	 }
	 return sb.toString();
	}

	
	/**
	 * 获取length位数字和字符组合的随机数 去掉 O0l1等难任的字符
	 * @param length
	 * @return
	 */
	public static String getClearRandomStr(int length){
	 StringBuilder sb = new StringBuilder();
	 for(int i=0;i <length;i++){ 
		 sb.append(getClearRandomChar()); 
	 }
	 return sb.toString();
	}
	
	public static String getClearRandomChar(){
		 String s = Integer.toString(rd.nextInt(36),36);
		 if(CommonUtils.isIn(s, nanrenzifu)){
			 return getClearRandomChar();
		 }
		 return s;
	}
	
	public static void main(String[] args){
		System.out.println(RandomUtils.getIntRandom(3));
		System.out.println(RandomUtils.getIntRandom(4,"-",3));
		System.out.println(RandomUtils.getRandomStr(33));
	}
}
