package org.itboys.admin.tools;

import java.util.List;
import java.util.Map;

import org.itboys.admin.entity.AdminMenu;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AdminMenuSortUtil {
	public static List<AdminMenu> sortbyParam(List<AdminMenu> list) {
		Map<Long, List<AdminMenu>> adminmenusLists = Maps.newLinkedHashMap();

		for (AdminMenu t : list) {
			Long parentId = t.getParentId();
			List<AdminMenu> menus = adminmenusLists.get(parentId);
			if (menus == null) {
				menus = Lists.newLinkedList();
				adminmenusLists.put(parentId, menus);
			}
			menus.add(t);
		}
		List<AdminMenu> result = Lists.newLinkedList();
		addsubmenusbyId(adminmenusLists.get(0L),result,  adminmenusLists);
		return result;
	}

	private static void addsubmenusbyId(List<AdminMenu> menus,
			List<AdminMenu> resultList,
			Map<Long, List<AdminMenu>> adminmenusLists) {
		for (AdminMenu menu : menus) {
			resultList.add(menu);
			Long id = menu.getId();
			List<AdminMenu> submenus = adminmenusLists.get(id);
			if (submenus != null) {
				addsubmenusbyId( submenus,resultList, adminmenusLists);
			}
		}
	}

}
