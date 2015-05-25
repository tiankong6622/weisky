package org.itboys.framework.csv;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * csv导出相关
 * @author Chenjunhui
 *
 */
public class CsvUtils {

	private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);
	
	/**
	 * 导出csv File 记得3个数组长度必须一样
	 * 
	 * @param titles 标题
	 * @param fields csv 数据对象对应的POJO的反射属性或map里的key
	 * @param list 导出的对象集合列表
	 * @param out
	 *            输出流
	 */
	public static <T> void exportCsvFile(String[] titles, String[] fields,List<T> list, OutputStream out) {
		CSVWriter writer = null;
		OutputStreamWriter os = null;
		int i = 0;
		int j = 0; 
		try {
			os = new OutputStreamWriter(out);
			writer = new CSVWriter(os, ',');
			writer.writeNext(titles);
			if(list!=null && !list.isEmpty()){
				List<String[]> datas = Lists.newArrayListWithCapacity(list.size());
				int size = list.size();
				while (i < size) {
					T t = list.get(i);
					if(t!=null){
						String[] data = new String[titles.length];
						while (j < fields.length) {
							String field = fields[j];
							Object value =  PropertyUtils.getProperty(t, field);
							String exportValue = StringUtils.EMPTY;
							if (value != null) {
								if (value instanceof Date) {
									exportValue = DateFormatUtils.format((Date) value, "yyyy-MM-dd HH:mm:ss");
								} else {
									exportValue = value.toString();
								}
							}
							data[j]=exportValue;
							j++;
						}
						datas.add(data);
						j=0;
					}
					i++;
				}
				writer.writeAll(datas);
			}
		} catch (Exception e) {
			logger.error("write csv file error,第 "+i+"行,第"+j+"列失败", e);
			throw new RuntimeException("write csv file error,第 "+i+"行,第"+j+"列失败",e);
		} finally {
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(writer);
		}
	}
	
	/**
	 * 导出csv File 记得3个数组长度必须一样
	 * 
	 * @param titles 标题
	 * @param fields csv 数据对象对应的POJO的反射属性或map里的key
	 * @param list 导出的对象集合列表
	 * @param out
	 *            输出流
	 */
	public static <T> void exportCsvFile(String[] titles, String[] fields,T[] arr, OutputStream out) {
		CSVWriter writer = null;
		OutputStreamWriter os = null;
		int i = 0;
		int j = 0; 
		try {
			os = new OutputStreamWriter(out);
			writer = new CSVWriter(os, ',');
			writer.writeNext(titles);
			if(arr!=null && arr.length>0){
				List<String[]> datas = Lists.newArrayListWithCapacity(arr.length);
				int size = arr.length;
				while (i < size) {
					T t = arr[i];
					if(t!=null){
						String[] data = new String[titles.length];
						while (j < fields.length) {
							String field = fields[j];
							Object value =  PropertyUtils.getProperty(t, field);
							String exportValue = StringUtils.EMPTY;
							if (value != null) {
								if (value instanceof Date) {
									exportValue = DateFormatUtils.format((Date) value, "yyyy-MM-dd HH:mm:ss");
								} else {
									exportValue = value.toString();
								}
							}
							data[j]=exportValue;
							j++;
						}
						datas.add(data);
						j=0;
					}
					i++;
				}
				writer.writeAll(datas);
			}
		} catch (Exception e) {
			logger.error("write csv file error,第 "+i+"行,第"+j+"列失败", e);
			throw new RuntimeException("write csv file error,第 "+i+"行,第"+j+"列失败",e);
		} finally {
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(writer);
		}
	}
}
