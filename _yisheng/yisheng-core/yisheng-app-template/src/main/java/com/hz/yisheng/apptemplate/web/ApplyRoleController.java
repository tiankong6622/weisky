package com.hz.yisheng.apptemplate.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javafans.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hz.yisheng.admin.AdminConstants;
import com.hz.yisheng.admin.dto.MenuPermission;
import com.hz.yisheng.admin.filte.LoginUserContext;
import com.hz.yisheng.admin.pojo.AdminMenu;
import com.hz.yisheng.admin.pojo.AdminRole;
import com.hz.yisheng.admin.pojo.AdminRoleMenu;
import com.hz.yisheng.admin.pojo.AdminUserRole;
import com.hz.yisheng.admin.utils.JSONUtils;
import com.hz.yisheng.apptemplate.bo.AppUserRoleBO;
import com.hz.yisheng.apptemplate.bo.ApplyRoleBO;
import com.hz.yisheng.apptemplate.bo.ApplyRoleTemplateBO;
import com.hz.yisheng.apptemplate.bo.RelevanceBO;
import com.hz.yisheng.apptemplate.dto.Relevance;
import com.hz.yisheng.apptemplate.orm.AppUserRole;
import com.hz.yisheng.apptemplate.orm.ApplyRole;
import com.hz.yisheng.apptemplate.orm.ApplyRoleTemplate;
import com.hz.yisheng.apptemplate.orm.TemplatePermission;
import com.hz.yisheng.apptemplate.util.ParamUtils;


@Controller
@RequestMapping("/admin/approle")
public class ApplyRoleController extends BaseController{

	@Autowired
	private ApplyRoleBO applyRoleBO;
	@Autowired
	private ApplyRoleTemplateBO applyRoleTemplateBO;
	@Autowired
	private RelevanceBO relevanceBO;
	@Autowired
	private AppUserRoleBO appUserRoleBO;
	@RequestMapping("/list")
	public void list(ApplyRole applyRole,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> param = ParamUtils.preparePageQueryParam(request);
		paramPackage(applyRole,param);
		List<ApplyRole> list = applyRoleBO.list(param);
		Long count = Long.parseLong(list.size()+"");
		JSONUtils.rendJsonPage(count, list, response);
	}
	private void paramPackage(ApplyRole applyRole, Map<String, Object> param) {
		// TODO Auto-generated method stub
		if(applyRole == null || param == null)
			return;
		if(applyRole.getRoleName() != null && applyRole.getRoleName() != "")
		{
			param.put("roleName", applyRole.getRoleName());
		}
	}
	@RequestMapping("preview-add")
	public String previewAdd(@RequestParam(value="id",required=false) Long id,
							 HttpServletRequest request,HttpServletResponse response,Model model){
		if(id != null){
			ApplyRole ar = applyRoleBO.select(id);
			model.addAttribute("ar", ar);
		}else{
			Integer sort = 0;
			ApplyRole ar = new ApplyRole();
			try {
				sort = applyRoleBO.getMaxSort() + 1;
			} catch (Exception e) {
				sort = 0;
			}
			ar.setSort(sort);
			model.addAttribute("ar", ar);
		}
		return "/adminapply/role";
	}
	@RequestMapping("/save")
	public String save(ApplyRole applyRole,HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			if(applyRole.getId() != null){
				applyRole.setUpdater(LoginUserContext.getLoginUser().getId());
				applyRoleBO.update(applyRole);
			}else{
				applyRole.setCreator(LoginUserContext.getLoginUser().getId());
				applyRoleBO.insert(applyRole);
			}
			model.addAttribute("messager", "success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/adminapply/role";
	}
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			applyRoleBO.delete(id);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}
	/**
	 * 角色所对应的应用与模块
	 * @param response
	 * @param request
	 */
	@RequestMapping("/myTemplate/{roleId}")
	public void myTemplateList(@PathVariable("roleId") Long roleId, HttpServletResponse response,HttpServletRequest request){
		List<Long> rolelist = new ArrayList<Long>();
		rolelist.add(roleId);
		List<ApplyRoleTemplate> getMyTemplate = applyRoleTemplateBO.getByRoleId(rolelist);
		List<Relevance> all = relevanceBO.getAll();
		for(int i=0;i<all.size();i++){
			if(all.get(i).getType() == 0){
				all.get(i).setId(all.get(i).getTempId().longValue());
			}
		}
        //回传json的Map集合
		List<TemplatePermission> templateList = Lists.newArrayList();
		
		for(Relevance r : all)
		{
			//一级菜单
			if(r.get_parentId() == 0)
			{
				TemplatePermission mpOne = new TemplatePermission();
				mpOne.setId(r.getId());
				mpOne.setText(r.getName());
				
				//子列表
				List<TemplatePermission> templatePermissionList = Lists.newArrayList();
				for(Relevance rson : all)
				{
					if(rson.get_parentId().longValue() == r.getId().longValue())
					{
						TemplatePermission mpson = new TemplatePermission();
						mpson.setText(rson.getName());
						mpson.setId(rson.getId());
						//将子级放入子列表
						templatePermissionList.add(mpson);
						for(ApplyRoleTemplate templatemy : getMyTemplate)
						{
							//角色原先拥有的权限要打钩
							if(Long.parseLong(templatemy.getTemplateId()+"")== mpson.getId().longValue())
							{
								//如果子级中有一个是打钩的，那么父级一定也打钩了
//								mpOne.setChecked(true);
								mpson.setChecked(true);
							}
						}
					}
				}
				mpOne.setChildren(templatePermissionList);
				templateList.add(mpOne);
			}
		}
		
		JSONUtils.objToJSON(response, templateList);
	}
	
