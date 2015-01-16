package org.javafans.common.utils.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

/**
 * zip相关的
 * @author ChenJunhui
 *
 */
public class ZipUtils {
	
	private static final int BUFFER = 2048;
	
	/**
	 * 将指定的文件打成zip压缩包
	 * @param output
	 * @param files
	 */
	public static final void zipFiles(OutputStream output, String... files) {
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(output);
			BufferedInputStream origin = null;
			byte data[] = new byte[BUFFER];
			for (String file : files) {
				File f = new File(file);
				FileInputStream fi = new FileInputStream(f);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(f.getName());
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				IOUtils.closeQuietly(fi);
				IOUtils.closeQuietly(origin);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(output);
		}
	}
	
	
	public static void main(String argv[])  throws Exception{
		ZipUtils.zipFiles(new FileOutputStream("C:\\A.zip"), "c:\\member1.sql","c:\\mmw.sql");
		
		/**
		 * servlet里这样搞
		 * 	response.setContentType("application/zip; charset=UTF-8");
			response.setHeader("Content-Disposition","Attachment;filename=aaa.zip");
			ZipUtils.zipFiles(response.getOutputStream(), "c:\\member1.sql","c:\\mmw.sql");
		 */
    }
}


