package com.hz.yisheng.commondata.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.javafans.common.utils.io.FileUtils;

/**
 * 存文件夹+文件格式的优惠券
 * @author ChenJunhui
 */
public class CouponInFilesUtils {

	public static final String SPLIT="A";//活动ID和文件下标
	public static final String SPLIT2="F";//文件下标和号码劈开字符串
	public static final String ZERO="0";
	public static final int SIZE=1000;//多少个号存一个文件
	
	public static void main(String args[]){
		CouponInFilesUtils.doGenCoupons("D:/aa", 1L, 9900);
	}
	
	/**
	 * 生成优惠券 一个活动建一个文件夹 每1万个优惠券一个文件
	 * @param baseDir 再哪个文件夹下生成
	 * @param projectId
	 * @param activeId
	 * @param num 生成多少张券
	 */
	public static  void doGenCoupons(String baseDir,Long activeId,int num){
		String suffix = activeId+SPLIT;
		File baseFile = new File(baseDir+FileUtils.FILE_SEPARATOR+activeId);
		if(!baseFile.exists()){
			baseFile.mkdir();
		}
		int fileCount = num/SIZE;
		if(num%SIZE>0){
			fileCount++;
		}
		for(int i=0;i<fileCount;i++){
			File f = new File(baseDir+FileUtils.FILE_SEPARATOR+activeId+FileUtils.FILE_SEPARATOR+i);
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			BufferedWriter bw = null;
			
			try {
				bw = new BufferedWriter(new FileWriter(f));
				int start = i*SIZE;
				int end = (i+1)*SIZE;
				end = end>num?num:end;
				for(int j=start+1;j<end;j++){
					String number = suffix+i+SPLIT2+digitToString(j,5);
					bw.write(number);
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				IOUtils.closeQuietly(bw);
			}
		}
	}
	
	/**
	 * 当 number位数小于length then前面补0
	 * @param number
	 * @param length
	 * @return
	 */
	public static String digitToString(int number,int length){
		String num = String.valueOf(number);
		if(num.length()<length){
			int len = length-num.length();
			for(int i=0;i<len;i++){
				num=ZERO+num;
			}
		}
		return num;
	}
}
