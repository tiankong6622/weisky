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
import com.hz.yisheng.handchain.dao.CustomerDeviceMapper;
import com.hz.yisheng.handchain.orm.CustomerDevice;

@Service
@Transactional
public class CustomerDeviceBO {
	@Autowired
	private CustomerDeviceMapper customerdeviceMapper;
	
	/**
	 * 客户设备插入操作
	 * @param customerDevice
	 */
	public void insert(CustomerDevice customerDevice){
		customerdeviceMapper.insert(customerDevice);
	}
	
	/**
	 * 查询客户设备信息
	 * @param queryParam
	 * @return
	 */
	public List<CustomerDevice> list(Map<String,Object> queryParam){
		return customerdeviceMapper.list(queryParam);
	}
	
	/**
	 * 根据设备id查询该客户的设备
	 * @param customerId
	 * @return
	 */
	public CustomerDevice getCustomerDeviceById(Long deviceId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("id", deviceId);
		List<CustomerDevice> list = customerdeviceMapper.list(queryParam);
		return (list.isEmpty()?null:list.get(0));
	}
	
	/**
	 * 根据客户id查询该客户的设备(一个客户可以有多个设备)
	 * @param customerId
	 * @return
	 */
	public List<CustomerDevice> getCustomerDeviceByCustomerId(Long customerId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("customerId", customerId);
		List<CustomerDevice> list = customerdeviceMapper.list(queryParam);
		return (list.isEmpty()?null:list);
	}
	/**
	 * 客户设备的个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param){
		return customerdeviceMapper.count(param);
	}
	/**
	 * 分页查询
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<CustomerDevice> pageQuery(Page<CustomerDevice> page, final Map<String, Object> queryMap) {
		page = PageQueryUtils.pageQuery(page, queryMap, new PageQuery<CustomerDevice>() {
			@Override
			public long count() {
				return customerdeviceMapper.count(queryMap);
			}
			@Override
			public List<CustomerDevice> list() {
				return customerdeviceMapper.list(queryMap);
			}
		});
		return page;
	}
	
	/**
	 * 根据设备编号查看该设备编号的使用情况
	 * @param num
	 * @return
	 */
	public List<CustomerDevice> deviceUse(String num){
		return customerdeviceMapper.deviceUse(num);
		
	}
}
