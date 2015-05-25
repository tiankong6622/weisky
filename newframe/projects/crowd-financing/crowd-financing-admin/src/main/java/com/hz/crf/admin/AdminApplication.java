package com.hz.crf.admin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hz.crf.model.interceptor.LoginIntercepter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AdminApplication extends WebMvcConfigurerAdapter {

	// -Dserver.port=$PORT 指定启动的 端口 右键 Run as 或 Debug as 的 Run Configuration
	// 或 Debug Configuration 的Arguments 的 vm arguments可修改启动端口
	// 可以这样加上 -Dserver.port=1984 -Xms128m -Xmx128m -XX:PermSize=64m -Xss256k
	// -XX:+DisableExplicitGC -XX:+UseParNewGC
	// -XX:+UseCMSCompactAtFullCollection -XX:-CMSParallelRemarkEnabled
	// -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -Xloggc:d:/logs/gc.log
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AdminApplication.class);
		app.setWebEnvironment(true);
		app.setShowBanner(false);
		Set<Object> set = new HashSet<Object>();
		set.add("applicationContext.xml");
		app.setSources(set);
		app.run(args);
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**");
	}

}
