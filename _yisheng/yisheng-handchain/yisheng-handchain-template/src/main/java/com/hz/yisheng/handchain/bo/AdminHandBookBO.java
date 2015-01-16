package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hz.yisheng.handchain.dao.AdminHandBookMapper;
import com.hz.yisheng.handchain.orm.HandBook;
import com.hz.yisheng.handchain.orm.Hospital;
import com.hz.yisheng.webdata.SessionHolder;

@Service
@Transactional
public class AdminHandBookBO {
	@Autowired
	private AdminHandBookMapper adminHandBookMapper;


	/**
	 * 分页查询
	 * 
	 * @param id
	 * @return
	 */
	public Page<HandBook> pageQuery(Page<HandBook> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<HandBook>() {
			@Override
			public long count() {
				return adminHandBookMapper.count(queryMap);
			}

			@Override
			public List<HandBook> list() {
				return adminHandBookMapper.list(queryMap);
			}
		});
		List<HandBook> list = page.getResult();
		if (!list.isEmpty()) {
			List<Long> userIds = Lists.newArrayList();
			for (HandBook hb : list) {
				userIds.add(hb.getId());
			}
		}
		return page;
	}
	
	/**
	 * 新增一个管理后台用户(医院指南)
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void insert(HandBook hb) {
//		hb.setTitle(hb.getTitle());
//		hb.setSubtitle(hb.getSubtitle());
//		hb.setSummary(hb.getSummary());
		SessionHolder.prepareAdminLoginData(hb);
		adminHandBookMapper.insert(hb);
	}

	
	public int delete(Long id) {
		int result = adminHandBookMapper.delete(id);
		return result;
	}
	/**
	 * 修改后台用户
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public void update(HandBook hb) {
		/*hb.setTitle(hb.getTitle());
		hb.setSubtitle(hb.getSubtitle());
		hb.setSummary(hb.getSummary());
		SessionHolder.prepareAdminLoginData(hb);*/
		adminHandBookMapper.update(hb);
	}
	
	/**
	 * 根据ID获取管理用户信息
	 * 
	 * @param id
	 * @return
	 */
	public HandBook select(Long id){
		return adminHandBookMapper.select(id);
	}
	public List<Hospital> getAll(){
		return adminHandBookMapper.getAll();
	}
	
	/**
	 * 根据id查询医院指南信息
	 * @param id
	 * @return
	 */
	public HandBook queryById(Long id){
		return adminHandBookMapper.queryById(id);
	}
}

