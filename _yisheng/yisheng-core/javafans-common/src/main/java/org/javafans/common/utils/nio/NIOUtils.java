package org.javafans.common.utils.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;

import org.javafans.common.utils.exception.Exceptions;

/**
 * NIO相关
 * @author ChenJunhui
 */
public class NIOUtils {
	
	public static final int DEFAULT_BUFFER_SIZE = 1024;
	
	public static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * NIO方式copy文件
	 * @param in
	 * @param out
	 * @param bufferSize buffer缓冲区
	 */
	public static void copyFile(File in,File out,int bufferSize){
		try {
			NIOUtils.copyFile(new FileInputStream(in), new FileOutputStream(out), bufferSize);
		} catch (FileNotFoundException e) {
			throw Exceptions.unchecked(e);
		}
	}
	
	/**
	 * NIO方式copy文件
	 * @param in
	 * @param out
	 * @param bufferSize buffer缓冲区
	 */
	public static void copyFile(FileInputStream in,FileOutputStream out,int bufferSize){
		try {
			FileChannel inChannel = in.getChannel();
			FileChannel outChannel = out.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			while(inChannel.read(buffer)!=-1){
				//flip方法 把极限设为位置值 并把位置设为0 如果最后一次读取的时候 文件数量无法填充buffer缓冲区 那么就用数据填满缓冲区
				buffer.flip();
				outChannel.write(buffer);
				//clear方法 把极限设为容量值 并把位置设为0
				buffer.clear();
				//remind 方法 不改变极限值 把位置设置0
			}
			inChannel.close();
			outChannel.close();
			//也可以用以下两个函数来做channel数据copy
			//inChannel.transferTo(0, inChannel.size(), outChannel);
			//outChannel.transferFrom(inChannel, 0, inChannel.size());
		} catch (IOException e) {
			throw Exceptions.unchecked(e);
		}
	}
	
	public static void main(String args[]) throws Exception{
		File in = new File("c:\\from.txt");
		File out = new File("c:\\to.txt");
		NIOUtils.copyFile(in, out, DEFAULT_BUFFER_SIZE);
		
		//编码相关
		ByteBuffer buffer = ByteBuffer.wrap("撒打发士大夫".getBytes(DEFAULT_CHARSET));
		System.out.println(buffer.asCharBuffer());
		Charset charset = Charset.forName(DEFAULT_CHARSET);
		CharBuffer cb = charset.decode(buffer);
		System.out.println(cb);
		MappedByteBuffer mbb =  new RandomAccessFile(new File("c:\\xxxs.txt"), "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 33333);
		mbb.put("三闾大夫".getBytes("UTF-8"));
		mbb.flip();
		//System.out.println(Charset.forName(DEFAULT_CHARSET).decode(mbb));
		FileOutputStream fos = new FileOutputStream("c:\\xxb.sql");
		FileLock fl = fos.getChannel().tryLock(); //该锁对虚拟机进程 和操作系统其他进程都起效果
		if(fl!=null){
			Thread.sleep(60000);
			fl.release();
			fos.getChannel().close();
		}
		//tryLock(long position, long size, boolean shared) 可以多文件的部分区域加锁
	}
}
