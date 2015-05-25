package com.hz.crf.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.springframework.stereotype.Service;

import com.hz.crf.model.CrowdConstants;
import com.hz.crf.model.orm.Address;
/**
 * 收货地址
 * @author weisky
 *
 * May 28, 2015
 */
@Service
public class AddressService extends BaseService<Address, Long>{
	
	private static final long serialVersionUID = 3713324026975670477L;
	
	@Resource(name = "baseMongoDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<Address> getEntityClass() {
		return Address.class;
	}
	
	/**
	 * 我的收货地址列表
	 * @param memberId
	 * @return
	 */
	public List<Address> findByMemberId(Long memberId){
		return super.findByField("memberId", memberId);
	}
	
	/**
	 * 获取一条值
	 * @param id
	 * @return
	 */
	public Address findById(Long id){
		return super.getByField("id", id);
	}
	
	/**
	 * 把某一条地址设为默认地址
	 * @param memberId
	 * @param addressId
	 * @return
	 */
	public boolean setDefault(Long memberId, Long addressId){
		try{
			List<Address> list = findByMemberId(memberId);
			/*
			 * 除了要设置的地址之外，把其他的地址设为非默认
			 */
			for(Address add : list){
				if(add.getId() != addressId){
					add.setDefaultAddre(CrowdConstants.Address.DEFAULT_NO);
				}else{
					add.setDefaultAddre(CrowdConstants.Address.DEFAULT_YES);
				}
			}
			super.batchSave(list);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("AddressService.setDefault", e);
			return false;
		}
		return true;
	}
	
}
