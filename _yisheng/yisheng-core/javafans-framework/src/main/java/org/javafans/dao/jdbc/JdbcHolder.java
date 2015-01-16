package org.javafans.dao.jdbc;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.javafans.framework.spring.common.SpringContextHolder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.google.common.collect.Maps;

/**
 * jdbcAPI 提供类
 * @author ChenJunhui
 *
 */
public class JdbcHolder {

	/**
	 * jdbc 模板 缓存map key为spring bean中 各种数据源的名称
	 */
	private static Map<String,NamedParameterJdbcTemplate> jdbcTemplateMap = Maps.newHashMap();
	
	/**
	 * 根据数据源获取JDBC模板
	 * @param dataSource
	 * @return
	 */
	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String dataSourceBeanName){
		NamedParameterJdbcTemplate jdbcTemplate = jdbcTemplateMap.get(dataSourceBeanName);
		if(jdbcTemplate!=null){
			return jdbcTemplate;
		}
		DataSource dataSource = SpringContextHolder.getBean(dataSourceBeanName);
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);  
		jdbcTemplateMap.put(dataSourceBeanName, jdbcTemplate);
		return jdbcTemplate;
	}
	
	public static List<Map<String, Object>> doQuery(String dataSourceName,String sql,Map<String, Object> paramMap){
		NamedParameterJdbcTemplate jt = getNamedParameterJdbcTemplate(dataSourceName); 
		return jt.queryForList(sql, paramMap);
	}
	public static <T> List<T> doQueryForList(String dataSourceName,String sql,Map<String, Object> paramMap,Class<T> clazz){
		NamedParameterJdbcTemplate jt = getNamedParameterJdbcTemplate(dataSourceName); 
		return jt.queryForList(sql, paramMap, clazz);
	}
}
