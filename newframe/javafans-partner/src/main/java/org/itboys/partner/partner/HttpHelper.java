package org.itboys.partner.partner;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.itboys.commons.utils.encryption.Digests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;


/**
 * http请求相关
 * @author ChenJunhui
 *
 */
public class HttpHelper {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
	public final static String FAIL="fail";

	/** 连接超时时间，缺省为8秒钟 */
	private static int defaultConnectionTimeout = 8000;

	/** 回应超时时间，缺省为30秒钟 */
	private static int defaultSoTimeout = 30000;

	/** 闲置连接超时时间，缺省为60秒钟 */
	//private static int defaultIdleConnTimeout = 60000;

	//private static int defaultMaxConnPerHost = 30;

	//private static int defaultMaxTotalConn = 80;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒 */
	private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;
	
	 /**
     * HTTP连接管理器，创建一个线程安全的HTTP连接池
     */
   /* private static HttpConnectionManager  connectionManager = new MultiThreadedHttpConnectionManager();
    static{
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
        connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);
        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(defaultIdleConnTimeout);
        ict.start();
    }*/
    
    /**
     * 获取httpclient对象
     * @return
     */
    public static HttpClient getHttpClient(){
    	HttpClient httpClient =  new HttpClient();
    	//HttpClient httpClient = new HttpClient(connectionManager);
        // 设置连接超时
        int connectionTimeout = defaultConnectionTimeout;
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
        // 设置等待ConnectionManager释放connection的时间
        httpClient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);
        return httpClient;
    }

	/**
	 * get请求获取返回内容
	 * @param url
	 * @return
	 */
	public static String doGet(String url){
		HttpClient httpClient = getHttpClient();
		GetMethod getMethod = new GetMethod(url);
		try{
			int statusCode = httpClient.executeMethod(getMethod);
		    if (statusCode != HttpStatus.SC_OK) {
		    	logger.error("http 请求失败 url is {},状态码 is {}",url,statusCode);
		    	return FAIL;
		    }
		    return getMethod.getResponseBodyAsString();
		}catch(Exception e){
			logger.error("get 请求失败 url is "+url,e);
			return FAIL;
		}finally{
			if(getMethod!=null){
				getMethod.releaseConnection();
			}
		}
	}
	
	/**
	 * 带参数get请求获取返回内容
	 * @param url
	 * @return
	 */
	public static String doGet(String url,Map<String,Object> param){
		StringBuilder sb=new StringBuilder();
		for(Iterator<Entry<String,Object>> iter = param.entrySet().iterator();iter.hasNext();){
			Entry<String,Object> entry = iter.next();
			if(entry.getValue()!=null){
				sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		String paramStr = sb.toString();
		if(StringUtils.indexOf(url, "?")==-1){
			paramStr=paramStr.replaceFirst("&", "?");
		}
		return doGet(url+paramStr);
	}
	
	/**
	 * 返回post请求的字符串
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doPost(String url,Map<String,Object> param){
		HttpClient httpClient = getHttpClient();
		PostMethod postMethod = new PostMethod(url);
		return postAndRerunStringContent(url, param, httpClient, postMethod);
	}
	
	public static String doPostWithUtf8(String url,Map<String,Object> param){
		HttpClient httpClient = getHttpClient();
		PostMethod postMethod = new UTF8PostMethod(url);
		return postAndRerunStringContent(url, param, httpClient, postMethod);
	}
	
	public static String doPostWithGbk(String url,Map<String,Object> param){
		HttpClient httpClient = getHttpClient();
		PostMethod postMethod = new GbkPostMethod(url);
		return postAndRerunStringContent(url, param, httpClient, postMethod);
	}
	
	
	public static class UTF8PostMethod extends PostMethod{
        public UTF8PostMethod(String url){
            super(url);
        }
        @Override
        public String getRequestCharSet() {
            return "UTF-8";
        }
    }
	
	public static class GbkPostMethod extends PostMethod{
        public GbkPostMethod(String url){
            super(url);
        }
        @Override
        public String getRequestCharSet() {
            return "gbk";
        }
    }

	private static String postAndRerunStringContent(String url,
			Map<String, Object> param, HttpClient httpClient,
			PostMethod postMethod) {
		NameValuePair[] postParam = new NameValuePair[param.size()];
		int i=0;
		for(Iterator<Entry<String,Object>> iter = param.entrySet().iterator();iter.hasNext();){
			Entry<String, Object> entry = iter.next();
			NameValuePair nvp = new NameValuePair(entry.getKey(), entry.getValue()==null?"":entry.getValue().toString());
			postParam[i] = nvp;
			i++;
		}
		postMethod.addParameters(postParam);
		try{
			int statusCode = httpClient.executeMethod(postMethod);
		    if (statusCode != HttpStatus.SC_OK) {
		    	logger.error("http 请求失败 url is {},状态码 is {},参数 is {}",new Object[]{url,statusCode,param});
		    	return FAIL;
		    }
		    return postMethod.getResponseBodyAsString();
		}catch(Exception e){
			logger.error("get 请求失败 url is "+url+" param is "+param,e);
			return FAIL;
		}finally{
			if(postMethod!=null){
				postMethod.releaseConnection();
			}
		}
	}
	
	public static void main(String args[]){
		Map<String,Object> params = Maps.newHashMap();
		params.put("username", "yepengsss");
		params.put("appkeys", "9d0648e4f1bc60c17e41fbe6");
		params.put("password ", Digests.md5("yepeng"));
		params.put("receiver_type", "4");
		params.put("code", "2d46247396827cc37f8dcb315931edc4");
		System.out.println(HttpHelper.doPost("http://api.jpush.cn:8800/sendmsg/sendmsg",params));
	}
}
