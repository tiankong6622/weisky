package org.itboys.admin.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.itboys.admin.constant.AdminConstants;
import org.itboys.admin.dto.AdminUserDTO;
import org.itboys.admin.dto.AdminUserData;
import org.itboys.admin.dto.MenuDTO;
import org.itboys.admin.entity.AdminMenu;
import org.itboys.admin.service.AdminMenuService;
import org.itboys.admin.service.AdminUserService;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.LoginUser;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.collection.FetchCondition;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 首页相关
 * 
 * @author 伟哥好强
 * 
 */
@RestController
public class AdminIndexController extends BaseController {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private ResourceHolder resourceHolder;
	@Autowired
	private AdminMenuService adminMenuService;

	@RequestMapping("/getAdminUserData")
	public AdminUserData getAdminUserData() {
		AdminUserData userData = new AdminUserData();
		Long userId = AdminSessionHolder.getAdminUserId();
		if (userId == null) {
			userData.setLogined(false);
			return userData;
		}
		userData = adminUserService.installAdminUserData(userId);

		return userData;
	}

	/**
	 * 首页跳转
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = { "/", "" })
	public ModelAndView index(HttpServletResponse response, Model model)
			throws Exception {
		model.addAttribute("staticRoot",resourceHolder.getStringValue("staticRoot"));
		model.addAttribute("adminRoot",resourceHolder.getStringValue("adminRoot"));
		model.addAttribute("imgRoot",resourceHolder.getStringValue("imgRoot"));
		if (AdminSessionHolder.getAdminUserId() == null) {
			return new ModelAndView("redirect:/admin/login");
			// response.sendRedirect(resourceHolder.getStringValue("adminLoginUrl"));
		} else {
			final LoginUser user = AdminSessionHolder.getAdminLoginUser();

			Map<String, Object> param = Maps.newHashMapWithExpectedSize(0);
			List<AdminMenu> menus = adminMenuService.list(param);

			final List<AdminMenu> roleMenus = (user.getType() == AdminConstants.TYPE_SUPER) ? null
					: new ArrayList<AdminMenu>(user.getMenuIds().size());
			if (user.getType() != AdminConstants.TYPE_SUPER) {
				for (AdminMenu am : menus) {
					if (user.getMenuIds().contains(am.getId())) {
						roleMenus.add(am);
					}
				}
			}
			//新页面暂时不考虑权限
			List<AdminMenu> allowMenus = user.getType() == AdminConstants.TYPE_SUPER ? menus
					: CommonCollectionUtils.filterCollection(menus,
							new FetchCondition<AdminMenu>() {
								@Override
								public boolean needFetch(AdminMenu t) {
									for (AdminMenu rm : roleMenus) {
										if (rm.getFullPath()
												.contains(
														AdminMenuService.PATH_SPLIT
																+ t.getId()
																+ AdminMenuService.PATH_SPLIT)) {
											return true;
										}
									}
									return false;
								}
							});
//			List<AdminMenu> allowMenus =  menus;
			List<MenuDTO> menuDTOList = Lists.newArrayList();
			for (AdminMenu menu : allowMenus) {
				if (menu.getParentId().equals(0L)) {
					MenuDTO dto = new MenuDTO();
					dto.setMenu(menu);
					menuDTOList.add(dto);
				}
			}
			for (MenuDTO dto : menuDTOList) {
				for (AdminMenu menu : allowMenus) {
					if (menu.getParentId().equals(dto.getMenu().getId())) {
						dto.getChildren().add(menu);
					}
				}
			}
			model.addAttribute("allowMenus", menuDTOList);

			AdminUserDTO au = adminUserService.getUserById(user.getUserid());

			model.addAttribute("adminUserDTO", au);
			return new ModelAndView(
					resourceHolder.getStringValue("adminIndexUrl"));
			// response.sendRedirect(resourceHolder.getStringValue("adminIndexUrl"));
		}
	}
	@RequestMapping("/sessiondelay")
	public ModelAndView sessionDelay(@RequestParam("type")String type){
		if(type.equals("0")){
			return new ModelAndView("session-delay");
		}else {
			return new ModelAndView("session-delay-index");
		}
	}

}
