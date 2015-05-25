package org.itboys.framework.spring.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.itboys.framework.spring.editor.EditorHolder;

/**
 * Controller 父类
 * @author ChenJunhui
 */
public abstract class BaseController {
	
	protected  Logger logger = LoggerFactory.getLogger(getClass());

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, EditorHolder.dateEditor);
        binder.registerCustomEditor(Integer.class, EditorHolder.integerEditor);
        binder.registerCustomEditor(Long.class, EditorHolder.longEditor);
        binder.registerCustomEditor(BigDecimal.class, EditorHolder.bigDecimalEditor);
    }

	
	
}
