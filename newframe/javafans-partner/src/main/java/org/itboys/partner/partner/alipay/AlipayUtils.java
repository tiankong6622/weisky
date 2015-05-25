package org.itboys.partner.partner.alipay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.itboys.commons.utils.string.CommonStringUtils;
import org.itboys.partner.partner.PayConfigHolder;
import org.itboys.commons.utils.io.FileIOUtils;

import com.google.common.collect.Maps;

/**
 * 支付宝调用通用逻辑处理
 * @author ChenJunhui
 */
public class AlipayUtils {

	 /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();
        for (int i = 0,size=keys.size(); i < size; i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr.append(key).append(AlipayConstants.CHARACTER_EQ).append(value);
            } else {
            	prestr.append(key).append(AlipayConstants.CHARACTER_EQ).append(value).append(AlipayConstants.CHARACTER_AND);
            }
        }
        return prestr.toString();
    }
    
    /**
     * 生成签名结果
     * @param sArray 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray) {
        String prestr = createLinkString(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        prestr = prestr + PayConfigHolder.getAlipayKey(); //把拼接后的字符串再与安全校验码直接连接起来
        String mysign = md5(prestr);
        return mysign;
    }
    
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = Maps.newHashMap();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        Set<Entry<String,String>> entrys = sArray.entrySet();
        for(Entry<String,String> entry:entrys){
        	if(StringUtils.isNotBlank(entry.getValue()) && CommonStringUtils.in(entry.getKey(), "sign","sign_type")){
        		result.put(entry.getKey(), entry.getValue());
        	} 
        }
        return result;
    }
    
    /**
     * 对字符串进行MD5签名
     * 
     * @param text
     *            明文
     * 
     * @return 密文
     */
    public static String md5(String text) {
        return DigestUtils.md5Hex(getContentBytes(text));

    }
    
    /**
     * 验证消息是否是支付宝发出的合法消息
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(Map<String, String> params) {
		Map<String, String> filterParam = paraFilter(params);
		String mysign = buildMysign(filterParam);
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			responseTxt = verifyResponse(params.get("notify_id"));
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		if (mysign.equals(sign) && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
    }
    
    /**
     * 获取远程服务器ATN结果,验证返回URL
     * @param notify_id 通知校验ID
     * @return 服务器ATN结果
     * 验证结果集：
     * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
     * true 返回正确信息
     * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    public static String verifyResponse(String notify_id) {
         //获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求
         String veryfy_url = AlipayConstants.HTTPS_VERIFY_URL + "partner=" + PayConfigHolder.getAlipayPartner() + "&notify_id=" + notify_id;
         return checkUrl(veryfy_url);
     }
    
    /**
     * 获取远程服务器ATN结果
     * @param urlvalue 指定URL路径地址
     * @return 服务器ATN结果
     * 验证结果集：
     * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
     * true 返回正确信息
     * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
     private static String checkUrl(String urlvalue) {
         String inputLine = StringUtils.EMPTY;
         BufferedReader in = null;
         try {
             URL url = new URL(urlvalue);
             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
             in = new BufferedReader(new InputStreamReader(urlConnection .getInputStream()));
             inputLine = in.readLine().toString();
         } catch (Exception e) {
             e.printStackTrace();
             inputLine = StringUtils.EMPTY;
         }finally{
        	 FileIOUtils.close(in);
         }
         return inputLine;
     }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content) {
        if (StringUtils.isBlank(content)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(AlipayConstants.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
