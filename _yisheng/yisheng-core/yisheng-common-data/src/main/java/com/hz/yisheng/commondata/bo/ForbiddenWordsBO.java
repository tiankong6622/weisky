package com.hz.yisheng.commondata.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.javafans.resources.ResourceConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.hz.yisheng.commondata.dao.ForbiddenWordsMapper;
import com.hz.yisheng.commondata.orm.ForbiddenWords;
import com.hz.yisheng.commondata.tools.KeywordFilter;

/**
 * 违禁词相关业务层
 * @author Liugaunjun
 */
@Service
public class ForbiddenWordsBO implements InitializingBean{
	
	/**
	 * 初始化违禁词
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		//一般前端网站 如果projectId配置在了resource-config.xml里的话 启动的时候就初始一下违禁词
		String projectId = ResourceConfig.getSysConfig("projectId");
		if(NumberUtils.isDigits(projectId)){
			initOrResetForbiddenKeyword(Long.parseLong(projectId));
		}
	}
	
	@Autowired
	private ForbiddenWordsMapper forbiddenWordsMapper;
	
	
	public Page<ForbiddenWords> pageQuery(Page<ForbiddenWords> page,final Map<String,Object> sqlMap){
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ForbiddenWords>() {
			@Override
			public List<ForbiddenWords> list(){
				List<ForbiddenWords> list = forbiddenWordsMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return forbiddenWordsMapper.count(sqlMap);
			}
		});
	}
	
	/**
	 * 初始或重新生成关键词
	 */
	public void initOrResetForbiddenKeyword(Long projectId){
		Map<String,Object> sqlMap = new HashMap<String, Object>(1);
		sqlMap.put("projectId", projectId);
		List<ForbiddenWords> list = forbiddenWordsMapper.list(sqlMap);
		List<String> keywords = Lists.transform(list, new Function<ForbiddenWords, String>() {
			@Override
			public String apply(ForbiddenWords input) {
				return input.getWord();
			}
		});
		KeywordFilter.initKeywords(keywords);
	}
	
	public ForbiddenWords getById(Long id){
		return forbiddenWordsMapper.getById(id);
	}
	
	
	public void insert(ForbiddenWords entity){
		forbiddenWordsMapper.insert(entity);
	}
	
	public void update(ForbiddenWords entity){
		forbiddenWordsMapper.update(entity);
	}
	
	public int delete(Long id){
		return forbiddenWordsMapper.delete(id);
	}


}
