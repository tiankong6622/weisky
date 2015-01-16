package com.hz.yisheng.commondata.bo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.dao.ParamConfigTemplateMapper;
import com.hz.yisheng.commondata.orm.Codes;
import com.hz.yisheng.commondata.orm.ParamConfigTemplate;

@Service
public class ParamConfigTemplateBO {

	@Autowired
	private ParamConfigTemplateMapper paramConfigTemplateMapper;

	public Page<ParamConfigTemplate> pageQuery(Page<ParamConfigTemplate> page,final Map<String,Object> sqlMap){
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ParamConfigTemplate>() {
			@Override
			public List<ParamConfigTemplate> list(){
				List<ParamConfigTemplate> list = paramConfigTemplateMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return paramConfigTemplateMapper.count(sqlMap);
			}
		});
	}
	
	public void insert(ParamConfigTemplate pconfig){
		if(StringUtils.isBlank(pconfig.getRelObjectId())){
			pconfig.setRelObjectId(Codes.DEFAULT_REL_OBJ_ID);
		}
		SessionHolder.prepareAdminAndProjectLoginData(pconfig);
		paramConfigTemplateMapper.insert(pconfig);
	}
	
	public void update(ParamConfigTemplate pconfig){
		SessionHolder.prepareAdminAndProjectLoginData(pconfig);
		paramConfigTemplateMapper.update(pconfig);
	}
	
	public int delete(Long id){
		return paramConfigTemplateMapper.delete(id);
	}
	
	
	public ParamConfigTemplate findById(Long  id){
		return paramConfigTemplateMapper.getById(id);
	}
	
	public List<ParamConfigTemplate> getCodes(String relObjectId,String type){
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("relObjectId", relObjectId);
		map.put("type", type);
		return paramConfigTemplateMapper.getCodesByType(map);
	}
	
}
