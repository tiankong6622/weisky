package com.hz.yisheng.nio.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.UnknownHostException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.common.collect.Maps;
import com.hz.yisheng.nio.conf.NettyRFIDConstant;
import com.hz.yisheng.nio.dao.BabyPositionDAO;
import com.hz.yisheng.nio.orm.BabyPosition;
import com.hz.yisheng.nio.utils.CRC16;
import com.hz.yisheng.nio.utils.ClassPathXmlApplicationContext;
import com.hz.yisheng.nosql.redis.RedisApi;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * RFID服务端逻辑处理，使用UDP广播通讯协议
 * 
 * @author WeiSky
 *
 */
public class NettyUDPRFIDServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{
	
	private static Logger logger = LoggerFactory.getLogger(NettyUDPRFIDServerHandler.class);
	
	private RedisApi redisApi;
	
	public NettyUDPRFIDServerHandler(){
		ApplicationContext ctx = ClassPathXmlApplicationContext.getClassPathXmlApplicationContext("applicationcContext-nosql.xml");
		redisApi = (RedisApi) ctx.getBean("redisApi");
	}
	
	/**
	 * 处理接收到的数据包
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx,
			DatagramPacket packet) throws Exception {
		String req = packet.content().toString(CharsetUtil.UTF_8);
		boolean bool = CRC16.checkCrc16(req);
		if(bool){//校验成功
			String str = String.valueOf(CRC16.getFinalChars(req));
			rfidHeartBeat(str);
			
			if(StringUtils.equals(NettyRFIDConstant.CMD_DA, str.substring(0, 3))){//功能代码，传输信息
				handchainHeartBeat(str);
			}
		}else{
			logger.warn("校验信息失败:" + req);
		}
	}
	
	/**
	 * rfid一体机心跳检测消息处理
	 * @param value
	 */
	public void rfidHeartBeat(String value){
		String heartBeatStr = value;
		String add = heartBeatStr.substring(3, 11);//获取设备地址 
		Map<String, String> pushMap = Maps.newHashMapWithExpectedSize(3);
		pushMap.put("addr", add);//一体机的设备地址，16位进制
		pushMap.put("ct", String.valueOf(System.currentTimeMillis()));//创建时间(或者是最后一次更新时间)，系统毫秒数
		pushMap.put("count", "0");//在规定的时间内，失败的次数。若失败次数大于某一个值，需要发邮件或者短信给相关人员
		redisApi.hmset("rfidhb" + add, pushMap);//存入redis,存入的设备地址是16进制
		System.out.println("rfidhb-"+ value +":" + redisApi.hgetAll("rfidhb" + add));
	}
	
	/**
	 * 手环的心跳检测消息处理
	 * @param value
	 */
	public void handchainHeartBeat(String value){
		String heartBeatStr = value;
		
		String tId = heartBeatStr.substring(15, 23);//获取婴儿手环ID,16进制表示 
		Map<String, String> pushMap = Maps.newHashMapWithExpectedSize(3);
		pushMap.put("tId", tId);//婴儿手环ID
		pushMap.put("ct", String.valueOf(System.currentTimeMillis()));//创建时间(或者是最后一次更新时间)，系统毫秒数
		pushMap.put("count", "0");//在规定的时间内，失败的次数。若失败次数大于某一个值，需要发邮件或者短信给相关人员
		redisApi.hmset("cmdhb" + tId, pushMap);//存入redis,存入的婴儿手环ID是16进制
		System.out.println("cmdhb--" + value + ":" + redisApi.hgetAll("cmdhb" + tId));
	}
	

	public static void main(String[] args) throws UnknownHostException {
		Mongo mongo = new MongoClient("192.168.3.224", 27017);
		Morphia morphia = new Morphia(); 
		BabyPositionDAO dao = new BabyPositionDAO(morphia, mongo);
		BabyPosition posi = new BabyPosition();
		posi.setAddr("AAAAA1");
		posi.setCt(System.currentTimeMillis());
		posi.setId(System.currentTimeMillis());
		System.out.println(dao.save(posi));
		
		Datastore ds = morphia.createDatastore(mongo, "BabyPosition"); 
		System.out.println(ds.find(BabyPosition.class));
		
		/*QueryResults<BabyPosition> res=dao.find(); 
		System.out.println(res);*/
	}
	
	
}















