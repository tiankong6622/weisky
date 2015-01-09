package com.hz.yisheng.handchain.nio.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * RFID服务端逻辑处理，使用UDP广播通讯协议
 * 
 * @author WeiSky
 *
 */
public class NettyUDPRFIDServerHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	/**
	 * 处理接收到的数据包
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx,
			DatagramPacket packet) throws Exception {
		char[] req = packet.content().toString(CharsetUtil.UTF_8).toCharArray();
		
		System.out.println(req);
	}
	
	/**
	 * 字符串转10进制的ascii码
	 * 
	 * @param str
	 */
	protected Character[] strToAscii10(String str){
		
		char[] resultArr = getFinalChars(str);//去除帧头和帧尾后的字符数组
		char[] cmdCode = new char[3];
		cmdCode = getPartChars(resultArr, cmdCode, 3);//获取数据包中的功能代码
		
		List<String> codeList = new ArrayList<String>();//存储加工后最终数据
		for (char c : cmdCode){
			codeList.add(String.valueOf((int)c));//功能代码字符转10 进制
		}
		
		String tempStr = String.valueOf(resultArr);//字符数组转成字符串，方便下一步截取
		System.out.println(tempStr.toCharArray());
		for(int i = 3; i < resultArr.length - 4; i = i+2){
			codeList.add("0x" + tempStr.substring(i, i+2).toLowerCase());//拼装成16进制，并放入list中
		}
		
		Character[] result = new Character[codeList.size()];
		for(int i  = 0; i < result.length; i ++){
			result[i] = codeList.get(i).toCharArray()[0];
		}
		return result;
	}
	
	/**
	 * 获取字符数组中的一部分数组（有顺序的获取）
	 * 
	 * @param targer
	 * @param res
	 * @param index
	 * @return
	 */
	private char[] getPartChars(char[] targer, char[] res, int index){
		if(targer == null || targer.length == 0 || index < 0 || index >= targer.length){
			return res;
		}
		
		for(int i = 0; i < index; i ++){
			res[i] = targer[i];
		}
		return res;
	}
	
	/**
	 * 去除数据包的帧头和帧尾
	 * 
	 * @param str
	 * @return
	 */
	private char[] getFinalChars(String str){
		return deleteItem(deleteItem(str.toCharArray(),0),str.toCharArray().length-2);
	}
	
	/**
	 * 删除数组指定的某个元素
	 * 
	 * @param arr
	 * @param delIndex
	 * @return
	 */
	public static char[] deleteItem(char[] arr, int delIndex){
		char[] temp = new char[arr.length - 1];
		if(arr == null || arr.length == 0 || delIndex < 0 || delIndex >= arr.length){
			return temp;
		}
		
		int k = 0;
		for(int i = 0; i < arr.length; i++){
			if(i != delIndex){
				temp[k] = arr[i];
				k ++;
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {
		NettyUDPRFIDServerHandler vv = new NettyUDPRFIDServerHandler();
		vv.strToAscii10(">HB0000000100023700D55B");
		System.out.println("0x78".toCharArray());
	}
	
	
}
