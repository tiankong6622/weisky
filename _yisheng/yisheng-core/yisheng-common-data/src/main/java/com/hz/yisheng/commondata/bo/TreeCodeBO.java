package com.hz.yisheng.commondata.bo;

import java.util.List;
import java.util.Map;

import org.javafans.common.utils.collection.CommonCollectionUtils;
import org.javafans.common.utils.collection.FetchCondition;
import org.javafans.dto.login.LoginHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.webdata.WebConstants;
import com.hz.yisheng.commondata.dao.TreeCodeMapper;
import com.hz.yisheng.commondata.orm.TreeCode;

@Service
public class TreeCodeBO {
	
	public static final String PATH_SPLIT = "/";

	@Autowired
	private TreeCodeMapper treeCodeMapper;
	
	/**
	 * 获取全部菜单
	 * @return
	 */
	public List<TreeCode> getAllCategorys(Long projectId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("projectId", projectId);
		return treeCodeMapper.list(queryParam);
	}
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<TreeCode> list(Map<String, Object> map){
		return treeCodeMapper.list(map);
	}
	
	
	/**
	 * 获取所有一级类目菜单
	 * @return
	 */
	public List<TreeCode> getRootCategorys(Long projectId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("parentId", 0l);
		List<TreeCode> list = treeCodeMapper.list(queryParam);
		return list;
	}
	
	/**
	 * 获取所有一级类目菜单
	 * @return
	 */
	public List<TreeCode> getRootCategorys(List<TreeCode> all){
		List<TreeCode> list = CommonCollectionUtils.filterCollection(all, new FetchCondition<TreeCode>() {
			@Override
			public boolean needFetch(TreeCode t) {
				return t.getParentId()==null || t.getParentId().equals(0L);
			}
		});
		return list;
	}
	
	/**
	 * 按list的顺序取出一个分类的祖先到自己的 集合
	 * @param catId
	 * @return
	 */
	public List<TreeCode> getFullPath(Long id){
		TreeCode treeCode = getTreeCode(id);
		if(treeCode==null){
			return Lists.newArrayListWithCapacity(0);
		}else if(treeCode.getParentId().equals(0L)){
			List<TreeCode> list = Lists.newArrayListWithCapacity(1);
			list.add(treeCode);
			return list;
		}
		return null;
	}
	
	/**
	 * 根据ID加载菜单对象
	 * @param id
	 * @return
	 */
	public TreeCode getTreeCode(Long id){
		return treeCodeMapper.getById(id);
	}
	
	/**
	 * 根据ID加载菜单对象
	 * @param id
	 * @return
	 */
	public List<TreeCode> getChilidsByParentid(Long parentId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("parentId", parentId);
		List<TreeCode> list = treeCodeMapper.list(queryParam);
		return list;
	}
	
	/*
	 * 添加分类
	 */
	public void insert(TreeCode entity){
		if(entity.getParentId()==null){
			entity.setParentId(0L);
		}
		LoginHolder.prepareLoginMessage(entity, WebConstants.ALL_USER_SESSIONKEY);
		if(entity.getParentId()==0L){			
			treeCodeMapper.insert(entity);
			
		}else{
			treeCodeMapper.insert(entity);
		}
	}

	
	/*
	 * 修改产品分类
	 */
	public int update(TreeCode entity){
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);//组装当前登入者信息
		return treeCodeMapper.update(entity);
	}
	
	/*
	 * 删除产品分类
	 */
	public int delete(Long id){
		int result = treeCodeMapper.delete(id);
		return result;
	}
}
