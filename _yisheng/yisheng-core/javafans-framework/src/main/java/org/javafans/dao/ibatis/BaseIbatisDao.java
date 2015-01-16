package org.javafans.dao.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javafans.common.utils.number.ToNumberUtils;
import org.javafans.dto.page.Page;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * ibatis 相关通用DAO基类
 * @author ChenJunHui
 */
public class BaseIbatisDao extends SqlMapClientDaoSupport {
	
	public static final String DOT = ".";
	
	/**
	 * 根据 statementName 加载实体
	 * @param nameSpace
	 * @param statementName
	 * @return
	 */
	public Object get(String statementName,Object parameterObject){
		  Object obj = getSqlMapClientTemplate().queryForObject(statementName, parameterObject);
		  return obj;
	}
	

	/**
	 * 根据 statementName 加载实体
	 * @param nameSpace
	 * @param statementName
	 * @return
	 */
	public Object get(String nameSpace,String statementName,Object parameterObject){
		  Object obj = getSqlMapClientTemplate().queryForObject(nameSpace+DOT+statementName, parameterObject);
		  return obj;
	}

	/**
	 * 根据 statementName 插入对象
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public void insert(String statementName,Object parameterObject){
		  getSqlMapClientTemplate().insert(statementName, parameterObject);
	}
	
	/**
	 * 根据 statementName 插入对象 命名空间格式
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public void insert(String nameSpace,String statementName,Object parameterObject){
		  getSqlMapClientTemplate().insert(nameSpace+DOT+statementName, parameterObject);
	}
	
	/**
	 * 根据 statementName 更新  delete操作也可以使用该方法  命名空间格式
	 * @param nameSpace
	 * @param statementName
	 * @return
	 */
	public int update(String nameSpace,String statementName){
		return getSqlMapClientTemplate().update(nameSpace+DOT+statementName);
	}
	
	/**
	 * 根据 statementName 更新  delete操作也可以使用该方法
	 * @param nameSpace
	 * @param statementName
	 * @return
	 */
	public int update(String statementName){
		return getSqlMapClientTemplate().update(statementName);
	}
	
	/**
	 * 根据 statementName 更新  delete操作也可以使用该方法
	 * @param nameSpace
	 * @param statementName
	 * @return
	 */
	public int update(String statementName,Object parameterObject){
		return getSqlMapClientTemplate().update(statementName, parameterObject);
	}
	
	/**
	 * 根据 statementName 更新  delete操作也可以使用该方法  命名空间格式
	 * @param nameSpace
	 * @param statementName
	 * @return
	 */
	public int update(String nameSpace,String statementName,Object parameterObject){
		return getSqlMapClientTemplate().update(nameSpace+DOT+statementName, parameterObject);
	}
	
	/**
	 * 根据 statementName count 数量
	 * @param statementName
	 * @return
	 */
	public Long count(String statementName){
		return ToNumberUtils.getLongValue(getSqlMapClientTemplate().queryForObject(statementName));
	}
	
	/**
	 * 根据 statementName count 数量 命名空间格式
	 * @param statementName
	 * @return
	 */
	public Long count(String nameSpace,String statementName){
		return ToNumberUtils.getLongValue(getSqlMapClientTemplate().queryForObject(nameSpace+DOT+statementName));
	}
	
	/**
	 * 根据 statementName count 数量
	 * @param statementName
	 * @return
	 */
	public Long count(String statementName,Object parameterObject){
		return ToNumberUtils.getLongValue(getSqlMapClientTemplate().queryForObject(statementName,parameterObject));
	}
	
	/**
	 * 根据 statementName count 数量 命名空间格式
	 * @param statementName
	 * @return
	 */
	public Long count(String nameSpace,String statementName,Object parameterObject){
		return ToNumberUtils.getLongValue(getSqlMapClientTemplate().queryForObject(nameSpace+DOT+statementName));
	}
	
	/**
	 * 根据 statementName 查询列表 
	 * @param statementName
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public List queryForList(String statementName){
		return getSqlMapClientTemplate().queryForList(statementName);
	}
	
	/**
	 * 根据 statementName 查询列表 命名空间格式
	 * @param nameSpace
	 * @param statementName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String nameSpace,String statementName){
		return getSqlMapClientTemplate().queryForList(nameSpace+DOT+statementName);
	}
	
	/**
	 * 根据 statementName 查询列表 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName,Object parameterObject){
		return getSqlMapClientTemplate().queryForList(statementName,parameterObject);
	}
	
	/**
	 * 根据 statementName 查询列表 命名空间格式
	 * @param nameSpace
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String nameSpace,String statementName,Object parameterObject){
		return getSqlMapClientTemplate().queryForList(nameSpace+DOT+statementName,parameterObject);
	}
	
	public static final String ROW_START="rowStart";
	public static final String PAGE_SIZE="pageSize";
	
	/**
	 * 分页查询
	 * @param countStatementName count sql 的 StatementName
	 * @param statementName 查询列表 sql 的 statementName
	 * @param page
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> queryForPage(String countStatementName, String statementName,Page page,Map<String,Object> paramMap){
		Long count = this.count(countStatementName, paramMap);
		Long first = page.getFirst();
		if(first!=null && first>=count){
			page.setResult(new ArrayList<T>());
		}
		if(first==null || first<=1){
			first = 1L;
		}
		paramMap.put(ROW_START, first-1L);
		paramMap.put(PAGE_SIZE, page.getPageSize());
		page.setTotalCount(count);
		page.setResult(queryForList(statementName, paramMap));
		return page;
	}
	
	/**
	 * 分页查询 带命名空间的
	 * @param countStatementName count sql 的 StatementName
	 * @param statementName 查询列表 sql 的 statementName
	 * @param page
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> queryForPage(String nameSpace ,String countStatementName,String statementName,Page page,Map<String,Object> paramMap){
		return this.queryForPage(nameSpace+DOT+countStatementName, nameSpace+DOT+statementName, page, paramMap);
	}
	
}
