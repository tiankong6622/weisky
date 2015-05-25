package org.itboys.admin.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.itboys.admin.dto.AdminUserDTO;
import org.itboys.admin.dto.EasyUiTreeDO;
import org.itboys.admin.entity.AdminOrg;
import org.itboys.admin.entity.AdminRole;
import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.entity.AdminUserOrgPost;
import org.itboys.admin.entity.AdminUserRole;
import org.itboys.admin.service.AdminOrgCityService;
import org.itboys.admin.service.AdminOrgService;
import org.itboys.admin.service.AdminRoleService;
import org.itboys.admin.service.AdminUserOrgPostService;
import org.itboys.admin.service.AdminUserService;
import org.itboys.admin.tools.AdminPermissionCheck;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.LoginHolder;
import org.itboys.admin.tools.WebConstants;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.encryption.Digests;
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.commons.utils.number.ToNumberUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private ResourceHolder resourceHolder;
	@Autowired
	private AdminOrgCityService adminOrgCityService;
	@Autowired
	private AdminOrgService adminOrgService;
	@Autowired
	private AdminUserOrgPostService adminUserOrgPostService;

	@ResponseBody
	@RequestMapping("/{id}")
	public AdminUser detail(@PathVariable("id") Long id) {
		return adminUserService.getById(id);
	}

	/**
	 * 分页查询管理用户
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	@AdminPermissionCheck("adminUser:list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Page<AdminUserDTO> page = adminUserService.pageQueryAll(request);
		JsonPageUtils.renderJsonPage(page.getTotal(),page.getTotalPages(), page.getData(), response);
	}

	/**
	 * 异步获取单个用户对象
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getUser/{id}")
	@AdminPermissionCheck("adminUser:getUser")
	public void getUser(@PathVariable("id") Long id,
			HttpServletResponse response) {
		AdminUserDTO user = adminUserService.getUserById(id);
		AjaxUtils.renderJson(response, user);
	}

	@RequestMapping("/isExists")
	public void isExists(String userName, HttpServletResponse response) {
		AjaxUtils.renderText(response,
				String.valueOf(adminUserService.isExists(userName)));
	}

	/**
	 * 修改密码
	 * 
	 * @param response
	 */
	@RequestMapping("/modifyPass")
	@AdminPermissionCheck("adminUser:save")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.valueOf(request.getParameter("id"));
		String newPassword = request.getParameter("new_password");
		if (StringUtils.isNotEmpty(newPassword)) {

		} else {
			newPassword = "123456";
		}
		try {
			if (id != null) {
				adminUserService.modifyPass(id, newPassword);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("modify password error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	/**
	 * 异步保存
	 */
	@RequestMapping("/save")
	@AdminPermissionCheck("adminUser:save")
	public void save(@ModelAttribute AdminUser user,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="logos",required=false) MultipartFile logos) {
		try {
			if (logos != null && !logos.isEmpty()) {
				String filePath = FileUtils.saveFile(logos.getInputStream(),
						resourceHolder.getStringValue("fileUploadPath"),
						logos.getOriginalFilename(), logos.getContentType());
				user.setBigLogo(filePath);
			}
			AdminUserOrgPost userOrgPost = new AdminUserOrgPost();
			userOrgPost.setOrgId(ToNumberUtils.getLongValue(user.getOrgId()));
			userOrgPost.setPostId(ToNumberUtils.getLongValue(user.getPostId()));
			userOrgPost.setIsManager(Integer.valueOf(request
					.getParameter("userOrgPostIsManager")));
			if(user.getId()!=0l){
				userOrgPost.setId(ToNumberUtils.getLongValue(request
						.getParameter("userOrgPostId")));
			}
			
			userOrgPost.setOrgId(ToNumberUtils.getLongValue(user.getOrgId()));
			userOrgPost.setPostId(ToNumberUtils.getLongValue(user.getPostId()));
			userOrgPost.setIsManager(Integer.valueOf(request
					.getParameter("userOrgPostIsManager")));
			if(user.getId()!=0l){
				userOrgPost.setId(ToNumberUtils.getLongValue(request
						.getParameter("userOrgPostId")));
			}
			
			AdminUserDTO dto = new AdminUserDTO();
			dto.setAdminUser(user);
			dto.setUserOrgPost(userOrgPost);
			if (user.getId() == 0l) {
				adminUserService.insert(dto);
			} else {
				adminUserService.doUpdate(dto);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save admin user error", e);
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	@RequestMapping("getUserRole/{id}")
	@AdminPermissionCheck("adminUser:getUserRole")
	public void getUserRole(@PathVariable Long id, HttpServletResponse response) {
		List<AdminRole> roleList = adminRoleService.list();
		List<EasyUiTreeDO> tree = Lists.newArrayListWithCapacity(roleList
				.size());
		List<Long> userRole = adminRoleService.getAdminRoleByUserIds(id);
		for (AdminRole roles : roleList) {
			EasyUiTreeDO roleTree = new EasyUiTreeDO();
			roleTree.setId(roles.getId());
			roleTree.setText(roles.getName());
			if (userRole.contains(roles.getId())) {
				roleTree.setChecked(true);
			}
			tree.add(roleTree);
		}
		AjaxUtils.renderJson(response, tree);
	}

	@RequestMapping("setUserRoles")
	@AdminPermissionCheck("adminUser:setUserRoles")
	public void setUserRoles(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Long userId = Long.valueOf(request.getParameter("userId"));
			String str = request.getParameter("roleIds");
			List<Long> roleIds = CommonCollectionUtils.splictToLongList(str,
					",");
			adminRoleService.insertUserRole(userId, roleIds);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	@AdminPermissionCheck("adminUser:delete")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		AjaxUtils.renderText(
				response,
				String.valueOf(adminUserService.deleteBy(adminUserService
						.getUserById(id).getAdminUser().getUsername(), id)));
	}

	@RequestMapping("/changepassword")
	public void changePassword(HttpServletRequest request,
			HttpServletResponse response) {
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		String upass = request.getParameter("upass");
		int i = adminUserService.updatePassword(oldpass, newpass, upass);
		AjaxUtils.renderText(response, String.valueOf(i));
	}

	@RequestMapping("/getUpdateUser")
	@AdminPermissionCheck("adminUser:getUpdateUser")
	public void getUpdateUser(HttpServletResponse response) {
		AjaxUtils.renderJson(
				response,
				adminUserService.getUserById(LoginHolder.getLoginUser(
						WebConstants.SessionKey.ADMIN_USER).getUserid()));
	}

	@RequestMapping("/getUpdateUserInfo")
	public void getUpdateUserInfo(HttpServletResponse response) {
		AjaxUtils.renderJson(
				response,
				adminUserService.getUserById(LoginHolder.getLoginUser(
						WebConstants.SessionKey.ADMIN_USER).getUserid()));
	}

	@RequestMapping("/getUserLoadPass")
	public void getUser(HttpServletResponse response) {
		AdminUserDTO user = adminUserService.getUserById(AdminSessionHolder
				.getAdminUserId());
		AjaxUtils.renderJson(response, user);
	}
	
	@RequestMapping("/simpleUpdate")
	public void simpleSave(@ModelAttribute AdminUser adminUser,HttpServletResponse response,@RequestParam("logos") MultipartFile logos){
		try {
			if (logos != null && !logos.isEmpty()) {
				String filePath = FileUtils.saveFile(logos.getInputStream(),
						resourceHolder.getStringValue("fileUploadPath"),
						logos.getOriginalFilename(), logos.getContentType());
				adminUser.setBigLogo(filePath);
			}
			if(adminUser.getId()==0l){
				adminUser.setPassword(Digests.md5(adminUser.getPassword()));
				adminUserService.save(adminUser);
				AdminOrg adminOrg = adminOrgService.getById(adminUser.getOrgId());
				AdminUserOrgPost adminUserOrgPost = new AdminUserOrgPost();
				adminUserOrgPost.setOrgId(adminUser.getOrgId());
				adminUserOrgPost.setPostId(0l);
				adminUserOrgPost.setDeptName(adminOrg.getFullNamePath());
				adminUserOrgPost.setUserId(adminUser.getId());
				adminUser.setPassword(Digests.md5(adminUser.getPassword()));
				adminUserOrgPostService.save(adminUserOrgPost);
				AdminUserRole userRole = new AdminUserRole();
				userRole.setUserId(adminUser.getId());
				userRole.setType(1);
			}else {
				AdminUser user = adminUserService.getById(adminUser.getId());
				adminUser.setCr(user.getCr());
				adminUser.setCt(user.getCt());
				adminUser.setOrgId(user.getOrgId());
				adminUser.setPostId(user.getPostId());
				adminUser.setPassword(user.getPassword());
				adminUser.setUr(AdminSessionHolder.getAdminUserId());
				adminUser.setUt(System.currentTimeMillis());
				adminUserService.update(adminUser);
			}
			
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("simpleSave admin user error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	/**
	 * 获取所有的用户信息（带分页）
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllUserAndPage")
	public Page<AdminUser> findAllUserAndPage(HttpServletRequest request) {
		return adminUserService.findAllUserAndPage(request);
	}
}
