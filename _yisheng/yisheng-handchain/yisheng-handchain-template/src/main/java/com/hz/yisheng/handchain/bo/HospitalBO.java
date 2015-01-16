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
import com.hz.yisheng.handchain.dao.HospitalMapper;
import com.hz.yisheng.handchain.orm.Hospital;
import com.hz.yisheng.webdata.SessionHolder;
@Service
@Transactional
public class HospitalBO {
	
	@Autowired
	private HospitalMapper hospitalMapper;
	
	public Page<Hospital> pageQuery(Page<Hospital> page,final Map<String, Object> queryMap){
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<Hospital>(){

			@Override
			public long count() {
				return hospitalMapper.count(queryMap);
			}

			@Override
			public List<Hospital> list() {
				return hospitalMapper.list(queryMap);
			}
			
		});
		List<Hospital> list = page.getResult();
		if (!list.isEmpty()) {
			List<Long> userIds = Lists.newArrayList();
			for (Hospital hb : list) {
				userIds.add(hb.getId());
			}
		}
		return page;
	}
	
	public Hospital select(Long id){
		return hospitalMapper.select(id);
	}
	@Transactional
	public void insert(Hospital hp){
		SessionHolder.prepareAdminLoginData(hp);
		hospitalMapper.insert(hp);
	}
	public void update(Hospital hp){
		hospitalMapper.update(hp);
	}
	public void delete(Long id){
		hospitalMapper.delete(id);
	}
	
	/**
	 * 查询所有的医院名称
	 * @param param
	 * @return
	 */
	public List<Hospital> queryHospitalName(Map<String,Object> param){
		return hospitalMapper.queryHospitalName(param);
	}

}
