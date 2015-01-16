package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.dto.CustomerDto;

public interface CustomerDtoMapper {
	
	/**
	 * 查询所有家长的信息
	 * @param queryparam
	 * @return
	 */
	public List<CustomerDto> list(Map<String,Object> queryparam);
	
	/**
	 * 根据家长id查询孩子信息
	 * @param queryParam
	 * @return
	 */
	public List<CustomerDto> listById(Map<String,Object> queryParam);
	/**
	 * 记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);
	
	/**
	 * 查询所有孩子的信息
	 * @param param
	 * @return
	 */
	public List<CustomerDto> childList(Map<String,Object> param);
	
	/**
	 * 家长详情列表信息
	 * @param queryparam
	 * @return
	 */
	public List<CustomerDto> list3(Map<String,Object> queryparam);
	
	/**
	 * 孩子详情列表信息
	 * @param queryparam
	 * @return
	 */
	public List<CustomerDto>  list3ById(Map<String,Object> queryparam);
}