	/**
	 * 给角色分配模块
	 * @param adminUserMenu
	 * @param response
	 */
	@RequestMapping("/giveTemplate")
	public void giveTemplate(ApplyRoleTemplate applyRoleTemplate,HttpServletResponse response){
		try{
			if(applyRoleTemplate.getTemplateIdStr() != null){
				String[] menuIds = applyRoleTemplate.getTemplateIdStr().split(",");
				if(menuIds!=null && menuIds.length > 0){
					//先删除表中原先的数据
					applyRoleTemplateBO.deleteByRoleId(applyRoleTemplate.getRoleId());
					//重新将新的数据加进表中
					for(String menuId : menuIds){
						ApplyRoleTemplate arm = new ApplyRoleTemplate();
						arm.setTemplateId(Long.parseLong(menuId));
						arm.setRoleId(applyRoleTemplate.getRoleId());
						applyRoleTemplateBO.insert(arm);
					}
				}
			}
			JSONUtils.objToJSON(response, 1);
		}catch(Exception e){
			
			JSONUtils.objToJSON(response, 0);
		}
	}
	/**
	 * 我的应用角色
	 * @param userId
	 * @param response
	 * @param request
	 */
	@RequestMapping("/myrole/{userId}")
	public void myRoleList(@PathVariable("userId") Long userId, HttpServletResponse response,HttpServletRequest request){
		List<AppUserRole> getMyRole = appUserRoleBO.getByUserId(userId);
		Map params = Maps.newHashMapWithExpectedSize(0);
        List<ApplyRole> allRole = applyRoleBO.list(params);
        for(ApplyRole role : allRole)
        {
        	for(AppUserRole myRole : getMyRole)
        	{
        		if(myRole.getRoleId() == role.getId())
        		{
        			role.setChecked(true);
        		}
        	}
        }
        
		Map<String,Object> map = new HashMap<String,Object>(2);
		map.put(AdminConstants.JSON_COUNT, allRole.size());
		map.put(AdminConstants.JSON_ROWS, allRole);
		JSONUtils.objToJSON(response, map);
	}
	@RequestMapping("/giveAppRole")
	public void giveAppRole(@RequestParam(value="userID",required=true) Long userID,@RequestParam(value="ids",required=true) String ids,HttpServletRequest request, HttpServletResponse response){
		try {
			if(ids != null && ids != "" && ids.endsWith(",")){
				ids = ids.substring(0,ids.length()-1);
			}
			String[] roleIds = ids.split(",");
			if(roleIds != null && roleIds.length>0){
				appUserRoleBO.deleteByUserId(userID);
				for(String id : roleIds){
					AppUserRole aur = new AppUserRole();
					aur.setRoleId(Long.parseLong(id));
					aur.setUserId(userID);
					appUserRoleBO.insert(aur);
				}
			}
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			JSONUtils.objToJSON(response, 0);
			e.printStackTrace();
		}
	}
	/***
	 * 给角色添加人员之前的操作
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/giveuser")
	public String giveuser(@RequestParam(value="id",required=false) Long id,
						   HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			ApplyRole ap = applyRoleBO.select(id);
			model.addAttribute("ap", ap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/adminapply/giveuser";
	}
	/***
	 * 获取未分配给角色的人员
	 * @param id
	 * @param depId
	 * @param userName
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getusersnotinrole")
	public void getusersnotinrole(@RequestParam(value="id",required=true) Long id,
								  @RequestParam(value="depid",required=false) Long depId,
								  @RequestParam(value="username",required=false) String userName,
								  HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			List<AppUserRole> list = appUserRoleBO.getusersnotinrole(id,depId,userName);
			JSONUtils.rendJsonPage(new Integer(list.size()).longValue(), list, response);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.rendJsonPage(0l, new ArrayList<AppUserRole>(), response);
		}
	}
	/***
	 * 获取已分配给角色的人员
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getusersinrole")
	public void getusersinrole(@RequestParam(value="id",required=false) Long id,HttpServletRequest request,HttpServletResponse response){
		try {
			List<AppUserRole> list = appUserRoleBO.getusersinrole(id);
			JSONUtils.rendJsonPage(new Integer(list.size()).longValue(), list, response);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.rendJsonPage(0l, new ArrayList<AppUserRole>(), response);
		}
	}
	/***
	 * 给角色分配人员
	 * @param userids
	 * @param roleid
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addusers")
	public void adduser(@RequestParam(value="userids",required=true) String userids,
						@RequestParam(value="roleid",required=true) Long roleid,
						HttpServletRequest request,HttpServletResponse response){
		try {
			appUserRoleBO.adduser(userids,roleid);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}
	/***
	 * 取消角色已分配的人员
	 * @param userids
	 * @param roleid
	 * @param request
	 * @param response
	 */
	@RequestMapping("/removeusers")
	public void removeusers(@RequestParam(value="userids",required=true) String userids,
							@RequestParam(value="roleid",required=true) Long roleid,
							HttpServletRequest request,HttpServletResponse response){
		try {
			appUserRoleBO.deleteuser(userids,roleid);
			JSONUtils.objToJSON(response, 1);
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.objToJSON(response, 0);
		}
	}
}
	 
	

