package com.hz.yisheng.apptemplate.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hz.yisheng.admin.bo.AdminDepartBO;
import com.hz.yisheng.admin.pojo.AdminDepart;
import com.hz.yisheng.apptemplate.dao.AppUserRoleMapper;
import com.hz.yisheng.apptemplate.orm.AppUserRole;

@Service
@Transactional
public class AppUserRoleBO {

	@Autowired
	private AppUserRoleMapper appUserRoleMapper;
	@Autowired
	private AdminDepartBO adminDepartBO;
	
	public List<AppUserRole> getByUserId(Long userId){
		return appUserRoleMapper.getByUserId(userId);
	}
	
	public void insert(AppUserRole aur){
		appUserRoleMapper.insert(aur);
	}
	
	public void deleteByUserId(Long userId){
		appUserRoleMapper.deleteByUserId(userId);
	}
	/***
	 * 获取未分配给角色的人员
	 * @param id 角色id
	 * @param depId 部门id
	 * @param userName 人员姓名
	 * @return
	 */
	public List<AppUserRole> getusersnotinrole(Long id,Long depId,String userName){
		//获取全部人员
		List<AppUserRole> listall = appUserRoleMapper.selectAllUser();
		//获取已经分配的人员
		List<AppUserRole> listrd = appUserRoleMapper.getByRoleId(id);
		//未分配的人员
		List<AppUserRole> listurd = new ArrayList<AppUserRole>();
		//根据部门获取未分配的人员
		List<AppUserRole> listd = new ArrayList<AppUserRole>();
		//部门列表
		List<AdminDepart> deplist = new ArrayList<AdminDepart>();
		//返回的数据
		List<AppUserRole> listre = new ArrayList<AppUserRole>();
		if(depId != null){
			deplist = adminDepartBO.getAllSon(depId);
			AdminDepart rootdep = new AdminDepart();
			rootdep.setId(depId);
			deplist.add(rootdep);
		}
		if(listall != null && listall.size()>0){
			if(listrd != null && listrd.size()>0){
				for(AppUserRole all : listall){
					boolean flag = false;
					for(AppUserRole rd : listrd){
						if(rd.getUserId().equals( all.getUserId())){
							flag = true;
							break;
						}
					}
					if(flag == false){
						listurd.add(all);
					}
				}
			}else{
				listurd = listall;
			}
		}
		if(listurd != null && listurd.size() > 0){
			if(depId != null){
				for(AppUserRole urd : listurd){
					for(AdminDepart ad : deplist){
						if(urd.getDepartId().longValue() == ad.getId().longValue()){
							listd.add(urd);
							break;
						}
							
					}
				}
			}else{
				listd = listurd;
			}
			if(userName != null && !userName.trim().equals("")){
				for(AppUserRole user : listd){
					if(user.getUserName().contains(userName))
						listre.add(user);
				}
			}else{
				listre = listd;
			}
		}
		return listre;
	}
	/***
	 * 获取已分配给角色的人员
	 * @param id
	 * @return
	 */
	public List<AppUserRole> getusersinrole(Long id){
		return appUserRoleMapper.getByRoleId(id);
	}
	/***
	 * 给角色添加人员
	 * @param userids
	 * @param roleid
	 */
	public void adduser(String userids,Long roleid){
		String[] str = userids.split(",");
		try {
			if(str != null && str.length > 0){
				List<String> list = Arrays.asList(str);
				for(String uid : list){
					appUserRoleMapper.adduser(uid,roleid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteuser(String userids,Long roleid){
		String[] str = userids.split(",");
		try {
			if(str != null && str.length > 0){
				List<String> list = Arrays.asList(str);
				for(String uid : list){
					appUserRoleMapper.deleteuser(uid,roleid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
