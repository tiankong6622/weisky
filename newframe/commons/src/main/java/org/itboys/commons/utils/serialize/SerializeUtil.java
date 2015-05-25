package org.itboys.commons.utils.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化相关
 * @author ChenJunhui
 *
 */
public class SerializeUtil {
	
	private  static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);
	
	/**
	 * 序列化对象
	 * 
	 * @param object
	 * @return
	 */
	public static <T > byte[] serialize(T object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error("serialize fail",e);
			return null;
		}finally{
			IOUtils.closeQuietly(baos);
			IOUtils.closeQuietly(oos);
		}
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return (T)ois.readObject();
		} catch (Exception e) {
			logger.error("unserialize fail",e);
			return null;
		}finally{
			IOUtils.closeQuietly(ois);
			IOUtils.closeQuietly(bais);
		}
	}
}
