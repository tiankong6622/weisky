package com.hz.yisheng.commondata.bo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.javafans.common.dto.Option;
import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.dao.CodesMapper;
import com.hz.yisheng.commondata.orm.Codes;

/**
 * 配置表相关业务层
 * 
 * @author ChenJunhui
 * 
 */
@Service
public class CodesBO {

	@Autowired
	private CodesMapper codesMapper;

	/**
	 * 分页查询codes
	 * 
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<Codes> pageQuery(Page<Codes> page, final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<Codes>() {
			@Override
			public List<Codes> list() {
				List<Codes> list = codesMapper.list(sqlMap);
				return list;
			}

			@Override
			public long count() {
				return codesMapper.count(sqlMap);
			}
		});
	}

	public List<Codes> list(Map<String, Object> map) {
		return codesMapper.list(map);
	}

	public void insert(Codes codes) {
		if (StringUtils.isBlank(codes.getRelObjectId())) {
			codes.setRelObjectId(Codes.DEFAULT_REL_OBJ_ID);
		}
		SessionHolder.prepareAdminAndProjectLoginData(codes);
		codesMapper.insert(codes);
	}

	public void update(Codes codes) {
		SessionHolder.prepareAdminAndProjectLoginData(codes);
		codesMapper.update(codes);
	}

	public int delete(Long id) {
		return codesMapper.delete(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public Codes getById(Long id) {
		return codesMapper.getById(id);
	}

	/**
	 * 根据type 查找到codes 的信息 并将 key-value 组装成 Option对象 返回
	 * 
	 * @param type
	 * @return
	 */
	public List<Option> getCodes(String type) {
		return getCodes(Codes.DEFAULT_REL_OBJ_ID, type);
	}

	/**
	 * 根据三个参数 查找到codes 的信息 并将 key-value 组装成 Option对象 返回
	 * 
	 * @param appKey
	 * @param relObjectId
	 * @param type
	 * @return
	 */
	public List<Option> getCodes(String relObjectId, String type) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("relObjectId", relObjectId);
		map.put("type", type);
		return codesMapper.getCodesByType(map);
	}

	/**
	 * 根据type和上次更新时间 获取更新列表
	 * 
	 * @param type
	 * @param date
	 * @return
	 */
	public List<Codes> getCodesByTypeAndUpdateTime(List<String> typeList, String date) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("typeList", typeList);
		map.put("date", date);
		return codesMapper.getCodesByTypeAndUpdateTime(map);
	}

	/**
	 * 根据type和上次更新时间 获取更新列表
	 * 
	 * @param type
	 * @param date
	 * @return
	 */
	public List<Codes> getCodesByTypeAndUpdateTime(String type, String date) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("type", type);
		map.put("date", date);
		return codesMapper.getCodesByTypeUpdateTime(map);
	}

}
