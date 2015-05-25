/*package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.dao.CityMapper;
import org.itboys.admin.dto.EasyUiTreeDO;
import org.itboys.admin.entity.City;
import org.itboys.commons.dto.IdValueOption;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

*//**
 * 省市县业务层
 * 
 * @author ChenJunhui
 *//*
@Service
public class CityService implements InitializingBean {

	@Autowired
	private CityMapper cityMapper;

	private static List<IdValueOption> provinces = Lists.newArrayList(); // 省
	private static Map<Long, List<IdValueOption>> districts = null; // 地区
	private static Map<Long, List<IdValueOption>> citys = null; // 县 市 区
	private static Map<Long, String> cityMap = null;// 县 市 区

	*//**
	 * 获得城市名称全路径
	 * 
	 * @param cityId
	 * @return
	 *//*
	public String getCityFullName(Long cityId) {
		return cityMap.get(cityId);
	}

	public String getCityName(Long cityId) {
		String name = cityMap.get(cityId);
		if (name != null) {
			int index = name.lastIndexOf("/");
			return name.substring(index + 1);
		}
		return name;
	}

	public String getCityAllName(Long cityId) {
		String name = cityMap.get(cityId);
		if (name != null) {
			String result = StringUtils.EMPTY;
			String[] arr = StringUtils.split(name, "/");
			for (String s : arr) {
				if (StringUtils.isNotBlank(s) && result.indexOf(s) == -1) {
					result = result + s;
				}
			}
			return result;
		}
		return name;
	}

	*//**
	 * 获得城市
	 * 
	 * @param cityId
	 * @return
	 *//*
	public City getCity(Long cityId) {
		return cityMapper.getCity(cityId);
	}

	*//**
	 * 获得所有省份
	 * 
	 * @return
	 *//*
	public List<IdValueOption> getProvinces() {
		return provinces;
	}
	*//**
	 * 获取某一省份
	 * @param id
	 * @return
	 *//*
	public List<IdValueOption> getProvinces(Integer id) {
		City city = cityMapper.getCity(Long.valueOf(id));
		IdValueOption option = new IdValueOption();
		option.setId(city.getId());
		option.setValue(city.getName());
		List<IdValueOption> list = new ArrayList<IdValueOption>();
		list.add(option);
		return list;
	}

	*//**
	 * 获得省下的所有地区
	 * 
	 * @return
	 *//*
	public List<IdValueOption> getDistricts(Long provinceId) {
		return districts.get(provinceId);
	}

	*//**
	 * 获得地区下所有县市
	 * 
	 * @return
	 *//*
	public List<IdValueOption> getCitys(Long districtId) {
		return citys.get(districtId);
	}

	*//**
	 * 获取省份
	 * 
	 * @param provinceId
	 * @return
	 *//*
	public List<Long> getCityIdByProviceId(Long provinceId) {
		List<Long> ids = Lists.newArrayList();
		List<IdValueOption> disIV = getDistricts(provinceId);
		for (IdValueOption d : disIV) {
			ids.add(d.getId());
			List<IdValueOption> cIV = getCitys(d.getId());
			for (IdValueOption c : cIV) {
				ids.add(c.getId());
			}
		}
		return ids;
	}

	*//**
	 * 加载城市树
	 * 
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public List<EasyUiTreeDO> getCityTree() {
		return getCityTree(Collections.EMPTY_LIST,null,null);
	}

	*//**
	 * 加载城市树
	 * 
	 * @return
	 *//*
	public List<EasyUiTreeDO> getCityTree(List<Long> selectIds,Integer proviceId,Integer districtId) {
		List<IdValueOption> _provinces = getProvinces();
		List<EasyUiTreeDO> tree = Lists.newArrayListWithCapacity(_provinces.size());
		if(proviceId == null){
			_provinces = getProvinces();
		}else {
			_provinces = getProvinces(proviceId);
			IdValueOption p = _provinces.get(0);
			EasyUiTreeDO pt = new EasyUiTreeDO();
			pt.setId(p.getId());
			pt.setText(p.getValue());
			if (selectIds.contains(p.getId())) {
				pt.setChecked(true);
			}
			List<IdValueOption> _districts = getProvinces(districtId);
			for (IdValueOption d : _districts) {
				EasyUiTreeDO dt = new EasyUiTreeDO();
				dt.setId(d.getId());
				dt.setText(d.getValue());
				if (selectIds.contains(d.getId())) {
					dt.setChecked(true);
				}
				List<IdValueOption> _citys = getCitys(d.getId());
				for (IdValueOption c : _citys) {
					EasyUiTreeDO ct = new EasyUiTreeDO();
					ct.setId(c.getId());
					ct.setText(c.getValue());
					if (selectIds.contains(c.getId())) {
						ct.setChecked(true);
					}
					dt.getChildren().add(ct);
				}
				pt.getChildren().add(dt);
			}
			tree.add(pt);
			return tree;
		}
		for (IdValueOption p : _provinces) {
			EasyUiTreeDO pt = new EasyUiTreeDO();
			pt.setId(p.getId());
			pt.setText(p.getValue());
			if (selectIds.contains(p.getId())) {
				pt.setChecked(true);
			}
			List<IdValueOption> _districts = getDistricts(p.getId());
			for (IdValueOption d : _districts) {
				EasyUiTreeDO dt = new EasyUiTreeDO();
				dt.setId(d.getId());
				dt.setText(d.getValue());
				if (selectIds.contains(d.getId())) {
					dt.setChecked(true);
				}
				List<IdValueOption> _citys = getCitys(d.getId());
				for (IdValueOption c : _citys) {
					EasyUiTreeDO ct = new EasyUiTreeDO();
					ct.setId(c.getId());
					ct.setText(c.getValue());
					if (selectIds.contains(c.getId())) {
						ct.setChecked(true);
					}
					dt.getChildren().add(ct);
				}
				pt.getChildren().add(dt);
			}
			tree.add(pt);
		}
		return tree;
	}

	public List<Long> mergeParentIds(List<Long> cityIds) {
		List<City> all = cityMapper.getAll();
		for (City c : all) {
			if (cityIds.contains(c.getParentId())) {
				continue;
			}
			if (cityIds.contains(c.getId())) {
				cityIds.add(c.getParentId());
			}
		}
		return cityIds;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<City> allCitys = cityMapper.getAll();
		List<Long> provinceIds = Lists.newArrayList();
		int citysSize = (allCitys.size());
		cityMap = Maps.newHashMapWithExpectedSize(citysSize);
		// 组装省
		for (City city : allCitys) {
			cityMap.put(city.getId(), city.getFullName());
			if (city.getLevel() == 1) {
				provinceIds.add(city.getId());
				// 添加省
				IdValueOption option = new IdValueOption();
				option.setId(city.getId());
				option.setValue(city.getName());
				provinces.add(option);
			}
		}

		// 组装地区
		List<Long> districtIdList = Lists.newArrayList();
		districts = Maps.newHashMapWithExpectedSize(provinceIds.size());
		for (Long provinceId : provinceIds) {
			List<IdValueOption> districtList = Lists.newArrayList();
			for (City city : allCitys) {
				if (city.getParentId().equals(provinceId)) {
					IdValueOption option = new IdValueOption();
					option.setId(city.getId());
					option.setValue(city.getName());
					districtList.add(option);
					districtIdList.add(city.getId());
				}
			}
			districts.put(provinceId, districtList);
		}

		// 组装最小一级 县市区
		// 为了节省永久区内存 准确算出map的容量
		citys = Maps.newHashMapWithExpectedSize(districtIdList.size());
		for (Long districtId : districtIdList) {
			List<IdValueOption> cityList = Lists.newArrayList();
			for (City city : allCitys) {
				if (city.getParentId().equals(districtId)) {
					IdValueOption option = new IdValueOption();
					option.setId(city.getId());
					option.setValue(city.getName());
					cityList.add(option);
				}
			}
			citys.put(districtId, cityList);
		}
	}

	public List<City> getCityByName(String name) {
		return cityMapper.getCityByName(name);
	}
	
	public List<City> getCitysByParentId(long id){
		return cityMapper.getCitysByParentId(id);
	}
	
	public void insert(City city){
		cityMapper.insert(city);
	}
	
	public void update(City city){
		cityMapper.update(city);
	}
	
	public List<City> list(Map<String, Object> map){
		return cityMapper.list(map);
	}
	
	public Long count(Map<String, Object> map){
		return cityMapper.count(map);
	}
	
	public void delete(Long id){
		cityMapper.delete(id);
	}

}
*/