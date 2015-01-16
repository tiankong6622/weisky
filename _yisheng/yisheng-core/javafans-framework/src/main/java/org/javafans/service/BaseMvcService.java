package org.javafans.service;

import java.math.BigDecimal;
import java.util.Date;

import org.javafans.web.springmvc.editor.EditorHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 一些服务如果以很多形式发布 比如 hessian webservice 等 其中有spring mvc发布的话 可能牵涉到一些参数转型 的 最好继承该类
 * @author Administrator
 *
 */
public abstract class BaseMvcService {

	protected  Logger logger = LoggerFactory.getLogger(getClass());

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, EditorHolder.dateEditor);
        binder.registerCustomEditor(Integer.class, EditorHolder.integerEditor);
        binder.registerCustomEditor(Long.class, EditorHolder.longEditor);
        binder.registerCustomEditor(BigDecimal.class, EditorHolder.bigDecimalEditor);
    }

}
