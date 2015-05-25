package com.hz.sunday.getui.controller;

import javax.servlet.http.HttpServletResponse;

import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hz.sunday.getui.entity.TokenRecord;
import com.hz.sunday.getui.services.TokenRecordService;
import com.hz.sunday.getui.utils.ResultDOHelper;

@RestController
@RequestMapping("/getui")
public class TokenRecordController extends BaseController
{
	@Autowired
	private TokenRecordService tokenRecordService;

	@RequestMapping("/saveToken")
	public void save(@ModelAttribute TokenRecord tokenRecord,
			HttpServletResponse response)
	{
		try
		{
			// 首先判断数据库里有没有这条记录(未冻结)
			TokenRecord isExists = tokenRecordService.isExists(tokenRecord);
			
			if (isExists != null)
			{
				if (!isExists.getToken().equals(tokenRecord.getToken()))
				{	
					tokenRecordService.delete(isExists.getId());
					tokenRecordService.save(tokenRecord);
				}
			}else {
				tokenRecordService.save(tokenRecord);
			}
			AjaxUtils.renderJson(response, 
					ResultDOHelper.getErrorResultDO(0, "success!"));
		} catch (Exception e)
		{
			logger.error("getCategory error!", e);
			AjaxUtils.renderJson(response,
					ResultDOHelper.getErrorResultDO(-1, "error!"));
		}
	}

}
