package org.itboys.framework.mail;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.itboys.framework.template.VelocityMarks;
import org.itboys.framework.template.VmContextModel;

/**
 * 邮件发送相关
 * @author ChenJunhui
 *
 */
public class MailSender {

	/**
	 * 可在spring的配置文件里加上
	 * <bean id="javaMailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
			<property name="host" ref="smtp.163.com" />
			<property name="username" value="786553789@qq.com" />
			<property name="password" value="556WULI779" />
			<property name="defaultEncoding" value="UTF-8" />
		</bean>
	 */
	private JavaMailSenderImpl javaMailSender;
	private VelocityMarks velocityMarks;
	
	/**
	 * 给1-n个人发 普通文本 邮件
	 * @param content 邮件内容
	 * @param subject 邮件主题
	 * @param toMail  邮件接收者 1-n个人 可以传数组 
	 */
	public void sendMail(String content,String subject,String...toMail){
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(javaMailSender.getUsername());
		smm.setSubject(subject);
		smm.setText(content);
		smm.setTo(toMail);
		javaMailSender.send(smm);
	}

	/**
	 * 根据vm模板内容给 多个人发普通文本邮件
	 * @param vmContextModel
	 * @param subject 邮件主题
	 * @param toMail 邮件接收者 1-n个人 可以传数组 
	 */
	public void sendMail(String vmPath, VmContextModel vmContextModel,
			String subject, String... toMail) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(javaMailSender.getUsername());
		smm.setSubject(subject);
		smm.setText(velocityMarks.getMergedTemplateContent(vmPath,vmContextModel));
		smm.setTo(toMail);
		javaMailSender.send(smm);
	}
	
	/**
	 * 富文本形式发送邮件 发送html
	 * @param content 邮件内容
	 * @param subject 邮件主题
	 * @param toMail  邮件接收者 1-n个人 可以传数组 
	 */
	public void sendRichTextMail(String content,String subject,String...toMail){
		MimeMessage msg = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
			 helper.setFrom(javaMailSender.getUsername());
		    helper.setTo(toMail);
		    helper.setSubject(subject);
		    helper.setText(content,true);
		    javaMailSender.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	/**
	 * 基于vm模板富文本形式发送邮件 发送html
	 * @param content 邮件内容
	 * @param subject 邮件主题
	 * @param toMail  邮件接收者 1-n个人 可以传数组 
	 */
	public void sendRichTextMail(String vmPath, VmContextModel vmContextModel,String subject,String...toMail){
		MimeMessage msg = javaMailSender.createMimeMessage();
		//第二个参数为true 说明发送html
	    MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
			 helper.setFrom(javaMailSender.getUsername());
		    helper.setTo(toMail);
		    helper.setSubject(subject);
		    helper.setText(velocityMarks.getMergedTemplateContent(vmPath,vmContextModel),true);
		    javaMailSender.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	/**
	 * 富文本 发送带附件邮件
	 * @param content
	 * @param subject
	 * @param toMai
	 * @param attachmentMap  key 附件显示名称  value InputStream 附件文件 (抽象类)
	 */
	public void sendMailWithAttachments(String content,String subject,final Map<String,InputStream> attachmentMap,String...toMail){
		MimeMessage msg = javaMailSender.createMimeMessage();
		//第二个参数为true 说明发送html
	    MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
			helper.setFrom(javaMailSender.getUsername());
		    helper.setTo(toMail);
		    helper.setSubject(subject);
		    helper.setText(content,true);
		    if(attachmentMap!=null && !attachmentMap.isEmpty()){
		    	for(final String key:attachmentMap.keySet()){
		    		helper.addAttachment(new  String(key.getBytes( "iso-8859-1"), javaMailSender.getDefaultEncoding()),
		    				new  InputStreamSource() {
				    			@Override
				    			public InputStream getInputStream() throws IOException {
				    				return attachmentMap.get(key);
				    			}
				    		}
		    		);
		    	}
		    }
		    
		    javaMailSender.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	public void setVelocityMarks(VelocityMarks velocityMarks) {
		this.velocityMarks = velocityMarks;
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
}
