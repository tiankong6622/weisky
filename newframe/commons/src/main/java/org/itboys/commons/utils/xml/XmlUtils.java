package org.itboys.commons.utils.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;


/**
 * javax 解析xml相关
 * @author ChenJunhui
 *
 */
public class XmlUtils {

	public static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory .newInstance(); 
	
	/**
	 * 根据xml类型获取 Document对象
	 * @param xml
	 * @return
	 */
	public static Document getDocument(InputStream stream){
		try {
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			Document doc = builder.parse(stream);
			return doc;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(stream);
		}
	}
	
	/**
	 * 获取类路径的xml文档
	 * @param filePath
	 * @return
	 */
	public static Document getDocumentFromClassPath(String fileName){
		try {
			InputStream stream =  Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			return getDocument(stream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
