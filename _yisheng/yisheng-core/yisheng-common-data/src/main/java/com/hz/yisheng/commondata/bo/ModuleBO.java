package com.hz.yisheng.commondata.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.dao.ModuleMapper;
import com.hz.yisheng.commondata.orm.Module;

/**
 * 功能模块相关业务层
 * @author GuoRui
 */
@Service
public class ModuleBO {

	@Autowired
	private ModuleMapper moduleMapper;
	
	public Page<Module> pageQuery(final Map<String, Object> sqlMap){
		return PageQueryUtils.pageQuery(sqlMap, new PageQuery<Module>() {
			@Override
			public List<Module> list(){
				List<Module> list = moduleMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return moduleMapper.count(sqlMap);
			}
		});
	}
	
	/**
	 * 获得全部模块
	 * @return
	 */
	public List<Module> getAll(){
		List< Module> list = moduleMapper.list(null);
		return list;
	}
	
	public Module findById(Long id){
		Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(1);
		sqlMap.put("id", id);
		List<Module> modules = moduleMapper.list(sqlMap);
		return modules.isEmpty()?null:modules.get(0);
	}
	
	public void insert(Module module){
		SessionHolder.prepareMemberLoginData(module);
		moduleMapper.insert(module);
	}
	
	public int update(Module module){
		SessionHolder.prepareMemberLoginData(module);
		return moduleMapper.update(module);
	}
	
	public int delete(Long id){
		return moduleMapper.delete(id);
	}

	public List<Module> getModule() {
		return moduleMapper.getModule();
	}
}
