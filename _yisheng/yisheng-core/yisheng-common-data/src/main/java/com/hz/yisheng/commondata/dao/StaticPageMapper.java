package com.hz.yisheng.commondata.dao;

import java.util.List;

import com.hz.yisheng.commondata.orm.StaticPage;

public interface StaticPageMapper {

	public List<StaticPage> list();
	
	public StaticPage getById(Long id);
	
	public StaticPage getByTitle(String title);
	
	public void del(Long id);
	
	public void insert(StaticPage page);
	
	public void update(StaticPage page);
}
