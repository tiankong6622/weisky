package org.itboys.framework.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.number.ToNumberUtils;
import org.itboys.commons.utils.reflection.Reflections;
import org.itboys.commons.utils.time.TimeUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Excel相关的操作
 * @author ChenJunhui
 */
public abstract class ExcelUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
	
	private static Map<String,List<CellParam>> excelReflectMap = Maps.newHashMap();
	
	static{
		initExcelReflact();
	}
	
	/**
	 * 组装excel反射信息 xml格式如下
	 * <?xml version="1.0" encoding="UTF-8"?>
		<beans>
		
			<bean class="org.javafans.excel.ExcelTestDO">
				<property field="aa" x="4" y="3"/>
				<property field="bb" x="5" y="3"/>
				<property field="id" x="9" y="3"/>
			</bean>
			
			<bean class="org.javafans.excel.ExcelTestDO2">
				<property field="reson" x="6" y="2"/>
				<property field="xxx" x="8" y="2"/>
			</bean>
		</beans>
	 */
	@SuppressWarnings("unchecked")
	private static void initExcelReflact(){
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		try{
			Resource resource = resourceLoader.getResource("excel-reflect.xml");
			if(resource==null){
				return;
			}
			SAXReader reader = new SAXReader();
			InputStream is = resource.getInputStream();
			Document document = reader.read(is);
			List<Element> elements = document.selectNodes("/beans/bean");
			if(elements!=null && !elements.isEmpty()){
				for(Element element:elements){
					String className = element.attributeValue("class");
					List<Element> children = element.elements("property");
					List<CellParam> cellParams = Lists.newArrayListWithCapacity(children.size());
					for(Element pro:children){
						CellParam cp = new CellParam(ToNumberUtils.getIntegerValue(pro.attributeValue("x")), 
								ToNumberUtils.getIntegerValue(pro.attributeValue("y")), pro.attributeValue("field"));
						cellParams.add(cp);
						excelReflectMap.put(className, cellParams);
					}
				}
				
			}
		}catch(Exception e){
			logger.error("initExcelReflact fail",e);
		}
	}
	
	/**
	 * 根据配置信息 将excel文件转成JAVA对象
	 * @param clazz
	 * @param excelFile
	 * @return
	 */
	public static <T>  T excelToObject(Class<T> clazz,InputStream is){
		String className = clazz.getName();
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			T obj = clazz.newInstance();
			List<CellParam> cellParams = excelReflectMap.get(className);
			if(cellParams==null){
				throw new RuntimeException("there is no config class for "+className);
			}
			for(CellParam cellParam:cellParams){
				HSSFRow row = sheet.getRow(cellParam.getX());
				HSSFCell cell = row.getCell(cellParam.getY());
				Object value = ExcelUtils.getCellValue(cell);
				excelFieldToBeanField(obj, value, cellParam.getField());
			}
			return obj;
		} catch (Exception ex) {
			throw new RuntimeException("excelToObject",ex);
		}finally{
			IOUtils.closeQuietly(is);
		}
	} 
	
	

	/**
	 * excel 转成对象列表
	 * @param excelFile excel文件
	 * @param clazz 要转成对象的类型
	 * @param fields 属性集合
	 * @param cols 要导入哪些列 列数第一列是从1开始
	 * @param rows 从第几行开始导入
	 * @return
	*/
	public static <T> List<T> excelToObjects(InputStream is,Class<T> clazz,String[] fields,int[] cols,int rows){
		if(is==null || fields==null || cols==null || (cols.length!=fields.length) || rows<=0){
			throw new RuntimeException("wrong params");
		}
		List<T> list = new ArrayList<T>();
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(is);
		} catch (Exception ex) {
			throw new RuntimeException("read excel file error",ex);
		}
		HSSFSheet sheet = workbook.getSheetAt(0);
		int i = 0;
		int j=0;
		try{
			int allRows = sheet.getLastRowNum();
			for(i=rows;i<=allRows;i++){
				HSSFRow row = sheet.getRow(i);
				T obj = clazz.newInstance();
				for(j=0;j<cols.length;j++){
					HSSFCell cell = row.getCell(cols[j]-1);
					Object value = ExcelUtils.getCellValue(cell);
					String field = fields[j];
					excelFieldToBeanField(obj, value, field);
				}
				list.add(obj);
			}
		}catch(Exception e){
			throw new RuntimeException("导入第"+i+"行第"+j+"列时出错,请检查excel里的数据",e);
		}finally{
			IOUtils.closeQuietly(is);
		}
		return list;
	}


	
	/**
	 * 导出Excel File
	 * 记得3个数组长度必须一样
	 * @param titles excel的标题
	 * @param collengths 每列的宽度 相对的 n*256 ,不想设置宽度这个数组对应的下标为0 如果这个数组是空数组或null 则不管宽度
	 * @param fields excel对象对应的POJO的属性或map里的key
	 * @param list 导出的对象集合列表
	 * @param out 输出流
	 */
	public static <T> void exportExcelFile(String sheetName,int[] collengths,String[] titles,String[] fields,List<T> list,OutputStream out){
		if(sheetName==null || titles==null || fields==null || (fields.length!=titles.length)  || list==null || out==null){
			throw new IllegalArgumentException("wrong arguments");
		}
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName);
		// 设置单元格风格
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		if(collengths!=null && collengths.length>0 && collengths.length==titles.length){
			for(int i=0;i<collengths.length;i++){
				if(collengths[i]>0){
					sheet.setColumnWidth(i,(collengths[i] * 256));
				}
			}
		}
		HSSFRow titleRow = sheet.createRow(0);
		for(int i=0;i<titles.length;i++){
			HSSFCell cell = titleRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 定义单元格为字符串类型
			cell.setCellValue(titles[i]);// 在单元格中输入一些内容
			cell.setCellStyle(style);
		}
		sheet.createFreezePane(0, 1, 0, 1);
		// 设置单元格风格
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		int size = list.size();
		for(int i=0;i<size;i++){
			T obj = list.get(i);
			HSSFRow row = sheet.createRow(i + 1);
			for(int j=0;j<fields.length;j++){
				String field = fields[j];
				try {
					Object value = PropertyUtils.getProperty(obj, field);
					if(value==null){
						value = StringUtils.EMPTY;
					}else{
						if(value instanceof Date){
							value = DateFormatUtils.format((Date)value, CommonConstants.DATE.FORMAT_YYYY_MM_dd_HH_mm);
						}else{
							value = value.toString();
						}
					}
					HSSFCell cell = row.createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(value.toString());
					cell.setCellStyle(style1);
				} catch (Exception e) {
					throw new RuntimeException("reflect property fail",e);
				}
			}
		}
		try{
			workbook.write(out);
			out.flush();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} 
		}
	}
	
	/**
	 * 导出Excel File
	 * 记得3个数组长度必须一样
	 * @param titles excel的标题
	 * @param collengths 每列的宽度 相对的 n*256 ,不想设置宽度这个数组对应的下标为0 如果这个数组是空数组或null 则不管宽度
	 * @param fields excel对象对应的POJO的属性或map里的key
	 * @param list 导出的对象集合列表
	 * @param out 输出流
	 */
	public static <T> void exportExcelFile(String sheetName,int[] collengths,String[] titles,String[] fields,T[] arr,OutputStream out){
		if(sheetName==null || titles==null || fields==null || (fields.length!=titles.length)  || arr==null || out==null){
			throw new IllegalArgumentException("wrong arguments");
		}
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName);
		// 设置单元格风格
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		if(collengths!=null && collengths.length>0 && collengths.length==titles.length){
			for(int i=0;i<collengths.length;i++){
				if(collengths[i]>0){
					sheet.setColumnWidth(i,(collengths[i] * 256));
				}
			}
		}
		HSSFRow titleRow = sheet.createRow(0);
		for(int i=0;i<titles.length;i++){
			HSSFCell cell = titleRow.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 定义单元格为字符串类型
			cell.setCellValue(titles[i]);// 在单元格中输入一些内容
			cell.setCellStyle(style);
		}
		sheet.createFreezePane(0, 1, 0, 1);
		// 设置单元格风格
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		int size = arr.length;
		for(int i=0;i<size;i++){
			T obj = arr[i];
			HSSFRow row = sheet.createRow(i + 1);
			for(int j=0;j<fields.length;j++){
				String field = fields[j];
				try {
					Object value = PropertyUtils.getProperty(obj, field);
					if(value==null){
						value = StringUtils.EMPTY;
					}else{
						if(value instanceof Date){
							value = DateFormatUtils.format((Date)value, CommonConstants.DATE.FORMAT_YYYY_MM_dd_HH_mm);
						}else{
							value = value.toString();
						}
					}
					HSSFCell cell = row.createCell(j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(value.toString());
					cell.setCellStyle(style1);
				} catch (Exception e) {
					throw new RuntimeException("reflect property fail",e);
				}
			}
		}
		try{
			workbook.write(out);
			out.flush();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} 
		}
	}
	
	public static Object getCellValue(HSSFCell cell){
		if(cell == null){
			return StringUtils.EMPTY;
		}
		if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			return new BigDecimal(cell.getNumericCellValue());
		}else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
			return StringUtils.trim(cell.getStringCellValue());
		}else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
			return StringUtils.EMPTY;
		}else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
			return cell.getBooleanCellValue();
		}else{
			throw new RuntimeException("unsupport HSSFCell type:"+cell.getCellType());
		}
	}
	

	private static <T> void excelFieldToBeanField(T obj,Object value, String field) throws NoSuchFieldException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Field f = Reflections.getAccessibleField(obj, field);
		//f.setAccessible(true);
		Class<?> fieldClass = f.getType();
		if(fieldClass.equals(String.class)){
			PropertyUtils.setProperty(obj, field, String.valueOf(value));
		}else if(fieldClass.equals(BigDecimal.class)){
			PropertyUtils.setProperty(obj, field, ToNumberUtils.getBigDecimal(value));
		}else if(fieldClass.equals(Long.class)){
			PropertyUtils.setProperty(obj, field, ToNumberUtils.getLongValue(value));
		}else if(fieldClass.equals(Integer.class)){
			PropertyUtils.setProperty(obj, field, ToNumberUtils.getIntegerValue(value));
		}else if(fieldClass.equals(Date.class)){
			PropertyUtils.setProperty(obj, field, TimeUtils.getDateValue(value));
		} else{
			throw new RuntimeException("excel 暂时不支持"+fieldClass.getName()+"对象类型的转换 请自行扩展该函数!");
		}
	}
}
