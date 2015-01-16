package com.hz.yisheng.commondata.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.yisheng.webdata.SessionHolder;
import com.hz.yisheng.commondata.dao.StaticPageMapper;
import com.hz.yisheng.commondata.orm.StaticPage;

@Service
public class StaticPageBO{
	@Autowired
	private StaticPageMapper staticPageMapper;
	
	public List<StaticPage> list(){
		return staticPageMapper.list();
	}
	
	public StaticPage getById(Long id){
		StaticPage staticPage = staticPageMapper.getById(id);
		
		return staticPage;
	}
	//existTitle
	public StaticPage getByTitle(String title){
		StaticPage staticPage = staticPageMapper.getByTitle(title);
		
		return staticPage;
	}
	
	public void del(Long id){
		staticPageMapper.del(id);
	}
	
	public void insert(StaticPage page){
		page.setProjectId(0l);
		page.setCreator(SessionHolder.getAdminUserId());
		page.setUpdater(SessionHolder.getAdminUserId());
		staticPageMapper.insert(page);
	}
	
	public void update(StaticPage page){
		page.setUpdater(SessionHolder.getAdminUserId());		
		staticPageMapper.update(page);
	}
}