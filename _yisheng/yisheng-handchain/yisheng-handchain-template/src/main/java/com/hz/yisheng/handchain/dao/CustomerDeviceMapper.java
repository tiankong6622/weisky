package com.hz.yisheng.handchain.dao;

import java.util.List;
import java.util.Map;

import com.hz.yisheng.handchain.orm.CustomerDevice;

public interface CustomerDeviceMapper {
	/**
	 * 客户设备的新增
	 * @param customerDevice
	 */
	public void insert(CustomerDevice customerDevice);
	/**
	 * 查询所有客户的设备
	 * @param queryParam
	 * @return
	 */
	public List<CustomerDevice> list(Map<String,Object> queryParam);
	
	/**
	 * 根据设备id查询客户设备
	 * @param customerId
	 * @return
	 */
	public List<CustomerDevice> getCustomerDeviceById(Long deviceId);
	
	/**
	 * 根据客户id查询客户设备
	 * @param customerId
	 * @return
	 */
	public List<CustomerDevice> getCustomerDeviceByCustomerId(Long customerId);
	/**
	 * 客户设备记录个数
	 * @param param
	 * @return
	 */
	public Long count(Map<String,Object> param);
	/**
	 * 根据设备编号查询使用情况
	 * @param num
	 * @return
	 */
	public List<CustomerDevice> deviceUse(String num);
	/**
	 * 根据设备编号查看记录
	 * @param deviceNo
	 * @return
	 */
	public String findDeviceIdBy(String deviceNo);
}
