package org.itboys.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminPost;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

/**
 * 职务service
 * @author WeiSky
 *
 */
@Service
public class AdminPostService extends BaseAdminService<AdminPost, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3599928484031949690L;
	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminPost> getEntityClass() {
		return AdminPost.class;
	}
	/**
	 * 获取所有的post
	 * @return
	 */
	public List<AdminPost> getAll(){
		List<AdminPost> list = ds.createQuery(getEntityClass()).filter("isDeleted", 0).asList();
		return list;
	}
	
	/**
	 * 删除  isDeleted标为1
	 * @param id
	 */
	public void deletePost(Long id){
		update(id, "isDeleted",1);
	}
	
}
