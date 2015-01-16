package org.javafans.web.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.web.AjaxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异步校验session中某个key是否存在
 * @author ChenJunhui
 *
 */
@Controller
public class CheckSessionController {

	@RequestMapping(value = "/checkSession/{sessionKey}")
	public void checkSession(@PathVariable("sessionKey") String sessionKey, HttpServletRequest request, HttpServletResponse response){
		AjaxUtils.renderText(response, Boolean.valueOf(request.getSession().getAttribute(sessionKey)!=null).toString());
	}
	
}
