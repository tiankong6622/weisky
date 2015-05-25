package com.hz.crf.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.hz.crf.model.orm.Attention;
/**
 * 会员关注的项目
 * @author weisky
 *
 * Jun 1, 2015
 */
@Service
public class AttentionService extends BaseService<Attention, Long>{
	
	private static final long serialVersionUID = -2352042926408422011L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<Attention> getEntityClass() {
		return Attention.class;
	}
	
	/**
	 * 新增加一个关注
	 * @param memberId
	 * @param productId
	 */
	public void addAttention(Long memberId, Long productId){
		Attention att = new Attention();
		att.setMemberId(memberId);
		att.setProductId(productId);
		att.setCt(System.currentTimeMillis());
		super.save(att);
	}
	
	/**
	 * 根据众筹项目ID  获取列表
	 * @param productId
	 * @return
	 */
	public List<Attention> findByProductId(Long productId){
		return super.findByField("productId", productId);
	}
	
	/**
	 * 根据会员ID  获取列表
	 * @param memeberId
	 * @return
	 */
	public List<Attention> findByMemeberId(Long memberId){
		return super.findByField("memberId", memberId);
	}

}
