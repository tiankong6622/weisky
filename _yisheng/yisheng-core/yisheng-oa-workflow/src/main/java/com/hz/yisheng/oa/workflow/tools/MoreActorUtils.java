package com.hz.yisheng.oa.workflow.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.javafans.dao.jdbc.JdbcHolder;
import org.javafans.framework.spring.common.SpringContextHolder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.oa.workflow.bo.TempNodeSqlBO;
import com.hz.yisheng.oa.workflow.orm.TempNodeSql;

/**
 * 多人查询帮助类
 * @author WeiSky
 *
 */
public class MoreActorUtils {

	/**
	 * 打项目用到的多处理人的动态SQL
	 */
	private static String MORE_ACTOR_SQL = null;
	private static Map<Long,String> projectSQLMap = new HashMap<Long, String>();
	static{
		MORE_ACTOR_SQL=getDynamicSql();
		builderProjectSql();
	}
	
	/**
	 * 获取多人查询sql
	 * @return
	 */
	public static String getMoreActorSql(){
		return MORE_ACTOR_SQL;
	}
	
	

	/**
	 * 组装sql动态查询人 给独立项目用
	 * @param actor
	 * @return
	 */
	public static String getMoreActorSql(long actor){
		return StringUtils.replace(MORE_ACTOR_SQL,"{actor}",String.valueOf(actor));
	}
	
	/**
	 * 组装sql 给project项目用
	 * @param projectId
	 * @param actor
	 * @return
	 */
	public static String getProjectMoreActorSql(long projectId,long actor){
		String sql = projectSQLMap.get(projectId);
		if(sql!=null){
			return StringUtils.replace(sql,"{actor}",String.valueOf(actor)); 
		}
		return null;
	}
	
	/**
	 * 节点sql有修改后新增后 执行改方法
	 */
	public static void reSetMoreActorSql(long projectId){
		if(projectId==0L){
			String sql =  getDynamicSql();
			MORE_ACTOR_SQL=sql;
		}else{
			TempNodeSqlBO tempNodeSqlBO = SpringContextHolder.getBean(TempNodeSqlBO.class);
			Map<String, Object> sqlMap = new HashMap<String, Object>(2);
			sqlMap.put("projectId", projectId);
			List<TempNodeSql> list = tempNodeSqlBO.list(sqlMap);
			projectSQLMap.put(projectId, builderSQL(list));
		}
	}
	
	/**
	 * 判断一个节点动态sql里是否存在一个人
	 * @param actor
	 * @param nodeId
	 * @return
	 */
	public  static boolean isInMoreSql(Long actor,long nodeId){
		TempNodeSqlBO tempNodeSqlBO = SpringContextHolder.getBean(TempNodeSqlBO.class);
		String sql = tempNodeSqlBO.getByNodId(nodeId);
		if(StringUtils.isNotBlank(sql)){
			//KVConfigBO kVConfigBO = SpringContextHolder.getBean(KVConfigBO.class);
			//String dataSource = kVConfigBO.getConfigValue("dataSource");
			String dataSource = "dataSource";
			if(StringUtils.isNotBlank(dataSource)){
				List<Long> list =  JdbcHolder.doQueryForList(dataSource, sql, new HashMap<String, Object>(0),Long.class);
				return list!=null && list.contains(actor); 
			}
		}
		return false;
	}
	
	/**
	 * 拼接这种sql
	 * (
			(current_node = 153  and 
				17 in 
						(
							select 
							user_id
							 from 
							admin_user_org_post
							where post_id=63  and is_deleted=1
						)
			)
			OR
			(current_node = 153  and 
					178 in 
							(
								select 
								user_id
								 from 
								admin_user_org_post
								where post_id=63  and is_deleted=1
							)
			)
				    
	  
	)		
	 * @return
	 */
	public static String getDynamicSql(){
		TempNodeSqlBO tempNodeSqlBO = SpringContextHolder.getBean(TempNodeSqlBO.class);
		Map<String, Object> sqlMap = new HashMap<String, Object>(2);
		sqlMap.put("projectId", 0L);
		List<TempNodeSql> list = tempNodeSqlBO.list(sqlMap);
		if(list.isEmpty()){
			return null;
		}
		return builderSQL(list);
	}

	/**
	 * 构建项目的SQL
	 */
	private static void builderProjectSql() {
		TempNodeSqlBO tempNodeSqlBO = SpringContextHolder.getBean(TempNodeSqlBO.class);
		List<TempNodeSql> list = tempNodeSqlBO.list(new HashMap<String, Object>(0));
		if(list==null){
			return ;
		}
		Map<Long, List<TempNodeSql>> projectNodeSqlMap = Maps.newHashMap();
		for(TempNodeSql tns:list){
			if(tns.getProjectId()>0L){
				if(projectNodeSqlMap.containsKey(tns.getProjectId())){
					projectNodeSqlMap.get(tns.getProjectId()).add(tns);
				}else{
					List<TempNodeSql> tnsList = Lists.newArrayList(tns);
					projectNodeSqlMap.put(tns.getProjectId(), tnsList);
				}
			}
		}
		for(Iterator<Entry<Long, List<TempNodeSql>>> iter = projectNodeSqlMap.entrySet().iterator();iter.hasNext();){
			Entry<Long, List<TempNodeSql>> entry = iter.next();
			projectSQLMap.put(entry.getKey(), builderSQL(entry.getValue()));
		}
	}
	
	private static String builderSQL(List<TempNodeSql> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("( current_node in (");
		int i=0;
		for(TempNodeSql sql:list){
			if(i>0){
				sb.append(",");
			}
			sb.append(sql.getNodeId());
			i++;
		}
		sb.append(") and ");
		i=0;
		for(TempNodeSql sql:list){
			if(i>0){
				sb.append(") or ");
			}
			sb.append("(current_node = "+sql.getNodeId()+" and ");
			sb.append("{actor} in (");
			sb.append(sql.getNodeSql());
			sb.append(")");
			i++;
		}
		sb.append("))");
		return sb.toString();
	}
	
	
}
