package com.hz.yisheng.handchain.bo;

import java.util.List;
import java.util.Map;

import org.javafans.dto.page.Page;
import org.javafans.dto.page.PageQuery;
import org.javafans.dto.page.PageQueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hz.yisheng.handchain.dao.CustomerChildMapper;
import com.hz.yisheng.handchain.dao.CustomerDtoMapper;
import com.hz.yisheng.handchain.dao.CustomerMapper;
import com.hz.yisheng.handchain.dto.CustomerDto;
import com.hz.yisheng.handchain.orm.CustomerChild;

@Service
@Transactional
public class CustomerChildBO {
	@Autowired
	private CustomerChildMapper  customerChildMapper;
	@Autowired
	private CustomerDtoMapper  customerDtoMapper;
	@Autowired
	private CustomerMapper  customerMapper;
	/**
	 * 插入
	 * @param customerChild
	 */
	public void insert(CustomerChild customerChild){
		customerChildMapper.insert(customerChild);
	}
	
	/**
	 * 修改
	 * @param customerChild
	 */
	public void update(CustomerChild customerChild){
		customerChildMapper.update(customerChild);
	}
	
	/**
	 * 获取所有家长与孩子信息
	 * @param queryparam
	 * @return
	 */
	public List<CustomerChild> list(Map<String,Object> queryparam){
		return customerChildMapper.list(queryparam);
	}
	/**
	 * 获取中间表信息
	 * @param queryparam
	 * @return
	 */
	public List<CustomerChild> contactList(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("customerId", customerId);
		return customerChildMapper.contactList(queryParam);
	}
	/**
	 * 获取记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param){
		return customerDtoMapper.count(param);
	}
	/**
	 * 根据customerid查询中间表信息
	 * @param cid
	 * @return
	 */
	public CustomerChild getCustomerChildById(Long cid){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", cid);
		List<CustomerChild> list = customerChildMapper.list(queryParam);
		return (list.isEmpty()?null:list.get(0));
	}
	
	/**
	 * 根据家长id查询孩子信息
	 * @param queryparam
	 * @return
	 */
	public List<CustomerDto> listById(Map<String,Object> queryparam){
		return customerDtoMapper.listById(queryparam);
	}
	
	/**
	 * 查询所有家长与孩子的信息
	 * @param queryparam
	 * @return
	 */
	public List<CustomerDto> getParentAndChild(Map<String,Object> queryparam){
		List<CustomerDto> customerdto = customerDtoMapper.list(queryparam);
		for(int i=0;i<customerdto.size();i++){
			queryparam.put("id",customerdto.get(i).getId());
			List<CustomerDto> customerDto2 = listById(queryparam);
			for(int j=0;j<customerDto2.size();j++){
				customerDto2.get(j).setParentId(customerdto.get(i).getId());
				customerDto2.get(j).set_parentId(customerdto.get(i).getId());
			}
			customerdto.get(i).setChildren(customerDto2);
		}
		return customerdto;
		
	}
	
/*	public List<CustomerDto> getParentAndChild2(Map<String,Object> queryparam){
		List<CustomerDto> customerdto = new ArrayList<CustomerDto>();
		List<CustomerDto> customerdto1 = customerDtoMapper.list(queryparam);
		//若家长信息记录不为空
		if(!customerdto1.isEmpty()){
			customerdto = customerdto1;
			for(int i=0;i<customerdto1.size();i++){
				queryparam.put("id",customerdto1.get(i).getId());
				List<CustomerDto> customerDto2 = listById(queryparam);
				for(int j=0;j<customerDto2.size();j++){
					customerDto2.get(j).setParentId(customerdto.get(i).getId());
					customerDto2.get(j).set_parentId(customerdto.get(i).getId());
				}
				customerdto.get(i).setChildren(customerDto2);
			}
		}else{
			List<CustomerDto> customerDto2 = customerDtoMapper.childList(queryparam);
			//家长信息为空，孩子记录不为空
			if(!customerDto2.isEmpty()){
				for(int i=0;i<customerDto2.size();i++){
					Map<String,Object> param = Maps.newHashMapWithExpectedSize(1);
					param.put("id",customerDto2.get(i).getCustomerId());
					//根据customerid查询该孩子的家长
					List<CustomerDto> customerdto3 = customerDtoMapper.list(param);
					if(!customerdto3.isEmpty()){
						customerdto = customerdto3;
						for(int k=0;i<customerdto3.size();i++){
							queryparam.put("id",customerdto3.get(k).getId());
							List<CustomerDto> customerDto4 = listById(queryparam);
							for(int j=0;j<customerDto4.size();j++){
								customerDto4.get(j).setParentId(customerDto2.get(i).getCustomerId());
								customerDto4.get(j).set_parentId(customerDto2.get(i).getCustomerId());
							}
							customerdto.get(i).setChildren(customerDto4);
						}
					}
				}
			}
		}
		 return customerdto;
		
	}*/
	
	
	
	/**
	 * 分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<CustomerDto> pageQuery(Page<CustomerDto> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<CustomerDto>() {
			@Override
			public long count() {
				return customerDtoMapper.count(queryMap);
			}
			@Override
			public List<CustomerDto> list() {
				return getParentAndChild(queryMap);
			}
		});
		return page;
	}
	
	/**
	 * 根据Id获取家长与孩子的详细信息
	 * @param customerId
	 * @return
	 */
	public CustomerDto getCustomerAndChildById(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", customerId);
		List<CustomerDto> customerDto= getParentAndChild(queryParam);
		return (customerDto.isEmpty()?null:customerDto.get(0));
	}
	
	public List<CustomerChild> queryByCustomerId(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("customerId", customerId);
		List<CustomerChild> cc = customerChildMapper.queryByCustomerId(queryParam);
		return cc;
		
	}
}
