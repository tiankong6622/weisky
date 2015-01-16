package org.javafans.common.utils.des3;

import java.security.Key;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 3DES加解密工具类
 * 
 * @author WeiSky
 *
 */
@SuppressWarnings("restriction")
public class Des3 {

	/**
	 * 默认秘钥
	 */
	private final static String secretKey = "yishengweisky@2014#10#2015!@#";
	
	/**
	 * 默认向量
	 */
	private final static String iv = "01234567";
	
	/**
	 * 加解密统一使用的编码格式
	 */
	private final static String encoding = "utf-8";
	
	public final static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * 加密（采用默认秘钥）
	 * 
	 * @param plainText ： 需要加密的普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encodeString(String plainText) throws Exception{
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		deskey = keyFactory.generateSecret(spec);
		
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}
	
	/**
	 * 加密（采用自定义秘钥）
	 * 
	 * @param plainText ： 需要加密的普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encodeString(String plainText, final String sfsecretKey) throws Exception{
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(sfsecretKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		deskey = keyFactory.generateSecret(spec);
		
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.encode(encryptData);
	}
	
	/**
	 * 对list集合加密（采用默认秘钥）
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String encodeList(List<?> list) throws Exception{
		return Des3.encodeString(mapper.writeValueAsString(list));
	}
	
	/**
	 * 对list集合加密（采用自定义秘钥）
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String encodeList(List<?> list, final String sfsecretKey) throws Exception{
		return Des3.encodeString(mapper.writeValueAsString(list), sfsecretKey);
	}
	
	/**
	 * 对map集合加密（采用默认秘钥）
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static <k, v> String encodeMap(Map<k,v> map) throws Exception{
		return Des3.encodeString(mapper.writeValueAsString(map));
	}
	
	/**
	 * 对map集合加密（采用自定义秘钥）
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static <k, v> String encodeMap(Map<k,v> map, final String sfsecretKey) throws Exception{
		return Des3.encodeString(mapper.writeValueAsString(map), sfsecretKey);
	}
	
	
	
	/**
	 * 解密（采用默认秘钥）
	 * 
	 * @param encryptText : 需要解密的普通文本
	 * @return
	 * @throws Exception
	 */
	public static String decodeString(String encryptText) throws Exception{
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		deskey = keyFactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		
		byte[] decryData = cipher.doFinal(Base64.decode(encryptText));
		return new String(decryData, encoding);
	}
	
	/**
	 * 解密（采用自定义秘钥）
	 * 
	 * @param encryptText : 需要解密的普通文本
	 * @return
	 * @throws Exception
	 */
	public static String decodeString(String encryptText, final String sfsecretKeys) throws Exception{
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(sfsecretKeys.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
		deskey = keyFactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		
		byte[] decryData = cipher.doFinal(Base64.decode(encryptText));
		return new String(decryData, encoding);
	}
	
	/**
	 * 对list集合解密（采用默认秘钥）
	 * 
	 * @param encrypt
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 * @throws Exception
	 */
	public static List<?> decodeList(String encrypt, Class<?> collectionClass, Class<?>... elementClasses) throws Exception{
		JavaType javaType = getCollectionType(collectionClass, elementClasses); 
		return mapper.readValue(Des3.decodeString(encrypt), javaType);
	}
	
	/**
	 * 对list集合解密（采用自定义秘钥）
	 * 
	 * @param encrypt
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 * @throws Exception
	 */
	public static List<?> decodeList(String encrypt, final String sfsecretKeys, Class<?> collectionClass, Class<?>... elementClasses) throws Exception{
		JavaType javaType = getCollectionType(collectionClass, elementClasses); 
		return mapper.readValue(Des3.decodeString(encrypt, sfsecretKeys), javaType);
	}
	
	/**
	 * 对map集合解密（采用默认秘钥）
	 * 
	 * @param encrypt
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <v, k> Map<k,v> decodeMap(String encrypt, Class<?> elementClasses) throws Exception{
		return (Map<k, v>) mapper.readValue(Des3.decodeString(encrypt), elementClasses);
	}
	
	/**
	 * 对map集合解密（采用自定义秘钥）
	 * 
	 * @param encrypt
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <v, k> Map<k,v> decodeMap(String encrypt, final String sfsecretKeys, Class<?> elementClasses) throws Exception{
		return (Map<k, v>) mapper.readValue(Des3.decodeString(encrypt, sfsecretKeys), elementClasses);
	}
	
	/**
	 * 获取泛型的Collentcion的类型
	 * 
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses){
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
	
	
	public static void main(String[] args) throws Exception {
		/*TestJaveBean test = new TestJaveBean();
		test.setAge(12);
		test.setId(2L);
		test.setName("xxx");
		
		List<TestJaveBean> list = Lists.newArrayListWithExpectedSize(1);
		list.add(test);
		
		System.out.println(encodeList(list));
		
		@SuppressWarnings("unchecked")
		List<TestJaveBean> li = (List<TestJaveBean>) decodeList("8c/yzqcCFkrsSM43eqhUyt7A6s3n8juMrfI0K5C3W0UcGo0tvUwf7g==", ArrayList.class, TestJaveBean.class);
		System.out.println(li.get(0).getName());*/
		
		/*ObjectMapper obmap = new ObjectMapper();
		String json = obmap.writeValueAsString(list);
		System.out.println(Des3.encodeString(json));
		System.out.println(Des3.decodeString("8c/yzqcCFkrsSM43eqhUyt7A6s3n8juMrfI0K5C3W0UcGo0tvUwf7g=="));
		
		JavaType javaType = getCollectionType(ArrayList.class, TestJaveBean.class); 
		List<TestJaveBean> test2 = mapper.readValue(Des3.decodeString("8c/yzqcCFkrsSM43eqhUyt7A6s3n8juMrfI0K5C3W0UcGo0tvUwf7g=="), javaType);
		System.out.println(test2.get(0).getName());*/
		
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
		map.put("a", "123");
		map.put("b", "456");
		System.out.println(Des3.encodeMap(map));
		Map<String,Object> mm = Des3.decodeMap(Des3.encodeMap(map), Map.class);
		System.out.println(mm.get("a"));
		//System.out.println(Des3.decodeMap("PFIVMwBh5lFqFV8KaIaeLVsdz6/KUZkK", HashMap.class, TestJaveBean.class));
	}
	
	
}
