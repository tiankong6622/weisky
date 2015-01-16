package com.hz.yisheng.apptemplate.dao;


import com.hz.yisheng.apptemplate.orm.Apply;

public interface ApplyMapper {

	public Apply select(Long id);
	
	public void update(Apply apply);
	
	public void insert(Apply apply);
	
	public void delete(Long id);
	
	public Long getId();
}
