package com.hz.yisheng.apptemplate.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.apptemplate.dao.RelevanceMapper;
import com.hz.yisheng.apptemplate.dto.Relevance;
import com.hz.yisheng.apptemplate.orm.Apply;
import com.hz.yisheng.apptemplate.orm.Template;

@Service
@Transactional
public class RelevanceBO {

	@Autowired
	private RelevanceMapper relevanceMapper;
	
	/**
	 * 获得应用和模块列表
	 * @return
	 */
	public List<Relevance> getAll(){
		List<Relevance> list = new ArrayList<Relevance>();
		try {
			List<Long> appids = new ArrayList<Long>();
			List<Apply> alist = relevanceMapper.getApply();
			List<Template> tlist = relevanceMapper.getTemplate();
			if(alist.size()>0){
				for(Apply a : alist){
					Relevance r = new Relevance();
					r.set_parentId(0L);
					r.setId(a.getId());
					r.setName(a.getAppName());
					r.setUrl(a.getTdcAddress());
					r.setType(1);
					list.add(r);
					appids.add(r.getId());
				}
			}
			if(tlist.size()>0){
				for(Template t : tlist){
					Relevance r = new Relevance();
					r.set_parentId(t.getAppId());
					
					r.setTempId(t.getId().intValue());
					r.setName(t.getTemplateName());
					r.setUrl(t.getImplAddress());
					r.setType(0);
					list.add(r);
				}
			}
			
			if(list != null && list.size() > 0){
				
				for(int i=0; i < list.size(); i++){
					Relevance r = list.get(i);
					if(r.getType() == 0){//如果是功能
						Long n = 1l;
						while(true){
							if(appids.contains(new Long(n))){
								n++;
							}else{
								break;
							}
						}
						r.setId(n);
						appids.add(n);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
