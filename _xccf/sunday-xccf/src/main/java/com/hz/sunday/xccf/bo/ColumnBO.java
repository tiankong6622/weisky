package com.hz.sunday.xccf.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.sunday.xccf.dao.ColumnMapper;
import com.hz.sunday.xccf.orm.ColumnBean;

/**
 * 栏目管理
 * 
 * @author huanglei
 * @date 2015年4月13日
 * @version V1.0
 */
@Service
public class ColumnBO {

	@Autowired
	private ColumnMapper ColumnMapper;

	/**
	 * 分页查询数据
	 */
	public Page<ColumnBean> pageQuery(Page<ColumnBean> page,
			final Map<String, Object> queryMap) {
		return PageQueryUtils.pageQuery(page, queryMap,
				new PageQuery<ColumnBean>() {

					@Override
					public long count() {
						return ColumnMapper.findCount(queryMap);
					}

					@Override
					public List<ColumnBean> list() {
						return ColumnMapper.findAll(queryMap);
					}

				});
	}

	/**
	 * 根据ID，获取栏目信息
	 */
	public ColumnBean findById(Long id) {
		return ColumnMapper.findById(id);
	}

	/**
	 * 新增栏目信息
	 * 
	 * @param customer
	 */
	public void insert(ColumnBean column) {
		ColumnMapper.insert(column);
	}

	/**
	 * 更新栏目信息
	 * 
	 * @param customer
	 */
	public void update(ColumnBean column) {
		ColumnMapper.update(column);

	}

	/**
	 * 根据ID，删除栏目信息
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		ColumnMapper.deleteById(id);
	}

}
