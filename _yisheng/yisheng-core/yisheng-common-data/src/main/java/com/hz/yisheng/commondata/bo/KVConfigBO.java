package com.hz.yisheng.commondata.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.dao.KVConfigMapper;
import com.hz.yisheng.commondata.orm.KVConfig;

@Service
public class KVConfigBO {

	@Autowired
	private KVConfigMapper kvConfigMapper;
	
	
	public Page<KVConfig> pageQuery(Page<KVConfig> page,final Map<String,Object> sqlMap){
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<KVConfig>() {
			@Override
			public List<KVConfig> list(){
				List<KVConfig> list = kvConfigMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return kvConfigMapper.count(sqlMap);
			}
		});
	}
	
	public List<KVConfig> list(Map<String,Object> sqlMap){
		List<KVConfig> list = kvConfigMapper.list(sqlMap);
		return list;
	}
	
	public void insert(KVConfig config){
		if(StringUtils.isBlank(config.getAppKey())){
			config.setAppKey(KVConfig.DEFAULT_APP_KEY);
		}
		SessionHolder.prepareAdminAndProjectLoginData(config);
		kvConfigMapper.insert(config);
	}
	
	public int update(KVConfig config){
		SessionHolder.prepareAdminAndProjectLoginData(config);
		return kvConfigMapper.update(config);
	};
	
	public int delete(Long id){
		return kvConfigMapper.delete(id);
	}
	
	/**
	 * 根据key获得默认配置的值
	 * @param key
	 * @return
	 */
	public String getConfigValue(String key){
		return getConfigValue(KVConfig.DEFAULT_APP_KEY, key);
	}
	
	/**
	 * 根据appkey 和key 获取配置的值
	 * @param appkey
	 * @param key
	 * @return
	 */
	public String getConfigValue(String appkey,String key){
		List<KVConfig> list = list(new HashMap<String,Object>());
		for(KVConfig config:list){
			if(StringUtils.equals(appkey, config.getAppKey()) && StringUtils.equals(key, config.getKey()))
				return config.getValue();
		}
		return null;
	}
	
	public KVConfig findById(Long id) {
		return kvConfigMapper.findById(id);
	}
	
}
